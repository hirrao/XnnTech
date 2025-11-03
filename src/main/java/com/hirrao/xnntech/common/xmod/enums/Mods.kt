package com.hirrao.xnntech.common.xmod.enums

import cpw.mods.fml.common.Loader

enum class Mods(val id: String) {
    ProgrammableHatches(Names.PROGRAMMABLE_HATCHES);

    var isModLoaded: Boolean? = null
        get() {
            if (field == null) {
                field = Loader.isModLoaded(id)
            }
            return field
        }
        private set

    object Names {
        const val PROGRAMMABLE_HATCHES: String = "programmablehatches"
    }
}
