package com.hirrao.NHCraft.loader;

import com.hirrao.NHCraft.recipes.CommonRecipes.CommonRecipes;
import com.hirrao.NHCraft.recipes.IRecipePoll;
import com.hirrao.NHCraft.recipes.MachineRecipes.MEInputRecipes;
import com.hirrao.NHCraft.recipes.MachineRecipes.ProgrammableHatchesRecipes;

public class RecipesLoader {

    public static void loadRecipes() {
        IRecipePoll[] craftRecipesPoll = { new CommonRecipes(), new MEInputRecipes(),
            new ProgrammableHatchesRecipes() };
        for (IRecipePoll recipePoll : craftRecipesPoll) {
            recipePoll.loadRecipes();
        }

    }

}
