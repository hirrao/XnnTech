package com.hirrao.xnntech.api.enums;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import gregtech.api.enums.ItemList;
import gregtech.api.util.GTLog;
import gregtech.api.util.GTUtility;

public enum ItemLists {

    CokeOven;

    private ItemStack mStack;
    private boolean mHasNotBeenSet;

    public ItemLists set(Item aItem) {
        mHasNotBeenSet = false;
        if (aItem == null) return this;
        ItemStack aStack = new ItemStack(aItem, 1, 0);
        mStack = GTUtility.copyAmount(1, aStack);
        return this;
    }

    public ItemLists set(ItemStack aStack) {
        mHasNotBeenSet = false;
        mStack = GTUtility.copyAmount(1, aStack);
        return this;
    }

    public ItemStack get(int aAmount) {
        sanityCheck();
        if (GTUtility.isStackInvalid(mStack)) {
            GTLog.out.println("Object in the ItemList is null at:");
            new NullPointerException().printStackTrace(GTLog.out);
            return GTUtility.copyAmount(aAmount, ItemList.AcceleratorLV.get(1));
        }
        return GTUtility.copyAmount(aAmount, mStack);
    }

    private void sanityCheck() {
        if (mHasNotBeenSet)
            throw new IllegalAccessError("The Enum '" + name() + "' has not been set to an Item at this time!");
    }
}
