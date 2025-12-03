package com.hirrao.xnntech.common.machines.multi

import com.gtnewhorizon.structurelib.alignment.constructable.ISurvivalConstructable
import com.gtnewhorizon.structurelib.structure.IStructureDefinition
import com.gtnewhorizon.structurelib.structure.ISurvivalBuildEnvironment
import com.gtnewhorizon.structurelib.structure.StructureDefinition
import com.gtnewhorizon.structurelib.structure.StructureUtility.*
import com.hirrao.xnntech.utils.addBusesNormal
import com.hirrao.xnntech.utils.addEnergyHatchNormal
import com.hirrao.xnntech.utils.addInputHatchNormal
import com.hirrao.xnntech.utils.addMaintenanceNormal
import com.hirrao.xnntech.utils.tr
import gregtech.api.GregTechAPI
import gregtech.api.enums.HatchElement
import gregtech.api.enums.Textures
import gregtech.api.interfaces.ITexture
import gregtech.api.interfaces.tileentity.IGregTechTileEntity
import gregtech.api.logic.ProcessingLogic
import gregtech.api.metatileentity.implementations.MTEExtendedPowerMultiBlockBase
import gregtech.api.recipe.RecipeMap
import gregtech.api.recipe.RecipeMaps
import gregtech.api.render.TextureFactory
import gregtech.api.util.GTStructureUtility.buildHatchAdder
import gregtech.api.util.GTUtility
import gregtech.api.util.MultiblockTooltipBuilder
import gregtech.api.util.tooltip.TooltipTier
import gregtech.common.blocks.BlockCasings10
import net.minecraft.item.ItemStack
import net.minecraft.util.StatCollector
import net.minecraftforge.common.util.ForgeDirection

class MTEMultiFluidSolidifier : MTEExtendedPowerMultiBlockBase<MTEMultiFluidSolidifier>, ISurvivalConstructable {
    companion object {
        private const val BASE_PARALLEL = 16
        private val STRUCTURE_DEFINITION: IStructureDefinition<MTEMultiFluidSolidifier> =
            StructureDefinition.builder<MTEMultiFluidSolidifier>().addShape(
                "main", transpose(
                    arrayOf(
                        arrayOf("ttttt", "ttttt", "ttttt"),
                        arrayOf("tt~tt", "tc-ct", "ttttt"),
                        arrayOf("ttttt", "ttttt", "ttttt")
                    )
                )
            ).addElement('c', ofBlock(GregTechAPI.sBlockCasings10, 14)).addElement(
                't', buildHatchAdder(MTEMultiFluidSolidifier::class.java).atLeast(
                    HatchElement.InputBus,
                    HatchElement.InputHatch,
                    HatchElement.OutputBus,
                    HatchElement.Maintenance,
                    HatchElement.Energy
                ).casingIndex((GregTechAPI.sBlockCasings10 as BlockCasings10).getTextureIndex(13)).dot(1).buildAndChain(
                    onElementPass(
                        { obj -> obj.onCasingAdded() }, ofBlock(GregTechAPI.sBlockCasings10, 13)
                    )
                )
            ).build()
    }

    private var mCasingAmount = 0

    constructor(aName: String) : super(aName)

    constructor(aID: Int, aName: String, aNameRegional: String) : super(aID, aName, aNameRegional)

    override fun getRecipeMap(): RecipeMap<*> = RecipeMaps.fluidSolidifierRecipes

    override fun construct(stackSize: ItemStack, hintsOnly: Boolean) {
        buildPiece("main", stackSize, hintsOnly, 2, 1, 0)
    }

    override fun survivalConstruct(stackSize: ItemStack, elementBudget: Int, env: ISurvivalBuildEnvironment): Int {
        if (mMachine) return -1
        return survivalBuildPiece("main", stackSize, 2, 1, 0, elementBudget, env, false, true)
    }

    override fun getStructureDefinition()= STRUCTURE_DEFINITION

    override fun createTooltip(): MultiblockTooltipBuilder {
        val tt = MultiblockTooltipBuilder()
        tt.addMachineType(tr("xnntech.multi_fluid_solidifier.gui.machine_type"))
            .addInfo(tr("xnntech.multi_fluid_solidifier.gui.info")).addStaticSpeedInfo(2.0f)
            .addDynamicParallelInfo(BASE_PARALLEL, TooltipTier.VOLTAGE)
            .beginStructureBlock(5, 3, 3, true)
            .addController(tr("xnntech.gui.front_center"))
            .addInputHatchNormal().addBusesNormal().addEnergyHatchNormal()
            .addMaintenanceNormal().toolTipFinisher()
        return tt
    }

    override fun checkMachine(aBaseMetaTileEntity: IGregTechTileEntity, aStack: ItemStack?): Boolean {
        return checkPiece("main", 2, 1, 0) && mCasingAmount >= 12 && mMaintenanceHatches.size == 1
    }

    override fun createProcessingLogic(): ProcessingLogic =
        ProcessingLogic().setSpeedBonus(1.0 / 2.0).setMaxParallelSupplier { this.trueParallel }


    override fun getMaxParallelRecipes() = (BASE_PARALLEL * GTUtility.getTier(this.maxInputVoltage))

    override fun newMetaEntity(aTileEntity: IGregTechTileEntity) = MTEMultiFluidSolidifier(this.mName)

    /**
     * From [gregtech.common.tileentities.machines.multi.MTEMultiSolidifier.getTexture]
     */
    override fun getTexture(
        baseMetaTileEntity: IGregTechTileEntity,
        side: ForgeDirection,
        facing: ForgeDirection,
        colorIndex: Int,
        active: Boolean,
        redstoneLevel: Boolean
    ): Array<ITexture> {
        val rTexture: Array<ITexture>
        if (side == facing) {
            if (active) {
                rTexture = arrayOf(
                    Textures.BlockIcons.getCasingTextureForId(
                        GTUtility.getCasingTextureIndex(
                            GregTechAPI.sBlockCasings10, 13
                        )
                    ),
                    TextureFactory.builder().addIcon(Textures.BlockIcons.OVERLAY_FRONT_MULTI_CANNER_ACTIVE).extFacing()
                        .build(),
                    TextureFactory.builder().addIcon(Textures.BlockIcons.OVERLAY_FRONT_MULTI_CANNER_ACTIVE_GLOW)
                        .extFacing().glow().build()
                )
            } else {
                rTexture = arrayOf(
                    Textures.BlockIcons.getCasingTextureForId(
                        GTUtility.getCasingTextureIndex(
                            GregTechAPI.sBlockCasings10, 13
                        )
                    ),
                    TextureFactory.builder().addIcon(Textures.BlockIcons.OVERLAY_FRONT_MULTI_CANNER).extFacing()
                        .build(),
                    TextureFactory.builder().addIcon(Textures.BlockIcons.OVERLAY_FRONT_MULTI_CANNER_GLOW).extFacing()
                        .glow().build()
                )
            }
        } else {
            rTexture = arrayOf(
                Textures.BlockIcons.getCasingTextureForId(
                    GTUtility.getCasingTextureIndex(
                        GregTechAPI.sBlockCasings10, 13
                    )
                )
            )
        }
        return rTexture
    }

    override fun supportsVoidProtection() = true

    override fun supportsInputSeparation() = true

    override fun supportsBatchMode() = true

    override fun supportsSingleRecipeLocking() = true

    private fun onCasingAdded() {
        mCasingAmount++
    }
}
