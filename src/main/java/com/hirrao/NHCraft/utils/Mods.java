package com.hirrao.NHCraft.utils;

import cpw.mods.fml.common.Loader;

public enum Mods {

    ProgrammableHatches(Names.PROGRAMMABLE_HATCHES);

    public static class Names {

        public static final String PROGRAMMABLE_HATCHES = "programmablehatches";
    }

    public final String ID;
    private Boolean modLoaded;

    Mods(String ID) {
        this.ID = ID;
    }

    public boolean isModLoaded() {
        if (this.modLoaded == null) {
            this.modLoaded = Loader.isModLoaded(ID);
        }
        return this.modLoaded;
    }
}
