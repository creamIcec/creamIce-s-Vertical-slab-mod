package com.verticalSlab.creamIce.common;

import com.verticalSlab.creamIce.verticalSlab.VerticalSlabBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;
import net.minecraft.block.IWaterLoggable;

public class BlackBoardStand extends VerticalSlabBlock implements IWaterLoggable {
    public BlackBoardStand(){
        super(AbstractBlock.Properties.copy(Blocks.STONE));
    }
}
