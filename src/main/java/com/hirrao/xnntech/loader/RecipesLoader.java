package com.hirrao.xnntech.loader;

import com.hirrao.xnntech.recipes.CommonRecipes.CommonRecipes;
import com.hirrao.xnntech.recipes.MachineRecipes.MEInputRecipes;
import com.hirrao.xnntech.recipes.MachineRecipes.ProgrammableHatchesRecipes;

public class RecipesLoader {

    public static void load() {
        CommonRecipes.loadRecipes();
        MEInputRecipes.loadRecipes();
        ProgrammableHatchesRecipes.loadRecipes();
    }

}
