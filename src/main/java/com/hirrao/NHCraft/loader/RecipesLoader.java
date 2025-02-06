package com.hirrao.NHCraft.loader;

import com.hirrao.NHCraft.recipes.CommonRecipes.CommonRecipes;
import com.hirrao.NHCraft.recipes.IRecipePoll;
import com.hirrao.NHCraft.recipes.MachineRecipes.AE2Recipes;

public class RecipesLoader {

    public static void loadRecipes() {
        IRecipePoll[] craftRecipesPoll = { new CommonRecipes(), new AE2Recipes() };
        for (IRecipePoll recipePoll : craftRecipesPoll) {
            recipePoll.loadRecipes();
        }

    }

}
