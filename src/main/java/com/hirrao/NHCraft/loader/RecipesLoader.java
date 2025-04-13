package com.hirrao.NHCraft.loader;

import com.hirrao.NHCraft.recipes.CommonRecipes.CommonRecipes;
import com.hirrao.NHCraft.recipes.MachineRecipes.MEInputRecipes;
import com.hirrao.NHCraft.recipes.MachineRecipes.ProgrammableHatchesRecipes;

public class RecipesLoader {

    public static void loadRecipes() {
        CommonRecipes.loadRecipes();
        MEInputRecipes.loadRecipes();
        ProgrammableHatchesRecipes.loadRecipes();
    }

}
