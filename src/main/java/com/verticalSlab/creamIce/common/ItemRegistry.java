package com.verticalSlab.creamIce.common;


import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "creamiceverticalslab");
    public static  RegistryObject<Item> black_board_stand = ITEMS.register("black_board_stand",()->{
        return new BlockItem(BlockRegistry.black_board_stand.get(),new Item.Properties().tab(VerticalSlabGroup.TAB_VERTICAL_SLAB));
    });
    public static  RegistryObject<Item> vertical_spruce_slab = ITEMS.register("vertical_spruce_slab",()->{
        return new BlockItem(BlockRegistry.vertical_spruce_slab.get(),new Item.Properties().tab(VerticalSlabGroup.TAB_VERTICAL_SLAB));
    });
}