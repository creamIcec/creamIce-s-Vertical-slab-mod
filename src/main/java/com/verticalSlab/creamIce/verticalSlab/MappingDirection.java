package com.verticalSlab.creamIce.verticalSlab;

import com.verticalSlab.creamIce.slabtype.SlabDirectionType;
import com.google.common.collect.ImmutableMap;
import net.minecraft.util.Direction;

import java.util.Map;

public class MappingDirection {
    private static final Map<SlabDirectionType, Direction> directionToSlabDirection = new ImmutableMap.Builder<SlabDirectionType,Direction>()
            .put(SlabDirectionType.NORTH,Direction.NORTH)
            .put(SlabDirectionType.SOUTH,Direction.SOUTH)
            .put(SlabDirectionType.WEST,Direction.WEST)
            .put(SlabDirectionType.EAST,Direction.EAST)
            .build();
    public static SlabDirectionType MappingDirection(Direction direction){
        SlabDirectionType slabType;
        for(Map.Entry<SlabDirectionType,Direction> m : directionToSlabDirection.entrySet()){
            if(m.getValue().equals(direction)){
                slabType = m.getKey();
                return slabType;
            }
        }

        return  SlabDirectionType.NORTH;
    }
}
