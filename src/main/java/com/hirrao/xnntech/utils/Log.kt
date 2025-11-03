package com.hirrao.xnntech.utils

import com.hirrao.xnntech.main.Info.NAME
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

object Log {
    private val LOG: Logger = LogManager.getLogger(NAME)

    fun error(s: String, vararg objects: Any?) = LOG.error(s, *objects)


    fun info(s: String, vararg objects: Any?) = LOG.info(s, *objects)


    fun warn(s: String, vararg objects: Any?) = LOG.warn(s, *objects)

}
