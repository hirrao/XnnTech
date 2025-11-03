package com.hirrao.xnntech.loader

import com.gtnewhorizon.gtnhlib.config.ConfigException
import com.gtnewhorizon.gtnhlib.config.ConfigurationManager
import com.hirrao.xnntech.config.CommonConfig
import com.hirrao.xnntech.utils.Log

fun loadConfig() {
    try {
        ConfigurationManager.registerConfig(CommonConfig::class.java)
        Log.info("Registering Config")
    } catch (e: ConfigException) {
        throw RuntimeException(e)
    }
}

