package com.hirrao.NHCraft.recipes.CommonRecipes;

import static com.hirrao.NHCraft.config.Config.enableAE2Recipes;

import com.hirrao.NHCraft.recipes.IRecipePoll;

import cpw.mods.fml.common.registry.GameRegistry;
import gregtech.api.enums.ItemList;

public class CommonRecipes implements IRecipePoll {

    public void loadRecipes() {
        if (enableAE2Recipes) {
            GameRegistry.addShapelessRecipe(
                ItemList.Hatch_CraftingInput_Bus_ME.get(1),
                ItemList.Hatch_Input_ME.get(1),
                ItemList.Hatch_Input_Bus_ME.get(1));
        }
    }
}
