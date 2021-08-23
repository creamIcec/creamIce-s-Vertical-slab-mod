package com.verticalSlab.creamIce.slabtype;

import net.minecraft.state.EnumProperty;
import net.minecraft.state.properties.BlockStateProperties;

public class SlabDirectionTypeProperties extends BlockStateProperties {
    public static final EnumProperty<SlabDirectionType> SLAB_TYPE = EnumProperty.create("type", SlabDirectionType.class);
}
