package com.verticalSlab.creamIce.slabtype;

import net.minecraft.util.Direction;
import net.minecraft.util.IStringSerializable;

public enum SlabDirectionType implements IStringSerializable {
    NORTH("north"),
    SOUTH("south"),
    EAST("east"),
    WEST("west"),
    FULL("full");

    private final String name;

    private SlabDirectionType(String p_i49332_3_) {
        this.name = p_i49332_3_;
    }

    public String toString() {
        return this.name;
    }

    public String getSerializedName() {
        return this.name;
    }
}
