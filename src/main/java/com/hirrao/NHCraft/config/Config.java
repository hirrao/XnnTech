package com.hirrao.NHCraft.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class Config {

    public static boolean enableAE2Recipes = true;

    public static void synchronizeConfiguration(File configFile) {
        Configuration configuration = new Configuration(configFile);
        enableAE2Recipes = configuration
            .getBoolean("enableAE2Recipes", Configuration.CATEGORY_GENERAL, enableAE2Recipes, "是否启用AE2相关配方修改");

        if (configuration.hasChanged()) {
            configuration.save();
        }
    }
}
