package dev.unnamed.vnv.common.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IWorldReader;

import static net.minecraft.block.Block.makeCuboidShape;

public interface IWallAttachable {
    //todo change to meet directions of placement
    VoxelShape[] SHAPES = new VoxelShape[]{
            makeCuboidShape(0,15,0,16,16,16),
            makeCuboidShape(0,0,0,16,1,16),
            makeCuboidShape(0,0,0,1,16,16),
            makeCuboidShape(0,0,0,1,16,16),
            makeCuboidShape(0,0,0,1,16,16),
            makeCuboidShape(0,0,0,1,16,16)
            //east
            //west
            //north
            //south

    };

    boolean isValidPosition(BlockState p_196260_1_, IWorldReader p_196260_2_, BlockPos p_196260_3_);



}
