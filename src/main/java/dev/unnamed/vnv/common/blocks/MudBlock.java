package dev.unnamed.vnv.common.blocks;

import dev.unnamed.vnv.common.ValleysNVistas;
import dev.unnamed.vnv.util.IsSolid;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluids;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.Property;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class MudBlock extends Block {



    @Override
    public boolean ticksRandomly(BlockState p_149653_1_) {
        return true;
    }


    @Override
    public void randomTick(BlockState p_225542_1_, ServerWorld p_225542_2_, BlockPos p_225542_3_, Random p_225542_4_) {
            if (p_225542_2_.isAreaLoaded(p_225542_3_, 1)) {
                    if(IsSolid.hasWater(p_225542_2_,p_225542_3_)){
                        p_225542_2_.setBlockState(p_225542_3_,VnvBlocks.MUD_WET.getDefaultState(),2);
                    }

            }

    }

    public MudBlock(Properties p_i48440_1_) {
        super(p_i48440_1_);

    }


}
