package com.verticalSlab.creamIce;

import com.verticalSlab.creamIce.common.BlockRegistry;
import com.verticalSlab.creamIce.common.ItemRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("creamiceverticalslab")

public class VerticalSlab {
    public VerticalSlab() {
        BlockRegistry.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ItemRegistry.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}