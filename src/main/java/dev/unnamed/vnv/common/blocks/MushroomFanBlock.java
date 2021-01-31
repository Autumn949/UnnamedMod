package dev.unnamed.vnv.common.blocks;


import dev.unnamed.vnv.util.IsSolid;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;

import javax.annotation.Nullable;

public class MushroomFanBlock extends Block {
    public static final DirectionProperty DIRECTION;
    public MushroomFanBlock(Properties p_i48440_1_) {
        super(p_i48440_1_);
        setDefaultState(stateContainer.getBaseState().with(DIRECTION, Direction.EAST));
    }
    private static final VoxelShape SHAPE = Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 4.0D, 14.0D);


    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext p_196258_1_) {
        setDefaultState(stateContainer.getBaseState().with(DIRECTION, p_196258_1_.getPlacementHorizontalFacing()));
        return super.getStateForPlacement(p_196258_1_);
    }

    public boolean isValidPosition(BlockState p_196260_1_, IWorldReader p_196260_2_, BlockPos p_196260_3_) {
        Block blockn = p_196260_2_.getBlockState(p_196260_3_.north()).getBlock();
        Block blocks = p_196260_2_.getBlockState(p_196260_3_.south()).getBlock();
        Block blocke = p_196260_2_.getBlockState(p_196260_3_.east()).getBlock();
        Block blockw = p_196260_2_.getBlockState(p_196260_3_.west()).getBlock();
        if(IsSolid.isSolid(blocke)||IsSolid.isSolid(blockn)||IsSolid.isSolid(blocks)|| IsSolid.isSolid(blockw)){
            return true;
        }
        return false;
    }
    public VoxelShape getShape(BlockState p_220053_1_, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_) {
        return SHAPE;
    }
    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(DIRECTION);
    }

    static{
       DIRECTION = HorizontalBlock.HORIZONTAL_FACING;
    }

}
