package com.hirrao.xnntech.recipes.CommonRecipes;

import com.hirrao.xnntech.api.enums.ItemLists;

import cpw.mods.fml.common.registry.GameRegistry;
import gregtech.api.enums.ItemList;

public class MetaTileEntitiesCraftRecipes {

    public static void loadRecipes() {
        GameRegistry.addShapelessRecipe(ItemLists.CokeOven.get(1), ItemList.PyrolyseOven.get(1));
        GameRegistry.addShapelessRecipe(ItemList.PyrolyseOven.get(1), ItemLists.CokeOven.get(1));
    }
}
