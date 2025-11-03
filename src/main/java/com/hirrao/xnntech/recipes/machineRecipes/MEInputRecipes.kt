package com.hirrao.xnntech.recipes.machineRecipes

import com.hirrao.xnntech.config.CommonConfig
import gregtech.api.enums.GTValues
import gregtech.api.enums.ItemList
import gregtech.api.enums.Mods
import gregtech.api.recipe.RecipeMaps
import gregtech.api.util.GTModHandler
import gregtech.api.util.GTRecipeBuilder
import gregtech.api.util.GTUtility
import net.minecraft.item.ItemStack

val FLUID_INTERFACE: ItemStack? = GTModHandler.getModItem(Mods.AE2FluidCraft.ID, "fluid_interface", 1)
val ACCELERATION_CARD_4: ItemStack? =
    GTModHandler.getModItem(Mods.AppliedEnergistics2.ID, "item.ItemMultiMaterial", 4, 30)
val WIRELESS_KIT_2: ItemStack? = GTModHandler.getModItem(Mods.AE2Stuff.ID, "Wireless", 2)

fun loadMEInputRecipes() {
    if (CommonConfig.enableMEInputRecipes) {
        GTValues.RA.stdBuilder().itemInputs(
                GTUtility.getIntegratedCircuit(1), ItemList.Hatch_Input_HV.get(1), FLUID_INTERFACE, ACCELERATION_CARD_4
            ).itemOutputs(ItemList.Hatch_Input_ME.get(1)).duration(15 * GTRecipeBuilder.SECONDS).eut(480)
            .addTo(RecipeMaps.assemblerRecipes)
        GTValues.RA.stdBuilder().itemInputs(
                GTUtility.getIntegratedCircuit(3),
                WIRELESS_KIT_2,
                ItemList.Hatch_Input_ME.get(1),
                ItemList.Hatch_Input_Bus_ME.get(1)
            ).itemOutputs(ItemList.Hatch_CraftingInput_Bus_Slave.get(1)).duration(15 * GTRecipeBuilder.SECONDS)
            .eut(7680).addTo(RecipeMaps.assemblerRecipes)
    }
}
