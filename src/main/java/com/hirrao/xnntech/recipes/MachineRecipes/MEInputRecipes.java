package com.hirrao.xnntech.recipes.MachineRecipes;

import static com.hirrao.xnntech.config.CommonConfig.enableMEInputRecipes;
import static gregtech.api.enums.Mods.*;
import static gregtech.api.util.GTRecipeBuilder.SECONDS;

import net.minecraft.item.ItemStack;

import gregtech.api.enums.GTValues;
import gregtech.api.enums.ItemList;
import gregtech.api.recipe.RecipeMaps;
import gregtech.api.util.GTModHandler;
import gregtech.api.util.GTUtility;

public class MEInputRecipes {

    public static final ItemStack FLUID_INTERFACE = GTModHandler.getModItem(AE2FluidCraft.ID, "fluid_interface", 1);
    public static final ItemStack ACCELERATION_CARD_4 = GTModHandler
        .getModItem(AppliedEnergistics2.ID, "item.ItemMultiMaterial", 4, 30);
    public static final ItemStack WIRELESS_KIT_2 = GTModHandler.getModItem(AE2Stuff.ID, "Wireless", 2);

    public static void loadRecipes() {
        if (enableMEInputRecipes) {
            GTValues.RA.stdBuilder()
                .itemInputs(
                    GTUtility.getIntegratedCircuit(1),
                    ItemList.Hatch_Input_HV.get(1),
                    FLUID_INTERFACE,
                    ACCELERATION_CARD_4)
                .itemOutputs(ItemList.Hatch_Input_ME.get(1))
                .duration(15 * SECONDS)
                .eut(480)
                .addTo(RecipeMaps.assemblerRecipes);
            GTValues.RA.stdBuilder()
                .itemInputs(
                    GTUtility.getIntegratedCircuit(3),
                    WIRELESS_KIT_2,
                    ItemList.Hatch_Input_ME.get(1),
                    ItemList.Hatch_Input_Bus_ME.get(1))
                .itemOutputs(ItemList.Hatch_CraftingInput_Bus_Slave.get(1))
                .duration(15 * SECONDS)
                .eut(7680)
                .addTo(RecipeMaps.assemblerRecipes);
        }

    }

}
