package com.hirrao.xnntech.recipes.commonRecipes

import com.hirrao.xnntech.config.CommonConfig
import cpw.mods.fml.common.registry.GameRegistry
import gregtech.api.enums.ItemList


fun loadCommonRecipes() {
    if (CommonConfig.enableMEInputRecipes) {
        //样板输入总成
        GameRegistry.addShapelessRecipe(
            ItemList.Hatch_CraftingInput_Bus_ME.get(1),
            ItemList.Hatch_Input_ME.get(1),
            ItemList.Hatch_Input_Bus_ME.get(1)
        )
    }
}

