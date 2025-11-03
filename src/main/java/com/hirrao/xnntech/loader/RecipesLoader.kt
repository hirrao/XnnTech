package com.hirrao.xnntech.loader

import com.hirrao.xnntech.recipes.commonRecipes.loadCommonRecipes
import com.hirrao.xnntech.recipes.commonRecipes.loadMTERecipes
import com.hirrao.xnntech.recipes.machineRecipes.loadMEInputRecipes
import com.hirrao.xnntech.recipes.machineRecipes.loadProgramableHatchesRecipes
import com.hirrao.xnntech.utils.Log

fun loadRecipes() {
    Log.info("Loading Recipes")
    loadCommonRecipes()
    loadMEInputRecipes()
    loadProgramableHatchesRecipes()
    loadMTERecipes()
}

