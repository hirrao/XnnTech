package com.hirrao.xnntech.recipes.machineRecipes

import com.hirrao.xnntech.config.CommonConfig
import com.hirrao.xnntech.common.xmod.enums.Mods
import gregtech.api.enums.GTValues
import gregtech.api.enums.ItemList
import gregtech.api.enums.Materials
import gregtech.api.recipe.RecipeMaps
import gregtech.api.util.GTModHandler
import gregtech.api.util.GTRecipeBuilder
import gtPlusPlus.api.recipe.GTPPRecipeMaps

fun loadProgramableHatchesRecipes() {
    if (Mods.ProgrammableHatches.isModLoaded == true && CommonConfig.enableProgrammableHatchesRecipes && CommonConfig.enableMEInputRecipes) {
        val PROGRAMMING_COVER = GTModHandler.getModItem(Mods.ProgrammableHatches.id, "proghatches.cover", 1L, 0)
        val PROGRAMMABLE_CRAFTINGINPUT_BUFFER =
            GTModHandler.getModItem(gregtech.api.enums.Mods.GregTech.ID, "gt.blockmachines", 1, 22069)
        // 编程样板输入总成配方
        GTValues.RA.stdBuilder().itemInputs(ItemList.Hatch_CraftingInput_Bus_ME.get(1), PROGRAMMING_COVER)
            .fluidInputs(Materials.AdvancedGlue.getFluid(4000)).itemOutputs(PROGRAMMABLE_CRAFTINGINPUT_BUFFER)
            .duration(20 * GTRecipeBuilder.SECONDS).eut(7680).addTo(GTPPRecipeMaps.mixerNonCellRecipes)
        GTValues.RA.stdBuilder().itemInputs(ItemList.Hatch_CraftingInput_Bus_ME.get(1), PROGRAMMING_COVER)
            .fluidInputs(Materials.AdvancedGlue.getFluid(4000)).itemOutputs(PROGRAMMABLE_CRAFTINGINPUT_BUFFER)
            .duration(20 * GTRecipeBuilder.SECONDS).eut(7680).addTo(RecipeMaps.mixerRecipes)
    }
}

