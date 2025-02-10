package com.hirrao.NHCraft.main;

import static com.hirrao.NHCraft.utils.Log.LOG;

import com.gtnewhorizon.gtnhlib.config.ConfigException;
import com.gtnewhorizon.gtnhlib.config.ConfigurationManager;
import com.hirrao.NHCraft.Tags;
import com.hirrao.NHCraft.config.CommonConfig;
import com.hirrao.NHCraft.loader.RecipesLoader;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLLoadCompleteEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

@SuppressWarnings("unused")
public class CommonProxy {

    // preInit "Run before anything else. Read your config, create blocks, items, etc, and register them with the
    // GameRegistry." (Remove if not needed)
    public void preInit(FMLPreInitializationEvent event) {
        LOG.info("Starting with Version " + Tags.VERSION);
        try {
            ConfigurationManager.registerConfig(CommonConfig.class);
        } catch (ConfigException e) {
            throw new RuntimeException(e);
        }
    }

    // load "Do your mod setup. Build whatever data structures you care about. Register recipes." (Remove if not needed)
    public void init(FMLInitializationEvent event) {}

    // postInit "Handle interaction with other mods, complete your setup based on this." (Remove if not needed)
    public void postInit(FMLPostInitializationEvent event) {}

    public void CompleteInit(FMLLoadCompleteEvent event) {
        RecipesLoader.loadRecipes();
    }

    // register server commands in this event handler (Remove if not needed)
    public void serverStarting(FMLServerStartingEvent event) {}
}
