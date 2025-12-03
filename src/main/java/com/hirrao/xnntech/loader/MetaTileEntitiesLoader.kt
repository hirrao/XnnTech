package com.hirrao.xnntech.loader

import com.hirrao.xnntech.api.enums.ItemLists
import com.hirrao.xnntech.api.enums.MetaTileEntityIDs
import com.hirrao.xnntech.common.machines.multi.MTECokeOven
import com.hirrao.xnntech.common.machines.multi.MTEMultiFluidSolidifier
import com.hirrao.xnntech.utils.Log
import com.hirrao.xnntech.utils.tr
import net.minecraft.util.StatCollector

private fun registerMultiblockControllers() {
    ItemLists.CokeOven.set(
        MTECokeOven(
            MetaTileEntityIDs.COKE_OVEN_CONTROLLER.id,
            "cokeoven",
            tr("xnntech.coke_oven.name")
        ).getStackForm(1L)
    )
    ItemLists.MultiFluidSolidifier.set(
        MTEMultiFluidSolidifier(
            MetaTileEntityIDs.MULTI_FLUID_SOLIDIFIER_CONTROLLER.id,
            "multifluidsolidifier",
            tr("xnntech,multi_fluid_solidifier.name")
        ).getStackForm(1L)
    )
}

fun loadMetaTileEntities() {
    Log.info("Loading MetaTileEntities")
    registerMultiblockControllers()
}
