package com.example.examplemod.common.blocks;

import com.example.examplemod.common.UnnamedMod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.Property;
import net.minecraft.state.StateContainer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ForgeHooks;

import java.util.Random;
import java.util.logging.LogManager;

//TODO: Add combined texture with leaves and flowers for flowering
public class FruitLeaves extends LeavesBlock {
    public FruitLeaves(Properties p_i48370_1_) {
        super(p_i48370_1_);
        this.setDefaultState((BlockState)((BlockState)this.stateContainer.getBaseState()).with(this.getFloweringProperty(), 0));
    }
    public static final IntegerProperty FLOWERING;
    static {
        FLOWERING = IntegerProperty.create("flowering",0,1);

    }
    protected int getFlowering(BlockState block){
        return (Integer)block.get(this.getFloweringProperty());
    }
    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> p_206840_1_) {
        p_206840_1_.add(new Property[]{FLOWERING});
        super.fillStateContainer(p_206840_1_);
    }

    public IntegerProperty getFloweringProperty() {
        return FLOWERING;
    }

    public boolean ticksRandomly(BlockState p_149653_1_) {
        return true;
    }

    @Override
    public void randomTick(BlockState p_225542_1_, ServerWorld p_225542_2_, BlockPos p_225542_3_, Random p_225542_4_) {
        if(p_225542_4_.nextInt(4)==2)
        if (p_225542_2_.isAreaLoaded(p_225542_3_, 1)) {
            if (p_225542_2_.getBlockState(p_225542_3_.down()).getBlock() == Blocks.AIR) {
                if(this.getFlowering(p_225542_1_)==1){

                    p_225542_2_.setBlockState(p_225542_3_.down(),ModBlocks.APPLE.getDefaultState());
                    p_225542_2_.setBlockState(p_225542_3_, (BlockState)this.getDefaultState().with(this.getFloweringProperty(), 0), 2);
                }else{
                    p_225542_2_.setBlockState(p_225542_3_, (BlockState)this.getDefaultState().with(this.getFloweringProperty(), 1), 2);
                }
            }

            }

        }

}
