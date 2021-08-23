package com.verticalSlab.creamIce.common;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public abstract class VerticalSlabGroup {
    public static final ItemGroup TAB_VERTICAL_SLAB = new ItemGroup(-1, "VerticalSlab_group") {
        @OnlyIn(Dist.CLIENT)
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ItemRegistry.black_board_stand.get());
        }
    };
}

