package com.hirrao.xnntech.main

import com.hirrao.xnntech.loader.loadConfig
import com.hirrao.xnntech.loader.loadMetaTileEntities
import com.hirrao.xnntech.loader.loadRecipes
import cpw.mods.fml.common.event.*

@Suppress("unused")
open class CommonProxy {
    // preInit "Run before anything else. Read your config, create blocks, items, etc, and register them with the
    // GameRegistry." (Remove if not needed)
    fun preInit(event: FMLPreInitializationEvent) {
        loadConfig()
        loadMetaTileEntities()
    }

    // load "Do your mod setup. Build whatever data structures you care about. Register recipes." (Remove if not needed)
    fun init(event: FMLInitializationEvent) {
        loadRecipes()
    }

    // postInit "Handle interaction with other mods, complete your setup based on this." (Remove if not needed)
    fun postInit(event: FMLPostInitializationEvent) {}

    fun completeInit(event: FMLLoadCompleteEvent) {
    }

    // register server commands in this event handler (Remove if not needed)
    fun serverStarting(event: FMLServerStartingEvent) {}
}
