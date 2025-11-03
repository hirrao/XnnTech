package com.hirrao.xnntech.utils;

import static com.hirrao.xnntech.main.Info.NAME;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Log {

    private static final Logger LOG = LogManager.getLogger(NAME);

    public static void error(String s, Object... objects) {
        LOG.error(s, objects);
    }

    public static void info(String s, Object... objects) {
        LOG.info(s, objects);
    }

    public static void warn(String s, Object... objects) {
        LOG.warn(s, objects);
    }
}
