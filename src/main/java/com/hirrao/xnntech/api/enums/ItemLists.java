package com.hirrao.xnntech.api.enums;

import static com.hirrao.xnntech.utils.Log.LOG;

import net.minecraft.item.ItemStack;

import gregtech.api.enums.ItemList;
import gregtech.api.util.GTUtility;

public enum ItemLists {

    CokeOven,
    MultiFluidSolidifier;

    private ItemStack mStack;
    private boolean mHasNotBeenSet;

    public void set(ItemStack aStack) {
        mHasNotBeenSet = false;
        mStack = GTUtility.copyAmount(1, aStack);
    }

    public ItemStack get(int aAmount) {
        sanityCheck();
        if (GTUtility.isStackInvalid(mStack)) {
            LOG.error("Object in the ItemList is null, {}",this.name());
            return GTUtility.copyAmount(aAmount, ItemList.AcceleratorLV.get(1));
        }
        return GTUtility.copyAmount(aAmount, mStack);
    }

    private void sanityCheck() {
        if (mHasNotBeenSet)
            throw new IllegalAccessError("The Enum '" + name() + "' has not been set to an Item at this time!");
    }
}
