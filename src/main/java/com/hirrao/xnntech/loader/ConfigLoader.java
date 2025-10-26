package com.hirrao.xnntech.loader;

import com.gtnewhorizon.gtnhlib.config.ConfigException;
import com.gtnewhorizon.gtnhlib.config.ConfigurationManager;
import com.hirrao.xnntech.config.CommonConfig;
import com.hirrao.xnntech.utils.Log;

public class ConfigLoader {

    public static void load() {
        try {
            ConfigurationManager.registerConfig(CommonConfig.class);
            Log.info("Registering Config");
        } catch (ConfigException e) {
            throw new RuntimeException(e);
        }
    }
}
