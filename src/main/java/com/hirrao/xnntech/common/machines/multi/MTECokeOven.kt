package com.hirrao.xnntech.common.machines.multi

import com.gtnewhorizon.structurelib.alignment.constructable.ISurvivalConstructable
import com.gtnewhorizon.structurelib.structure.IStructureDefinition
import com.gtnewhorizon.structurelib.structure.ISurvivalBuildEnvironment
import com.gtnewhorizon.structurelib.structure.StructureDefinition
import com.gtnewhorizon.structurelib.structure.StructureUtility.*
import com.hirrao.xnntech.utils.addAllHatchesNormal
import com.hirrao.xnntech.utils.addMufflerHatchNormal
import cpw.mods.fml.relauncher.Side
import cpw.mods.fml.relauncher.SideOnly
import gregtech.api.GregTechAPI
import gregtech.api.enums.HatchElement
import gregtech.api.enums.HeatingCoilLevel
import gregtech.api.enums.SoundResource
import gregtech.api.enums.Textures
import gregtech.api.interfaces.ITexture
import gregtech.api.interfaces.tileentity.IGregTechTileEntity
import gregtech.api.logic.ProcessingLogic
import gregtech.api.metatileentity.implementations.MTEExtendedPowerMultiBlockBase
import gregtech.api.recipe.RecipeMap
import gregtech.api.recipe.RecipeMaps
import gregtech.api.recipe.check.CheckRecipeResult
import gregtech.api.render.TextureFactory
import gregtech.api.util.GTStructureUtility.*
import gregtech.api.util.MultiblockTooltipBuilder
import gregtech.api.util.tooltip.TooltipTier
import net.minecraft.item.ItemStack
import net.minecraft.util.StatCollector
import net.minecraftforge.common.util.ForgeDirection
import java.util.function.BiConsumer

class MTECokeOven : MTEExtendedPowerMultiBlockBase<MTECokeOven>, ISurvivalConstructable {
    companion object {
        private const val CASING_INDEX = 1090
        private val STRUCTURE_DEFINITION: IStructureDefinition<MTECokeOven> =
            StructureDefinition.builder<MTECokeOven>().addShape(
                "main", transpose(
                    arrayOf(
                        arrayOf("ttt", "ccc", "ccc", "ttt"),
                        arrayOf("t~t", "c-c", "c-c", "tmt"),
                        arrayOf("ttt", "ccc", "ccc", "ttt")
                    )
                )
            ).addElement('m', HatchElement.Muffler.newAny(CASING_INDEX, 2)).addElement(
                'c', activeCoils(ofCoil(BiConsumer { obj: MTECokeOven, aCoilLevel: HeatingCoilLevel ->
                    obj.coilLevel = aCoilLevel
                }) { obj -> obj.coilLevel })
            ).addElement(
                't', buildHatchAdder(MTECokeOven::class.java).atLeast(
                    HatchElement.InputBus,
                    HatchElement.InputHatch,
                    HatchElement.OutputBus,
                    HatchElement.OutputHatch,
                    HatchElement.Energy,
                    HatchElement.Maintenance
                ).casingIndex(CASING_INDEX).dot(1).buildAndChain(
                    onElementPass(
                        { obj -> obj.onCasingAdded() }, ofBlock(GregTechAPI.sBlockCasingsNH, 2)
                    )
                )
            ).build()
    }

    private val mPollutionPerSecond = 300
    private var coilLevel: HeatingCoilLevel = HeatingCoilLevel.None
    private var mCasingAmount = 0

    constructor(aID: Int, aName: String, aNameRegional: String) : super(aID, aName, aNameRegional)

    constructor(aName: String) : super(aName)

    override fun construct(stackSize: ItemStack, hintsOnly: Boolean) {
        buildPiece("main", stackSize, hintsOnly, 1, 1, 0)
    }

