package com.hirrao.xnntech.api.enums

import com.hirrao.xnntech.utils.Log
import gregtech.api.enums.ItemList
import gregtech.api.util.GTUtility
import net.minecraft.item.ItemStack

enum class ItemLists {
    CokeOven,
    MultiFluidSolidifier;

    private lateinit var mStack: ItemStack
    private var mHasNotBeenSet = false

    fun set(aStack: ItemStack) {
        mHasNotBeenSet = false
        mStack = GTUtility.copyAmount(1, aStack)
    }

    fun get(aAmount: Int): ItemStack {
        if (GTUtility.isStackInvalid(mStack)) {
            Log.error("Object in the ItemList is null,${this.name}")
            return GTUtility.copyAmount(aAmount, ItemList.AcceleratorLV.get(1))
        }
        return GTUtility.copyAmount(aAmount, mStack)
    }
}
