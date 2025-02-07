package com.hirrao.NHCraft.config;

import static com.hirrao.NHCraft.main.Info.MODID;

import com.gtnewhorizon.gtnhlib.config.Config;

@Config(modid = MODID, filename = "NHCraft")
public class CommonConfig {

    @Config.Comment("是否启用AE2相关配方修改")
    @Config.DefaultBoolean(true)
    @Config.RequiresMcRestart
    public static boolean enableAE2Recipes;
}
