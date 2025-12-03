package com.hirrao.xnntech.recipes.commonRecipes

import com.hirrao.xnntech.api.enums.ItemLists
import cpw.mods.fml.common.registry.GameRegistry
import gregtech.api.enums.ItemList


fun loadMTERecipes() {
    //热解炉
    GameRegistry.addShapelessRecipe(ItemLists.CokeOven.get(1), ItemList.PyrolyseOven.get(1))
    GameRegistry.addShapelessRecipe(ItemList.PyrolyseOven.get(1), ItemLists.CokeOven.get(1))
    //工业流体固化机
    GameRegistry.addShapelessRecipe(ItemLists.MultiFluidSolidifier.get(1), ItemList.Machine_Multi_Solidifier.get(1))
    GameRegistry.addShapelessRecipe(ItemList.Machine_Multi_Solidifier.get(1), ItemLists.MultiFluidSolidifier.get(1))
}
