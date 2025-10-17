package com.hirrao.xnntech.common.machines.multi;

import static com.gtnewhorizon.structurelib.structure.StructureUtility.*;
import static gregtech.api.enums.HatchElement.*;
import static gregtech.api.enums.HatchElement.Energy;
import static gregtech.api.enums.HatchElement.Maintenance;
import static gregtech.api.enums.Textures.BlockIcons.*;
import static gregtech.api.util.GTStructureUtility.buildHatchAdder;

import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.util.ForgeDirection;

import org.jetbrains.annotations.NotNull;

import com.gtnewhorizon.structurelib.alignment.constructable.ISurvivalConstructable;
import com.gtnewhorizon.structurelib.structure.IStructureDefinition;
import com.gtnewhorizon.structurelib.structure.ISurvivalBuildEnvironment;
import com.gtnewhorizon.structurelib.structure.StructureDefinition;

import gregtech.api.GregTechAPI;
import gregtech.api.enums.Textures;
import gregtech.api.interfaces.ITexture;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.logic.ProcessingLogic;
import gregtech.api.metatileentity.implementations.MTEExtendedPowerMultiBlockBase;
import gregtech.api.recipe.RecipeMap;
import gregtech.api.recipe.RecipeMaps;
import gregtech.api.recipe.check.CheckRecipeResult;
import gregtech.api.render.TextureFactory;
import gregtech.api.util.GTUtility;
import gregtech.api.util.MultiblockTooltipBuilder;
import gregtech.common.blocks.BlockCasings10;
import gregtech.common.tileentities.machines.multi.MTEMultiSolidifier;

public class MTEMultiFluidSolidifier extends MTEExtendedPowerMultiBlockBase<MTEMultiFluidSolidifier>
    implements ISurvivalConstructable {

    private static final IStructureDefinition<MTEMultiFluidSolidifier> STRUCTURE_DEFINITION = StructureDefinition
        .<MTEMultiFluidSolidifier>builder()
        .addShape(
            "main",
            transpose(
                new String[][] { { "ttttt", "ttttt", "ttttt" }, { "tt~tt", "tc-ct", "ttttt" },
                    { "ttttt", "ttttt", "ttttt" } }))
        .addElement('c', ofBlock(GregTechAPI.sBlockCasings10, 14))
        .addElement(
            't',
            buildHatchAdder(MTEMultiFluidSolidifier.class).atLeast(InputBus, InputHatch, OutputBus, Maintenance, Energy)
                .casingIndex(((BlockCasings10) GregTechAPI.sBlockCasings10).getTextureIndex(13))
                .dot(1)
                .buildAndChain(
                    onElementPass(MTEMultiFluidSolidifier::onCasingAdded, ofBlock(GregTechAPI.sBlockCasings10, 13))))
        .build();
    private int mCasingAmount;

    public MTEMultiFluidSolidifier(String aName) {
        super(aName);
    }

    public MTEMultiFluidSolidifier(int aID, String aName, String aNameRegional) {
        super(aID, aName, aNameRegional);
    }

    @Override
    public RecipeMap<?> getRecipeMap() {
        return RecipeMaps.fluidSolidifierRecipes;
    }

    @Override
    public void construct(ItemStack stackSize, boolean hintsOnly) {
        buildPiece("main", stackSize, hintsOnly, 2, 1, 0);
    }

    @Override
    public int survivalConstruct(ItemStack stackSize, int elementBudget, ISurvivalBuildEnvironment env) {
        if (mMachine) return -1;
        return survivalBuildPiece("main", stackSize, 2, 1, 0, elementBudget, env, false, true);
    }

    @Override
    public IStructureDefinition<MTEMultiFluidSolidifier> getStructureDefinition() {
        return STRUCTURE_DEFINITION;
    }

    @Override
    protected MultiblockTooltipBuilder createTooltip() {
        final MultiblockTooltipBuilder tt = new MultiblockTooltipBuilder();
        tt.addMachineType(StatCollector.translateToLocal("xnntech.multi_fluid_solidifier.gui.machine_type"))
            .addInfo(StatCollector.translateToLocal("xnntech.multi_fluid_solidifier.gui.info"))
            .addStaticSpeedInfo(2.0f)
            .addController(StatCollector.translateToLocal("xnntech.gui.front_center"))
            .beginStructureBlock(5, 3, 3, true)
            .toolTipFinisher();
        return tt;
    }

    @Override
    public boolean checkMachine(IGregTechTileEntity aBaseMetaTileEntity, ItemStack aStack) {
        return checkPiece("main", 2, 1, 0) && mCasingAmount >= 12 && mMaintenanceHatches.size() == 1;
    }

    @Override
    protected ProcessingLogic createProcessingLogic() {
        return new ProcessingLogic() {

            @NotNull
            @Override
            public CheckRecipeResult process() {
                setSpeedBonus(0.5);
                setMaxParallel(16);
                return super.process();
            }
        };
    }

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
        return new MTEMultiFluidSolidifier(this.mName);
    }

    /**
     * From {@link MTEMultiSolidifier#getTexture}
     */
    @Override
    public ITexture[] getTexture(IGregTechTileEntity baseMetaTileEntity, ForgeDirection side, ForgeDirection facing,
        int colorIndex, boolean active, boolean redstoneLevel) {
        ITexture[] rTexture;
        if (side == facing) {
            if (active) {
                rTexture = new ITexture[] {
                    Textures.BlockIcons
                        .getCasingTextureForId(GTUtility.getCasingTextureIndex(GregTechAPI.sBlockCasings10, 13)),
                    TextureFactory.builder()
                        .addIcon(OVERLAY_FRONT_MULTI_CANNER_ACTIVE)
                        .extFacing()
                        .build(),
                    TextureFactory.builder()
                        .addIcon(OVERLAY_FRONT_MULTI_CANNER_ACTIVE_GLOW)
                        .extFacing()
                        .glow()
                        .build() };
            } else {
                rTexture = new ITexture[] {
                    Textures.BlockIcons
                        .getCasingTextureForId(GTUtility.getCasingTextureIndex(GregTechAPI.sBlockCasings10, 13)),
                    TextureFactory.builder()
                        .addIcon(OVERLAY_FRONT_MULTI_CANNER)
                        .extFacing()
                        .build(),
                    TextureFactory.builder()
                        .addIcon(OVERLAY_FRONT_MULTI_CANNER_GLOW)
                        .extFacing()
                        .glow()
                        .build() };
            }
        } else {
            rTexture = new ITexture[] { Textures.BlockIcons
                .getCasingTextureForId(GTUtility.getCasingTextureIndex(GregTechAPI.sBlockCasings10, 13)) };
        }
        return rTexture;
    }

    @Override
    public boolean supportsVoidProtection() {
        return true;
    }

    @Override
    public boolean supportsInputSeparation() {
        return true;
    }

    @Override
    public boolean supportsBatchMode() {
        return true;
    }

    @Override
    public boolean supportsSingleRecipeLocking() {
        return true;
    }

    private void onCasingAdded() {
        mCasingAmount++;
    }
}
