package com.example.examplemod.common.blocks;

import com.example.examplemod.common.UnnamedMod;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.FallingBlockEntity;
import net.minecraft.entity.monster.RavagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.Property;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.event.ForgeEventFactory;

import javax.annotation.Nullable;
import java.util.Random;

public class FruitBlock extends FallingBlock implements IGrowable {
    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState p_180655_1_, World p_180655_2_, BlockPos p_180655_3_, Random p_180655_4_) {}

    @Override
    public void scheduledTick(BlockState p_225534_1_, ServerWorld p_225534_2_, BlockPos p_225534_3_, Random p_225534_4_) {
        if(this.getAge(p_225534_1_)>=3) {
            if (p_225534_2_.isAirBlock(p_225534_3_.down()) || canFallThrough(p_225534_2_.getBlockState(p_225534_3_.down())) && p_225534_3_.getY() >= 0) {
                FallingBlockEntity fallingblockentity = new FallingBlockEntity(p_225534_2_, (double) p_225534_3_.getX() + 0.5D, (double) p_225534_3_.getY(), (double) p_225534_3_.getZ() + 0.5D, p_225534_2_.getBlockState(p_225534_3_));
                this.onStartFalling(fallingblockentity);
                p_225534_2_.addEntity(fallingblockentity);
            }
        }
    }
    public static final IntegerProperty AGE;
    private static final VoxelShape[] SHAPE_BY_AGE;
    public static final BooleanProperty PERSISTANT;

    @Override
    public void onBlockPlacedBy(World p_180633_1_, BlockPos p_180633_2_, BlockState p_180633_3_, @Nullable LivingEntity p_180633_4_, ItemStack p_180633_5_) {
        if(p_180633_4_ instanceof PlayerEntity){
            p_180633_1_.setBlockState(p_180633_2_,this.withPersistant(true),2);
        }
        super.onBlockPlacedBy(p_180633_1_, p_180633_2_, p_180633_3_, p_180633_4_, p_180633_5_);
    }


    public FruitBlock(Properties p_i48421_1_) {
        super(p_i48421_1_);

        this.setDefaultState((BlockState)((BlockState)this.stateContainer.getBaseState()).with(this.getAgeProperty(), 0));
        this.setDefaultState((BlockState)((BlockState)this.stateContainer.getBaseState()).with(this.getPersitantProperty(), false));
    }

    public VoxelShape getShape(BlockState p_220053_1_, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_) {
        return SHAPE_BY_AGE[(Integer)p_220053_1_.get(this.getAgeProperty())];
    }


    public IntegerProperty getAgeProperty() {
        return AGE;
    }
    public BooleanProperty getPersitantProperty() {
        return PERSISTANT;
    }

    public int getMaxAge() {
        return 6;
    }

    protected int getAge(BlockState p_185527_1_) {
        return (Integer)p_185527_1_.get(this.getAgeProperty());
    }

    protected boolean getPersitant(BlockState p_185527_1_) {
        return (Boolean)p_185527_1_.get(this.getPersitantProperty());
    }
    public BlockState withAge(int p_185528_1_) {
        return (BlockState)this.getDefaultState().with(this.getAgeProperty(), p_185528_1_);
    }
    public BlockState withPersistant(boolean p_185528_1_) {
        return (BlockState)this.getDefaultState().with(this.getPersitantProperty(), p_185528_1_);
    }

    public boolean isMaxAge(BlockState p_185525_1_) {
        return (Integer)p_185525_1_.get(this.getAgeProperty()) >= this.getMaxAge();
    }

    public boolean ticksRandomly(BlockState p_149653_1_) {
        return true;
    }

    public void randomTick(BlockState p_225542_1_, ServerWorld p_225542_2_, BlockPos p_225542_3_, Random p_225542_4_) {
        if (p_225542_2_.isAreaLoaded(p_225542_3_, 1)) {
            if (p_225542_2_.getBaseLightLevel(p_225542_3_, 0) >= 9) {
                int i = ((FruitBlock)p_225542_1_.getBlock()).getAge(p_225542_1_);
                if (!this.isMaxAge(p_225542_1_)) {
                    float f = getGrowthChance(this, p_225542_2_, p_225542_3_);
                    if (f==0.0f){

                    }else{
                        if(p_225542_4_.nextInt(10)==5) {
                            p_225542_2_.setBlockState(p_225542_3_, this.withAge(i + 1), 2);
                            ForgeHooks.onCropsGrowPost(p_225542_2_, p_225542_3_, p_225542_1_);
                        }
                    }
                }else{
                        p_225542_2_.destroyBlock(p_225542_3_, false);
                }
            }

        }
    }

    public void grow(World p_176487_1_, BlockPos p_176487_2_, BlockState p_176487_3_) {
        int i = this.getAge(p_176487_3_) + this.getBonemealAgeIncrease(p_176487_1_);
        int j = this.getMaxAge();
        if (i > j) {
            i = j;
        }

        p_176487_1_.setBlockState(p_176487_2_, this.withAge(i), 2);
    }

    protected int getBonemealAgeIncrease(World p_185529_1_) {
        return MathHelper.nextInt(p_185529_1_.rand, 1, 1);
    }

    protected static float getGrowthChance(Block p_180672_0_, IBlockReader p_180672_1_, BlockPos p_180672_2_) {
        //returns 0.2 if can grow 0.0 if cannot due to being player placed
        float f = 0.0f;
            if(((FruitBlock) p_180672_0_).getPersitant(p_180672_1_.getBlockState(p_180672_2_))==false) {
                        f = 0.2f;
            }
        return f;
    }

    public boolean isValidPosition(BlockState p_196260_1_, IWorldReader p_196260_2_, BlockPos p_196260_3_) {
        //makes sure cannot be placed on other fruit
        BlockPos blockpos = p_196260_3_.down();
        if(p_196260_2_.getBlockState(blockpos).getBlock() instanceof FruitBlock){
            return false;
        }
        return true;
    }

    public void onEntityCollision(BlockState p_196262_1_, World p_196262_2_, BlockPos p_196262_3_, Entity p_196262_4_) {
    }



    public ItemStack getItem(IBlockReader p_185473_1_, BlockPos p_185473_2_, BlockState p_185473_3_) {
        return new ItemStack(this.getBlock());
    }

    public boolean canGrow(IBlockReader p_176473_1_, BlockPos p_176473_2_, BlockState p_176473_3_, boolean p_176473_4_) {
        return !this.isMaxAge(p_176473_3_);
    }

    public boolean canUseBonemeal(World p_180670_1_, Random p_180670_2_, BlockPos p_180670_3_, BlockState p_180670_4_) {
        return false;
    }

    public void grow(ServerWorld p_225535_1_, Random p_225535_2_, BlockPos p_225535_3_, BlockState p_225535_4_) {
        this.grow(p_225535_1_, p_225535_3_, p_225535_4_);
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> p_206840_1_) {
        p_206840_1_.add(new Property[]{AGE});
        p_206840_1_.add(new Property[]{PERSISTANT});
    }

    static {
        AGE = IntegerProperty.create("age",0,6);
        PERSISTANT = BooleanProperty.create("persistant");
        //TODO:SET BLOCK SHAPE PROPERLY FOR FRUITS
        SHAPE_BY_AGE = new VoxelShape[]{Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 10.0D, 16.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 14.0D, 16.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D)};
    }

}
