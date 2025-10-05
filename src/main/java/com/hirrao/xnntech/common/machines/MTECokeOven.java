package com.hirrao.xnntech.common.machines;

import static com.gtnewhorizon.structurelib.structure.StructureUtility.*;
import static gregtech.api.enums.HatchElement.*;
import static gregtech.api.enums.Textures.BlockIcons.*;
import static gregtech.api.util.GTStructureUtility.*;
import static net.minecraft.util.StatCollector.translateToLocal;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.ForgeDirection;

import com.gtnewhorizon.structurelib.alignment.constructable.ISurvivalConstructable;
import com.gtnewhorizon.structurelib.structure.IStructureDefinition;
import com.gtnewhorizon.structurelib.structure.StructureDefinition;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtech.api.GregTechAPI;
import gregtech.api.enums.HeatingCoilLevel;
import gregtech.api.enums.SoundResource;
import gregtech.api.enums.Textures;
import gregtech.api.interfaces.ITexture;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.metatileentity.implementations.MTEEnhancedMultiBlockBase;
import gregtech.api.recipe.RecipeMap;
import gregtech.api.recipe.RecipeMaps;
import gregtech.api.render.TextureFactory;
import gregtech.api.util.MultiblockTooltipBuilder;
import gregtech.api.util.tooltip.TooltipTier;
import gregtech.common.tileentities.machines.multi.MTEPyrolyseOven;

public class MTECokeOven extends MTEEnhancedMultiBlockBase<MTECokeOven> implements ISurvivalConstructable {

    private HeatingCoilLevel coilHeat;
    private static final int CASING_INDEX = 1090;
    private int mCasingAmount = 0;
    private static final int mPollutionPerSecond = 300;

    private static final IStructureDefinition<MTECokeOven> STRUCTURE_DEFINITION = StructureDefinition
        .<MTECokeOven>builder()
        .addShape(
            "main",
            transpose(
                new String[][] { { "ttt", "ccc", "ccc", "ttt" }, { "t~t", "c-c", "c-c", "tmt" },
                    { "ttt", "ccc", "ccc", "ttt" } }))
        .addElement('m', Muffler.newAny(CASING_INDEX, 2))
        .addElement('c', activeCoils(ofCoil(MTECokeOven::setCoilLevel, MTECokeOven::getCoilLevel)))
        .addElement(
            't',
            buildHatchAdder(MTECokeOven.class)
                .atLeast(InputBus, InputHatch, OutputBus, OutputHatch, Energy, Maintenance)
                .casingIndex(CASING_INDEX)
                .dot(1)
                .buildAndChain(onElementPass(MTECokeOven::onCasingAdded, ofBlock(GregTechAPI.sBlockCasingsNH, 2))))
        .build();

    public MTECokeOven(int aID, String aName, String aNameRegional) {
        super(aID, aName, aNameRegional);
    }

    public MTECokeOven(String aName) {
        super(aName);
    }

    @Override
    public void construct(ItemStack stackSize, boolean hintsOnly) {
        buildPiece("main", stackSize, hintsOnly, 1, 1, 0);
    }

    @Override
    public RecipeMap<?> getRecipeMap() {
        return RecipeMaps.pyrolyseRecipes;
    }

    @Override
    public IStructureDefinition<MTECokeOven> getStructureDefinition() {
        return STRUCTURE_DEFINITION;
    }

    @Override
    protected MultiblockTooltipBuilder createTooltip() {
        final MultiblockTooltipBuilder tt = new MultiblockTooltipBuilder();
        tt.addMachineType(translateToLocal("xnntech.coke_oven.gui.machine_type"))
            .addInfo(translateToLocal("xnntech.coke_oven.gui.info"))
            .addController(translateToLocal("xnntech.coke_oven.gui.controller"))
            .addDynamicSpeedInfo(0.5f, TooltipTier.COIL)
            .addCasingInfoMin(translateToLocal("xnntech.coke_oven.gui.casing_info"), 8, false)
            .beginStructureBlock(3, 3, 4, true)
            .addPollutionAmount(getPollutionPerSecond(null))
            .toolTipFinisher();
        return tt;
    }

    @Override
    public boolean checkMachine(IGregTechTileEntity aBaseMetaTileEntity, ItemStack aStack) {
        coilHeat = HeatingCoilLevel.None;
        mCasingAmount = 0;
        return checkPiece("main", 1, 1, 0) && mCasingAmount >= 8
            && mMaintenanceHatches.size() == 1
            && !mMufflerHatches.isEmpty();
    }

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
        return new MTECokeOven(this.mName);
    }

    /**
     *
     * Now Copy From {@link MTEPyrolyseOven}
     *
     */
    @Override
    public ITexture[] getTexture(IGregTechTileEntity baseMetaTileEntity, ForgeDirection sideDirection,
        ForgeDirection facingDirection, int colorIndex, boolean active, boolean redstoneLevel) {
        if (sideDirection == facingDirection) {
            if (active) return new ITexture[] { Textures.BlockIcons.getCasingTextureForId(CASING_INDEX),
                TextureFactory.builder()
                    .addIcon(OVERLAY_FRONT_PYROLYSE_OVEN_ACTIVE)
                    .extFacing()
                    .build(),
                TextureFactory.builder()
                    .addIcon(OVERLAY_FRONT_PYROLYSE_OVEN_ACTIVE_GLOW)
                    .extFacing()
                    .glow()
                    .build() };
            return new ITexture[] { Textures.BlockIcons.getCasingTextureForId(CASING_INDEX), TextureFactory.builder()
                .addIcon(OVERLAY_FRONT_PYROLYSE_OVEN)
                .extFacing()
                .build(),
                TextureFactory.builder()
                    .addIcon(OVERLAY_FRONT_PYROLYSE_OVEN_GLOW)
                    .extFacing()
                    .glow()
                    .build() };
        }
        return new ITexture[] { Textures.BlockIcons.getCasingTextureForId(CASING_INDEX) };
    }

    public HeatingCoilLevel getCoilLevel() {
        return coilHeat;
    }

    private void setCoilLevel(HeatingCoilLevel aCoilLevel) {
        coilHeat = aCoilLevel;
    }

    private void onCasingAdded() {
        mCasingAmount++;
    }

    @Override
    public int getPollutionPerSecond(ItemStack aStack) {
        return mPollutionPerSecond;
    }

    @SideOnly(Side.CLIENT)
    @Override
    protected SoundResource getActivitySoundLoop() {
        return SoundResource.GTCEU_LOOP_FIRE;
    }
}
