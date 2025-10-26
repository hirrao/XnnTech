package com.hirrao.xnntech.loader;

import com.hirrao.xnntech.recipes.CommonRecipes.CommonRecipes;
import com.hirrao.xnntech.recipes.CommonRecipes.MetaTileEntitiesCraftRecipes;
import com.hirrao.xnntech.recipes.MachineRecipes.MEInputRecipes;
import com.hirrao.xnntech.recipes.MachineRecipes.ProgrammableHatchesRecipes;
import com.hirrao.xnntech.utils.Log;

public class RecipesLoader {

    public static void load() {
        Log.info("Loading Recipes");
        CommonRecipes.loadRecipes();
        MEInputRecipes.loadRecipes();
        ProgrammableHatchesRecipes.loadRecipes();
        MetaTileEntitiesCraftRecipes.loadRecipes();
    }

}
