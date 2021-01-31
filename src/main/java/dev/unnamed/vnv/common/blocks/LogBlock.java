package dev.unnamed.vnv.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;

public class LogBlock extends RotatedPillarBlock {
    public final Block STRIPPED;
    public LogBlock(Properties p_i48339_1_, Block b) {
        super(p_i48339_1_);
        STRIPPED=b;
    }

    @Nullable
    @Override
    public BlockState getToolModifiedState(BlockState state, World world, BlockPos pos, PlayerEntity player, ItemStack stack, ToolType toolType) {
        if(toolType==ToolType.AXE){
            return STRIPPED.getDefaultState();
        }
        return state;
    }
}
