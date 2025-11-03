package com.hirrao.xnntech.main;

import com.hirrao.xnntech.loader.ConfigLoader;
import com.hirrao.xnntech.loader.MetaTileEntitiesLoader;
import com.hirrao.xnntech.loader.RecipesLoader;

import cpw.mods.fml.common.event.*;

@SuppressWarnings("unused")
public class CommonProxy {

    // preInit "Run before anything else. Read your config, create blocks, items, etc, and register them with the
    // GameRegistry." (Remove if not needed)
    public void preInit(FMLPreInitializationEvent event) {
        ConfigLoader.load();
        MetaTileEntitiesLoader.load();
    }

    // load "Do your mod setup. Build whatever data structures you care about. Register recipes." (Remove if not needed)
    public void init(FMLInitializationEvent event) {
        RecipesLoader.load();
    }

    // postInit "Handle interaction with other mods, complete your setup based on this." (Remove if not needed)
    public void postInit(FMLPostInitializationEvent event) {}

    public void CompleteInit(FMLLoadCompleteEvent event) {

    }

    // register server commands in this event handler (Remove if not needed)
    public void serverStarting(FMLServerStartingEvent event) {}
}
