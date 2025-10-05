package com.hirrao.xnntech.api.enums;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import gregtech.api.util.GTUtility;

public enum ItemList {

    CokeOven;

    private ItemStack mStack;
    private boolean mHasNotBeenSet;

    public ItemList set(Item aItem) {
        mHasNotBeenSet = false;
        if (aItem == null) return this;
        ItemStack aStack = new ItemStack(aItem, 1, 0);
        mStack = GTUtility.copyAmount(1, aStack);
        return this;
    }

    public ItemList set(ItemStack aStack) {
        mHasNotBeenSet = false;
        mStack = GTUtility.copyAmount(1, aStack);
        return this;
    }
}
