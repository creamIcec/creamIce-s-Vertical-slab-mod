package com.verticalSlab.creamIce.verticalSlab;

import com.verticalSlab.creamIce.slabtype.SlabDirectionType;
import com.verticalSlab.creamIce.slabtype.SlabDirectionTypeProperties;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;

public class VerticalSlabBlock extends Block implements IWaterLoggable {
    public static final EnumProperty<SlabDirectionType> TYPE = SlabDirectionTypeProperties.SLAB_TYPE;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    private static final VoxelShape BASE_NORTH = Block.box(16.0D,0.0D,16.0D,0.0D,16.0D,8.0D);
    private static final VoxelShape BASE_SOUTH = Block.box(0.0D,0.0D,0.0D,16.0D,16.0D,8.0D);
    private static final VoxelShape BASE_WEST = Block.box(16.0D,0.0D,0.0D,8.0D,16.0D,16.0D);
    private static final VoxelShape BASE_EAST = Block.box(0.0D,0.0D,0.0D,8.0D,16.0D,16.0D);
    private static final VoxelShape FULL = Block.box(0.0D,0.0D,0.0D,16.0D,16.0D,16.0D);
    public VerticalSlabBlock(Properties properties){
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(TYPE, SlabDirectionType.NORTH)
                .setValue(WATERLOGGED,false));
    }
    public BlockState getStateForPlacement(BlockItemUseContext PlacingDirection) {
        BlockState state = PlacingDirection.getLevel().getBlockState(PlacingDirection.getClickedPos());
        BlockPos blockpos = PlacingDirection.getClickedPos();
        SlabDirectionType direction;
        FluidState fluidstate = PlacingDirection.getLevel().getFluidState(blockpos);
        boolean flagLocationZ = PlacingDirection.getClickLocation().z >= 0.5;
        boolean flagLocationX = PlacingDirection.getClickLocation().x >= 0.5;
        if (state.is(this)){
            direction = state.getValue(TYPE);
            if(direction == SlabDirectionType.NORTH && (flagLocationZ)){
                return this.defaultBlockState()
                        .setValue(TYPE,SlabDirectionType.FULL).setValue(WATERLOGGED, false);
            }else if(direction == SlabDirectionType.SOUTH && (flagLocationZ)){
                return this.defaultBlockState()
                        .setValue(TYPE,SlabDirectionType.FULL).setValue(WATERLOGGED, false);
            }else if(direction == SlabDirectionType.WEST && (flagLocationX)) {
                return this.defaultBlockState()
                        .setValue(TYPE,SlabDirectionType.FULL).setValue(WATERLOGGED, false);
            }else if(direction == SlabDirectionType.EAST && (flagLocationX)) {
                return this.defaultBlockState()
                        .setValue(TYPE,SlabDirectionType.FULL).setValue(WATERLOGGED, false);
            }
        }else {
            return this.defaultBlockState()
                    .setValue(TYPE, MappingDirection.MappingDirection(PlacingDirection.getHorizontalDirection().getOpposite()))
                    .setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
        }
        return this.defaultBlockState()
                .setValue(TYPE, SlabDirectionType.NORTH)
                .setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
    }
    public boolean canBeReplaced(BlockState p_196253_1_, BlockItemUseContext p_196253_2_) {
        ItemStack itemstack = p_196253_2_.getItemInHand();
        SlabDirectionType isFull = p_196253_1_.getValue(TYPE);
        if (isFull != SlabDirectionType.FULL && itemstack.getItem() == this.asItem()) {
            boolean flagEast= p_196253_2_.getClickLocation().x - (double)p_196253_2_.getClickedPos().getX() <= 0.5D;
            boolean flagSouth = p_196253_2_.getClickLocation().z - (double)p_196253_2_.getClickedPos().getZ() <= 0.5D;
            boolean flagWest= p_196253_2_.getClickLocation().x - (double)p_196253_2_.getClickedPos().getX() >= 0.5D;
            boolean flagNorth = p_196253_2_.getClickLocation().z - (double)p_196253_2_.getClickedPos().getZ() >= 0.5D;
            if (isFull == SlabDirectionType.NORTH && flagSouth) {
                return true;
            } else if(isFull == SlabDirectionType.SOUTH && flagNorth){
                return true;
            }else if(isFull == SlabDirectionType.WEST && flagEast){
                return true;
            }else return isFull == SlabDirectionType.EAST && flagWest;
        } else {
            return false;
        }
    }
    public FluidState getFluidState(BlockState p_204507_1_) {
        return p_204507_1_.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(p_204507_1_);
    }
    public boolean placeLiquid(IWorld p_204509_1_, BlockPos p_204509_2_, BlockState p_204509_3_, FluidState p_204509_4_) {
        return p_204509_3_.getValue(TYPE) != SlabDirectionType.FULL && IWaterLoggable.super.placeLiquid(p_204509_1_, p_204509_2_, p_204509_3_, p_204509_4_);
    }

    public boolean canPlaceLiquid(IBlockReader p_204510_1_, BlockPos p_204510_2_, BlockState p_204510_3_, Fluid p_204510_4_) {
        return p_204510_3_.getValue(TYPE) != SlabDirectionType.FULL && IWaterLoggable.super.canPlaceLiquid(p_204510_1_, p_204510_2_, p_204510_3_, p_204510_4_);
    }

    public BlockState updateShape(BlockState p_196271_1_, Direction p_196271_2_, BlockState p_196271_3_, IWorld p_196271_4_, BlockPos p_196271_5_, BlockPos p_196271_6_) {
        if (p_196271_1_.getValue(WATERLOGGED)) {
            p_196271_4_.getLiquidTicks().scheduleTick(p_196271_5_, Fluids.WATER, Fluids.WATER.getTickDelay(p_196271_4_));
        }
        return super.updateShape(p_196271_1_, p_196271_2_, p_196271_3_, p_196271_4_, p_196271_5_, p_196271_6_);
    }
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> p_206840_1_) {
        p_206840_1_.add(TYPE);
        p_206840_1_.add(WATERLOGGED);
    }
    public boolean isPathfindable(BlockState p_196266_1_, IBlockReader p_196266_2_, BlockPos p_196266_3_, PathType p_196266_4_) {
        switch(p_196266_4_) {
            case WATER:
                return p_196266_2_.getFluidState(p_196266_3_).is(FluidTags.WATER);
            case LAND:
            case AIR:
            default:
                return false;
        }
    }
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context){
        SlabDirectionType direction = state.getValue(TYPE);
        switch(direction){
            case NORTH:
                return BASE_NORTH;
            case SOUTH:
                return BASE_SOUTH;
            case WEST:
                return BASE_WEST;
            case EAST:
                return BASE_EAST;
            case FULL:
                return FULL;
        }
        return BASE_NORTH;
    }
}
