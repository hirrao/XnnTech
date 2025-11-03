package com.hirrao.xnntech.config

import com.gtnewhorizon.gtnhlib.config.Config
import com.gtnewhorizon.gtnhlib.config.Config.DefaultBoolean
import com.gtnewhorizon.gtnhlib.config.Config.RequiresMcRestart
import com.hirrao.xnntech.main.Info.MODID

@Config(modid = MODID, filename = "XnnTech")
class CommonConfig {
    companion object{
        @Config.Comment("是否启用ME输入相关配方修改")
        @DefaultBoolean(true)
        @RequiresMcRestart
        var enableMEInputRecipes: Boolean = false

        @Config.Comment("是否启用可编程仓室总成修改(需打开ME输入修改)")
        @DefaultBoolean(false)
        @RequiresMcRestart
        var enableProgrammableHatchesRecipes: Boolean = false
    }

}
