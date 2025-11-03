package com.hirrao.xnntech.main

import com.hirrao.xnntech.Tags
import cpw.mods.fml.common.Mod
import cpw.mods.fml.common.SidedProxy
import cpw.mods.fml.common.event.*

@Mod(
    modid = Info.MODID,
    version = Tags.VERSION,
    modLanguageAdapter = "net.shadowfacts.forgelin.KotlinAdapter",
    name = Info.NAME,
    acceptedMinecraftVersions = "[1.7.10]",
    dependencies = Info.DEPENDENCIES
)
@SuppressWarnings("unused")
object Xnntech {
    @Mod.EventHandler // preInit "Run before anything else. Read your config, create blocks, items, etc, and register them with the
    // GameRegistry." (Remove if not needed)
    fun preInit(event: FMLPreInitializationEvent) = proxy.preInit(event)

    @Mod.EventHandler // load "Do your mod setup. Build whatever data structures you care about. Register recipes." (Remove if not needed)
    fun init(event: FMLInitializationEvent) = proxy.init(event)

    @Mod.EventHandler // postInit "Handle interaction with other mods, complete your setup based on this." (Remove if not needed)
    fun postInit(event: FMLPostInitializationEvent) = proxy.postInit(event)

    @Mod.EventHandler
    fun completeInit(event: FMLLoadCompleteEvent) = proxy.completeInit(event)

    @Mod.EventHandler // register server commands in this event handler (Remove if not needed)
    fun serverStarting(event: FMLServerStartingEvent) = proxy.serverStarting(event)

    @SidedProxy(clientSide = Info.CLIENT_SIDE, serverSide = Info.SERVER_SIDE)
    lateinit var proxy: CommonProxy

}
