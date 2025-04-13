package com.hirrao.NHCraft.recipes.CommonRecipes;

import static com.hirrao.NHCraft.config.CommonConfig.enableMEInputRecipes;

import cpw.mods.fml.common.registry.GameRegistry;
import gregtech.api.enums.ItemList;

public class CommonRecipes {

    public static void loadRecipes() {
        if (enableMEInputRecipes) {
            GameRegistry.addShapelessRecipe(
                ItemList.Hatch_CraftingInput_Bus_ME.get(1),
                ItemList.Hatch_Input_ME.get(1),
                ItemList.Hatch_Input_Bus_ME.get(1));
        }
    }
}
