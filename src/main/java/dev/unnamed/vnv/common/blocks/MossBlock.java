package dev.unnamed.vnv.common.blocks;

import dev.unnamed.vnv.common.ValleysNVistas;
import dev.unnamed.vnv.util.IsSolid;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class MossBlock extends Block implements IWallAttachable {
    public MossBlock(Properties p_i48440_1_) {
        super(p_i48440_1_);
        setDefaultState(stateContainer.getBaseState().with(DIRECTION, Direction.EAST));
    }
    public static final DirectionProperty DIRECTION = DirectionProperty.create("direction",Direction.EAST,Direction.NORTH,Direction.SOUTH,Direction.WEST);

    //TODO:Make cube shapes match planar direction

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(DIRECTION);
    }


    @Override
    public void onBlockPlacedBy(World p_180633_1_, BlockPos p_180633_2_, BlockState p_180633_3_, @Nullable LivingEntity p_180633_4_, ItemStack p_180633_5_) {
        if(p_180633_4_ == null){
            Block blockn = p_180633_1_.getBlockState(p_180633_2_.north()).getBlock();
            Block blocke = p_180633_1_.getBlockState(p_180633_2_.east()).getBlock();
            Block blockw = p_180633_1_.getBlockState(p_180633_2_.west()).getBlock();
            if(IsSolid.isSolid(blocke)){
                setDefaultState(stateContainer.getBaseState().with(DIRECTION, Direction.EAST));
            }else if(IsSolid.isSolid(blockn)){
                setDefaultState(stateContainer.getBaseState().with(DIRECTION, Direction.NORTH));
            }else if(IsSolid.isSolid(blockw)){
                setDefaultState(stateContainer.getBaseState().with(DIRECTION, Direction.WEST));
            }else{
                setDefaultState(stateContainer.getBaseState().with(DIRECTION, Direction.SOUTH));
            }
        }else{
            setDefaultState(stateContainer.getBaseState().with(DIRECTION, p_180633_4_.getAdjustedHorizontalFacing()));
        }
        super.onBlockPlacedBy(p_180633_1_, p_180633_2_, p_180633_3_, p_180633_4_, p_180633_5_);
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

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {        switch(state.get(DIRECTION)){
        case UP:
            return SHAPES[0];

        case DOWN:
            return SHAPES[1];

        case EAST:
            return SHAPES[2];

        case WEST:
            return SHAPES[3];

        case NORTH:
            return SHAPES[4];

        case SOUTH:
            return SHAPES[5];

    }        return null;
    }
}
