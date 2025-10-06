package com.hirrao.xnntech.loader;

import static com.hirrao.xnntech.utils.Log.LOG;

import com.gtnewhorizon.gtnhlib.config.ConfigException;
import com.gtnewhorizon.gtnhlib.config.ConfigurationManager;
import com.hirrao.xnntech.config.CommonConfig;

public class ConfigLoader {

    public static void load() {
        try {
            ConfigurationManager.registerConfig(CommonConfig.class);
            LOG.info("Registering Config");
        } catch (ConfigException e) {
            throw new RuntimeException(e);
        }
    }
}