    override fun survivalConstruct(stackSize: ItemStack, elementBudget: Int, env: ISurvivalBuildEnvironment): Int {
        if (mMachine) return -1
        return survivalBuildPiece("main", stackSize, 1, 1, 0, elementBudget, env, false, true)
    }

    override fun getRecipeMap(): RecipeMap<*> = RecipeMaps.pyrolyseRecipes

    override fun getStructureDefinition() = STRUCTURE_DEFINITION

    override fun createTooltip(): MultiblockTooltipBuilder {
        val tt = MultiblockTooltipBuilder()
        tt.addMachineType(StatCollector.translateToLocal("xnntech.coke_oven.gui.machine_type"))
            .addInfo(StatCollector.translateToLocal("xnntech.coke_oven.gui.info"))
            .addDynamicSpeedInfo(0.5f, TooltipTier.COIL)
            .addCasingInfoMin(StatCollector.translateToLocal("xnntech.coke_oven.gui.casing_info"), 8, false)
            .beginStructureBlock(3, 3, 4, true).addPollutionAmount(getPollutionPerSecond(null))
            .addController(StatCollector.translateToLocal("xnntech.gui.front_center"))
            .addAllHatchesNormal()
            .addMufflerHatchNormal().toolTipFinisher()
        return tt
    }

    override fun checkMachine(aBaseMetaTileEntity: IGregTechTileEntity, aStack: ItemStack?): Boolean {
        coilLevel = HeatingCoilLevel.None
        mCasingAmount = 0
        return checkPiece(
            "main", 1, 1, 0
        ) && mCasingAmount >= 8 && mMaintenanceHatches.size == 1 && !mMufflerHatches.isEmpty()
    }

    override fun newMetaEntity(aTileEntity: IGregTechTileEntity) = MTECokeOven(this.mName)

    override fun createProcessingLogic(): ProcessingLogic {
        return object : ProcessingLogic() {
            override fun process(): CheckRecipeResult {
                setSpeedBonus((2.0 / (1 + coilLevel.tier)))
                return super.process()
            }
        }
    }

    /**
     * Now Copy From [gregtech.common.tileentities.machines.multi.MTEPyrolyseOven.getTexture]
     */
    override fun getTexture(
        baseMetaTileEntity: IGregTechTileEntity,
        sideDirection: ForgeDirection,
        facingDirection: ForgeDirection,
        colorIndex: Int,
        active: Boolean,
        redstoneLevel: Boolean
    ): Array<ITexture> {
        if (sideDirection == facingDirection) {
            if (active) return arrayOf(
                Textures.BlockIcons.getCasingTextureForId(CASING_INDEX),
                TextureFactory.builder().addIcon(Textures.BlockIcons.OVERLAY_FRONT_PYROLYSE_OVEN_ACTIVE).extFacing()
                    .build(),
                TextureFactory.builder().addIcon(Textures.BlockIcons.OVERLAY_FRONT_PYROLYSE_OVEN_ACTIVE_GLOW)
                    .extFacing().glow().build()
            )
            return arrayOf(
                Textures.BlockIcons.getCasingTextureForId(CASING_INDEX),
                TextureFactory.builder().addIcon(Textures.BlockIcons.OVERLAY_FRONT_PYROLYSE_OVEN).extFacing().build(),
                TextureFactory.builder().addIcon(Textures.BlockIcons.OVERLAY_FRONT_PYROLYSE_OVEN_GLOW).extFacing()
                    .glow().build()
            )
        }
        return arrayOf(Textures.BlockIcons.getCasingTextureForId(CASING_INDEX))
    }

    private fun onCasingAdded() = mCasingAmount++

    override fun getPollutionPerSecond(aStack: ItemStack?) = mPollutionPerSecond

    override fun supportsVoidProtection() = true

    override fun supportsInputSeparation() = true

    override fun supportsBatchMode() = true

    override fun supportsSingleRecipeLocking() = true

    @SideOnly(Side.CLIENT)
    override fun getActivitySoundLoop(): SoundResource {
        return SoundResource.GTCEU_LOOP_FIRE
    }
}
