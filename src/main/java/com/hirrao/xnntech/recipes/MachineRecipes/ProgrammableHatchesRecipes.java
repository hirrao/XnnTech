package com.hirrao.xnntech.recipes.MachineRecipes;

import static com.hirrao.xnntech.config.CommonConfig.enableMEInputRecipes;
import static com.hirrao.xnntech.config.CommonConfig.enableProgrammableHatchesRecipes;
import static com.hirrao.xnntech.utils.Mods.ProgrammableHatches;
import static gregtech.api.enums.Mods.GregTech;
import static gregtech.api.util.GTRecipeBuilder.SECONDS;

import net.minecraft.item.ItemStack;

import gregtech.api.enums.GTValues;
import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gregtech.api.recipe.RecipeMaps;
import gregtech.api.util.GTModHandler;
import gtPlusPlus.api.recipe.GTPPRecipeMaps;

public class ProgrammableHatchesRecipes {

    public static void loadRecipes() {
        if (ProgrammableHatches.isModLoaded() && enableProgrammableHatchesRecipes && enableMEInputRecipes) {
            // TODO
            final ItemStack PROGRAMMING_COVER = GTModHandler
                .getModItem(ProgrammableHatches.ID, "proghatches.cover", 1L, 0);
            final ItemStack PROGRAMMABLE_CRAFTINGINPUT_BUFFER = GTModHandler
                .getModItem(GregTech.ID, "gt.blockmachines", 1, 22069);
            // 编程样板输入总成配方
            GTValues.RA.stdBuilder()
                .itemInputs(ItemList.Hatch_CraftingInput_Bus_ME.get(1), PROGRAMMING_COVER)
                .fluidInputs(Materials.AdvancedGlue.getFluid(4000))
                .itemOutputs(PROGRAMMABLE_CRAFTINGINPUT_BUFFER)
                .duration(20 * SECONDS)
                .eut(7680)
                .addTo(GTPPRecipeMaps.mixerNonCellRecipes);
            GTValues.RA.stdBuilder()
                .itemInputs(ItemList.Hatch_CraftingInput_Bus_ME.get(1), PROGRAMMING_COVER)
                .fluidInputs(Materials.AdvancedGlue.getFluid(4000))
                .itemOutputs(PROGRAMMABLE_CRAFTINGINPUT_BUFFER)
                .duration(20 * SECONDS)
                .eut(7680)
                .addTo(RecipeMaps.mixerRecipes);
        }
    }
}
