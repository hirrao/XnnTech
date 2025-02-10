package com.hirrao.NHCraft.recipes.MachineRecipes;

import static com.hirrao.NHCraft.config.CommonConfig.enableMEInputRecipes;
import static com.hirrao.NHCraft.config.CommonConfig.enableProgrammableHatchesRecipes;

import com.hirrao.NHCraft.recipes.IRecipePoll;

import cpw.mods.fml.common.Loader;

public class ProgrammableHatchesRecipes implements IRecipePoll {

    @Override
    public void loadRecipes() {
        if (Loader.isModLoaded("programablehatches") && enableProgrammableHatchesRecipes && enableMEInputRecipes) {
            // TODO
        }
    }
}
