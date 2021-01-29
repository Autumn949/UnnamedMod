package com.example.examplemod.common.blocks;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.FallingBlockEntity;
import net.minecraft.entity.monster.RavagerEntity;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.Property;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.event.ForgeEventFactory;

import java.util.Random;

public class FruitBlock extends FallingBlock implements IGrowable {
    public static final IntegerProperty AGE;
    private static final VoxelShape[] SHAPE_BY_AGE;

    public FruitBlock(Properties p) {
        super(p);
        this.setDefaultState((BlockState) ((BlockState) this.stateContainer.getBaseState()).with(this.getAgeProperty(), 0));
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE_BY_AGE[this.getAge(state)];
    }


    public IntegerProperty getAgeProperty() {
        return AGE;
    }

    public int getMaxAge() {
        return 4;
    }

    protected int getAge(BlockState state) {
        return (Integer) state.get(this.getAgeProperty());
    }

    public BlockState withAge(int age) {
        return (BlockState) this.getDefaultState().with(this.getAgeProperty(), age);
    }

    public boolean isMaxAge(BlockState state) {
        return (Integer) state.get(this.getAgeProperty()) >= this.getMaxAge();
    }

    public boolean ticksRandomly(BlockState state) {
        return !this.isMaxAge(state);
    }

    public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
        if (worldIn.isAreaLoaded(pos, 1)) {
            if (worldIn.getLightSubtracted(pos, 0) >= 9) {
                int i = this.getAge(state);
                if (i < this.getMaxAge()) {
                    float f = getGrowthChance(this, worldIn, pos);
                    if (ForgeHooks.onCropsGrowPre(worldIn, pos, state, random.nextInt((int) (25.0F / f) + 1) == 0)) {
                        worldIn.setBlockState(pos, this.withAge(i + 1), 2);
                        ForgeHooks.onCropsGrowPost(worldIn, pos, state);
                    }
                }
            }

        }
    }

    public void grow(World worldIn, BlockPos pos, BlockState state) {
        int i = this.getAge(state) + this.getBonemealAgeIncrease(worldIn);
        int j = this.getMaxAge();
        if(i<(this.getMaxAge()-1)){
            i=this.getMaxAge()-1;
        }else{
            i = j;
        }

        worldIn.setBlockState(pos, this.withAge(i), 2);
    }

    protected int getBonemealAgeIncrease(World worldIn) {
        return MathHelper.nextInt(worldIn.rand, 1, 2);
    }

    protected static float getGrowthChance(Block blockIn, IBlockReader worldIn, BlockPos pos) {
        float f = 1.0F;
        BlockPos blockpos = pos.up();

        for (int i = -1; i <= 1; ++i) {
            float f1 = 0.0F;
            BlockState blockstate = worldIn.getBlockState(blockpos);
            if (blockstate.getBlock() instanceof LeavesBlock) {
                    f1=1f;
            }
            f += f1;

        }

        BlockPos blockpos1 = pos.north();
        BlockPos blockpos2 = pos.south();
        BlockPos blockpos3 = pos.west();
        BlockPos blockpos4 = pos.east();
        boolean flag = blockIn == worldIn.getBlockState(blockpos3).getBlock() || blockIn == worldIn.getBlockState(blockpos4).getBlock();
        boolean flag1 = blockIn == worldIn.getBlockState(blockpos1).getBlock() || blockIn == worldIn.getBlockState(blockpos2).getBlock();
        if (flag && flag1) {
            f /= 2.0F;
        } else {
            boolean flag2 = blockIn == worldIn.getBlockState(blockpos3.north()).getBlock() || blockIn == worldIn.getBlockState(blockpos4.north()).getBlock() || blockIn == worldIn.getBlockState(blockpos4.south()).getBlock() || blockIn == worldIn.getBlockState(blockpos3.south()).getBlock();
            if (flag2) {
                f /= 2.0F;
            }
        }

        return f;
    }

    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        return (worldIn.getLightSubtracted(pos, 0) >= 8 || worldIn.canSeeSky(pos)) && super.isValidPosition(state, worldIn, pos);
    }

    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
        if (entityIn instanceof RavagerEntity && ForgeEventFactory.getMobGriefingEvent(worldIn, entityIn)) {
            worldIn.destroyBlock(pos, true, entityIn);
        }

        super.onEntityCollision(state, worldIn, pos, entityIn);
    }


    @Override
    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
        if(this.getAge(state)>=(this.getMaxAge()-1)) {
            if (worldIn.isAirBlock(pos.down()) || canFallThrough(worldIn.getBlockState(pos.down())) && pos.getY() >= 0) {
                FallingBlockEntity fallingblockentity = new FallingBlockEntity(worldIn, (double) pos.getX() + 0.5D, (double) pos.getY(), (double) pos.getZ() + 0.5D, worldIn.getBlockState(pos));
                this.onStartFalling(fallingblockentity);
                worldIn.addEntity(fallingblockentity);
            }
        }
    }

    public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
        return !this.isMaxAge(state);
    }

    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state) {
        if(this.getAge(state)<(this.getMaxAge()-1)){
            return true;
        }else{
            return false;
        }
    }

    public void grow(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) {
        this.grow(worldIn, pos, state);
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{AGE});
    }

    static {
        AGE = IntegerProperty.create("age", 0, 4);;
        SHAPE_BY_AGE = new VoxelShape[]{Block.makeCuboidShape(7.0D, 14.0D, 7.0D, 9.0D, 16.0D, 9.0D), Block.makeCuboidShape(6.0D, 11.0D, 6.0D, 10.0D, 15.0D, 10.0D), Block.makeCuboidShape(6.0D, 10.0D, 6.0D, 12.0D, 16.0D, 12.0D), Block.makeCuboidShape(7.0D, 14.0D, 7.0D, 9.0D, 16.0D, 9.0D), Block.makeCuboidShape(7.0D, 14.0D, 7.0D, 9.0D, 16.0D, 9.0D)};
    }

}
