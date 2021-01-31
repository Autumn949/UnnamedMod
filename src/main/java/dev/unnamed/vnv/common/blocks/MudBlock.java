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
    public static BooleanProperty ISWET;

    protected boolean getWet(BlockState block){
        return(Boolean)block.get(this.getWetProperty());
    }

    @Override
    public boolean ticksRandomly(BlockState p_149653_1_) {
        return true;
    }

    public BooleanProperty getWetProperty() {
        return ISWET;
    }

    @Override
    public void randomTick(BlockState p_225542_1_, ServerWorld p_225542_2_, BlockPos p_225542_3_, Random p_225542_4_) {

            if (p_225542_2_.isAreaLoaded(p_225542_3_, 1)) {
                    if(IsSolid.hasWater(p_225542_2_,p_225542_3_)){
                        p_225542_2_.setBlockState(p_225542_3_,this.getDefaultState().with(getWetProperty(),true),2);
                    }else{
                        p_225542_2_.setBlockState(p_225542_3_,this.getDefaultState().with(getWetProperty(),false),2);
                    }

            }

    }

    public MudBlock(Properties p_i48440_1_) {
        super(p_i48440_1_);
        this.setDefaultState((BlockState)((BlockState)this.stateContainer.getBaseState()).with(this.getWetProperty(), false));
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> p_206840_1_) {
        p_206840_1_.add(new Property[]{ISWET});
        super.fillStateContainer(p_206840_1_);
    }
    static{
        ISWET = BooleanProperty.create("wet");
    }
}
