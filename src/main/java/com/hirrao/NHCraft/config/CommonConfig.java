package com.hirrao.NHCraft.config;

import static com.hirrao.NHCraft.main.Info.MODID;

import com.gtnewhorizon.gtnhlib.config.Config;

@Config(modid = MODID, filename = "NHCraft")
public class CommonConfig {

    @Config.Comment("是否启用ME输入相关配方修改")
    @Config.DefaultBoolean(true)
    @Config.RequiresMcRestart
    public static boolean enableMEInputRecipes;

    @Config.Comment("是否启用可编程仓室总成修改(需打开ME输入修改)")
    @Config.DefaultBoolean(false)
    @Config.RequiresMcRestart
    public static boolean enableProgrammableHatchesRecipes;
}
