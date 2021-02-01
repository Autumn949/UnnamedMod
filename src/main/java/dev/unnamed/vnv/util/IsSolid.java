package dev.unnamed.vnv.util;

import net.minecraft.block.AirBlock;
import net.minecraft.block.Block;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.common.FarmlandWaterManager;
import net.minecraftforge.common.IForgeShearable;

import java.util.Iterator;

public class IsSolid {

    public static boolean isSolid(Block b){
        if(b instanceof Block){
            if(b instanceof AirBlock){
                return false;
            }else if(b instanceof IForgeShearable){
                return false;
            }else if(b instanceof IWaterLoggable){
                return false;
            }
            return true;
        }
        return false;
    }

    public static boolean hasWater(IWorldReader worldIn, BlockPos pos) {
        Iterator var2 = BlockPos.getAllInBoxMutable(pos.add(-4, 0, -4), pos.add(4, 1, 4)).iterator();

        BlockPos blockpos;
        do {
            if (!var2.hasNext()) {
                return FarmlandWaterManager.hasBlockWaterTicket(worldIn, pos);
            }

            blockpos = (BlockPos)var2.next();
        } while(!worldIn.getFluidState(blockpos).isTagged(FluidTags.WATER));

        return true;
    }
}
