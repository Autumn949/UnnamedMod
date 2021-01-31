package dev.unnamed.vnv.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FallingBlock;
import net.minecraft.block.IGrowable;
import net.minecraft.entity.item.FallingBlockEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ForgeHooks;

import javax.annotation.Nullable;
import java.util.Random;

@SuppressWarnings("deprecation")
public class AppleBlock extends FallingBlock implements IGrowable {
    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 6);
    private static final VoxelShape[] SHAPE_BY_AGE = {
        makeAppleShape(2, 2, false),
        makeAppleShape(4, 5, false),
        makeAppleShape(6, 7, false),
        makeAppleShape(6, 8, true),
        makeAppleShape(6, 8, true),
        makeAppleShape(6, 8, true),
        makeAppleShape(6, 8, true)
    };
    public static final BooleanProperty PERSISTENT = BooleanProperty.create("persistent");


    public AppleBlock(Properties props) {
        super(props);

        setDefaultState(stateContainer.getBaseState().with(AGE, 0));
        setDefaultState(stateContainer.getBaseState().with(PERSISTENT, false));
    }

    private static VoxelShape makeAppleShape(double size, double len, boolean ground) {
        double s2 = size / 2;
        return makeCuboidShape(8 - s2, ground ? 0 : 16 - len, 8 - s2, 8 + s2, ground ? len : 16, 8 + s2);
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(AGE, PERSISTENT);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext ctx) {
        return getDefaultState().with(PERSISTENT, true);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
        return SHAPE_BY_AGE[state.get(AGE)];
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
        return VoxelShapes.empty();
    }

    @Override
    public boolean ticksRandomly(BlockState state) {
        return super.ticksRandomly(state) && !state.get(PERSISTENT);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random rng) {
        if (state.get(PERSISTENT)) {
            return;
        }

        if (world.isAreaLoaded(pos, 1)) {
            if (world.getBaseLightLevel(pos, 0) >= 5) {
                int age = state.get(AGE);
                if (age < 6) {
                    world.setBlockState(pos, state.with(AGE, age + 1), 3);
                    ForgeHooks.onCropsGrowPost(world, pos, state);
                } else {
                    // TODO: WHAT?!
                    world.destroyBlock(pos, false);
                }
            }

        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState state, World world, BlockPos pos, Random rng) {
        // Cancel dust particles
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random rng) {
        if (state.get(AGE) >= 3) {
            if (world.isAirBlock(pos.down()) || canFallThrough(world.getBlockState(pos.down())) && pos.getY() >= 0) {
                FallingBlockEntity fallingBlock = new FallingBlockEntity(
                    world,
                    pos.getX() + 0.5,
                    pos.getY(),
                    pos.getZ() + 0.5,
                    world.getBlockState(pos)
                );
                onStartFalling(fallingBlock);
                world.addEntity(fallingBlock);
            }
        }
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader world, BlockPos pos) {
        BlockState up = world.getBlockState(pos.up());
        BlockState down = world.getBlockState(pos.down());

        return down.isSideSolidFullSquare(world, pos, Direction.UP) || up.isIn(BlockTags.LEAVES);
    }



    @Override
    public ItemStack getItem(IBlockReader p_185473_1_, BlockPos p_185473_2_, BlockState p_185473_3_) {
        return new ItemStack(this.getBlock());
    }

    @Override
    public boolean canGrow(IBlockReader world, BlockPos pos, BlockState state, boolean client) {
        return state.get(AGE) < 6;
    }

    @Override
    public boolean canUseBonemeal(World world, Random rand, BlockPos pos, BlockState state) {
        return true;
    }

    protected int getBonemealAgeIncrease(World world) {
        return MathHelper.nextInt(world.rand, 1, 3);
    }

    @Override
    public void grow(ServerWorld world, Random rand, BlockPos pos, BlockState state) {
        int age = Math.min(state.get(AGE) + getBonemealAgeIncrease(world), 6);
        world.setBlockState(pos, state.with(AGE, age), 3);
    }
}
