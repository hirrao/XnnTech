package com.hirrao.xnntech.loader;

import static com.hirrao.xnntech.api.enums.MetaTileEntityIDs.*;
import static com.hirrao.xnntech.utils.Log.LOG;

import net.minecraft.util.StatCollector;

import com.hirrao.xnntech.api.enums.ItemLists;
import com.hirrao.xnntech.common.machines.multi.MTECokeOven;
import com.hirrao.xnntech.common.machines.multi.MTEMultiFluidSolidifier;

public class MetaTileEntitiesLoader {

    private static void registerMultiblockControllers() {
        ItemLists.CokeOven.set(
            new MTECokeOven(
                COKE_OVEN_CONTROLLER.ID,
                "cokeoven",
                StatCollector.translateToLocal("xnntech.coke_oven.name")).getStackForm(1L));
        ItemLists.MultiFluidSolidifier.set(
            new MTEMultiFluidSolidifier(
                MULTI_FLUID_SOLIDIFIER_CONTROLLER.ID,
                "multifluidsolidifier",
                StatCollector.translateToLocal("xnntech,multi_fluid_solidifier.name")).getStackForm(1L));
    }

    public static void load() {
        LOG.info("Loading MetaTileEntities");
        registerMultiblockControllers();
    }
}
