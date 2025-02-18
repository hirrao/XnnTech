package com.hirrao.NHCraft.recipes.MachineRecipes;

import static com.hirrao.NHCraft.config.CommonConfig.enableMEInputRecipes;
import static gregtech.api.enums.Mods.AE2FluidCraft;
import static gregtech.api.enums.Mods.AppliedEnergistics2;
import static gregtech.api.util.GTRecipeBuilder.SECONDS;

import net.minecraft.item.ItemStack;

import com.hirrao.NHCraft.recipes.IRecipePoll;

import gregtech.api.enums.GTValues;
import gregtech.api.enums.ItemList;
import gregtech.api.recipe.RecipeMaps;
import gregtech.api.util.GTModHandler;
import gregtech.api.util.GTUtility;

public class MEInputRecipes implements IRecipePoll {

    public static final ItemStack FLUID_INTERFACE = GTModHandler.getModItem(AE2FluidCraft.ID, "fluid_interface", 1);
    public static final ItemStack PART_FLUID_INTERFACE = GTModHandler
        .getModItem(AE2FluidCraft.ID, "part_fluid_interface", 1);
    public static final ItemStack ACCELERATION_CARD_4 = GTModHandler
        .getModItem(AppliedEnergistics2.ID, "item.ItemMultiMaterial", 4, 30);

    @Override
    public void loadRecipes() {
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
                    GTUtility.getIntegratedCircuit(1),
                    ItemList.Hatch_Input_HV.get(1),
                    PART_FLUID_INTERFACE,
                    ACCELERATION_CARD_4)
                .itemOutputs(ItemList.Hatch_Input_ME.get(1))
                .duration(15 * SECONDS)
                .eut(480)
                .addTo(RecipeMaps.assemblerRecipes);
        }

    }

}
