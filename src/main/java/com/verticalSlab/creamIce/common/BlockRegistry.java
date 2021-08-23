package com.verticalSlab.creamIce.common;

import com.verticalSlab.creamIce.verticalSlab.VerticalSlabBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockRegistry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, "creamiceverticalslab");
    public static RegistryObject<Block> black_board_stand = BLOCKS.register("black_board_stand", BlackBoardStand::new);
    public static RegistryObject<Block> vertical_spruce_slab = BLOCKS.register("vertical_spruce_slab", () -> new VerticalSlabBlock(Block.Properties.copy(Blocks.SPRUCE_PLANKS)));
}
