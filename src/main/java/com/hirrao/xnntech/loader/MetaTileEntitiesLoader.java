package com.hirrao.xnntech.loader;

import static com.hirrao.xnntech.api.enums.MetaTileEntityIDs.*;
import static com.hirrao.xnntech.utils.Log.LOG;

import net.minecraft.util.StatCollector;

import com.hirrao.xnntech.api.enums.ItemList;
import com.hirrao.xnntech.common.machines.MTECokeOven;

public class MetaTileEntitiesLoader {

    private static void registerMultiblockControllers() {
        ItemList.CokeOven.set(
            new MTECokeOven(
                COKE_OVEN_CONTROLLER.ID,
                "cokeoven",
                StatCollector.translateToLocal("xnntech.coke_oven.name")).getStackForm(1L));
    }

    public static void load() {
        LOG.info("Loading MetaTileEntities");
        registerMultiblockControllers();
    }
}
