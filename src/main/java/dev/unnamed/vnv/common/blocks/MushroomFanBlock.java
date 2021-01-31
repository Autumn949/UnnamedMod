package dev.unnamed.vnv.common.blocks;


import dev.unnamed.vnv.util.IsSolid;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;

import javax.annotation.Nullable;

public class MushroomFanBlock extends Block {
    public Direction direction;
    public MushroomFanBlock(Properties p_i48440_1_) {
        super(p_i48440_1_);
    }
    private static final VoxelShape SHAPE = Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 4.0D, 14.0D);
    //private final Direction direction;


    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext p_196258_1_) {
        direction = p_196258_1_.getFace();
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

}
