package dev.unnamed.vnv.common.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

//TODO: Add combined texture with leaves and flowers for flowering
public class FloweringLeavesBlock extends LeavesBlock {
    public FloweringLeavesBlock(Properties props) {
        super(props);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (world.isAreaLoaded(pos, 1)) {
            if (world.getBlockState(pos.down()).getBlock() == Blocks.AIR) {
                world.setBlockState(pos.down(), VnvBlocks.APPLE.getDefaultState());
            }
        }
    }

}
