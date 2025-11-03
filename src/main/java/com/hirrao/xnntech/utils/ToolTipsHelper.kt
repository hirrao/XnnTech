package com.hirrao.xnntech.utils

import gregtech.api.util.MultiblockTooltipBuilder
import net.minecraft.util.StatCollector

private val ANY_CASING = StatCollector.translateToLocal("xnntech.gui.any_casing")

fun MultiblockTooltipBuilder.addInputHatchNormal(): MultiblockTooltipBuilder = addInputHatch(ANY_CASING)

fun MultiblockTooltipBuilder.addOutPutHatchNormal(): MultiblockTooltipBuilder = addOutputHatch(ANY_CASING)

fun MultiblockTooltipBuilder.addHatchesNormal(): MultiblockTooltipBuilder = addInputHatchNormal().addOutPutHatchNormal()

fun MultiblockTooltipBuilder.addInputBusNormal(): MultiblockTooltipBuilder = addInputBus(ANY_CASING)

fun MultiblockTooltipBuilder.addOutputBusNormal(): MultiblockTooltipBuilder = addOutputBus(ANY_CASING)

fun MultiblockTooltipBuilder.addBusesNormal(): MultiblockTooltipBuilder = addInputBusNormal().addOutputBusNormal()

fun MultiblockTooltipBuilder.addMaintenanceNormal(): MultiblockTooltipBuilder = addMaintenanceHatch(ANY_CASING)

fun MultiblockTooltipBuilder.addEnergyHatchNormal(): MultiblockTooltipBuilder = addEnergyHatch(ANY_CASING)

fun MultiblockTooltipBuilder.addMufflerHatchNormal(): MultiblockTooltipBuilder = addMufflerHatch(ANY_CASING)

fun MultiblockTooltipBuilder.addAllHatchesNormal(): MultiblockTooltipBuilder =
    addHatchesNormal().addBusesNormal().addMaintenanceNormal().addEnergyHatchNormal()

