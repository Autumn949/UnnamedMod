package com.example.examplemod.init;

import com.example.examplemod.common.blocks.Apple;
import com.example.examplemod.common.blocks.AppleLeaves;
import com.example.examplemod.common.blocks.AppleLog;
import com.example.examplemod.common.blocks.ApplePlanks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final RegistryObject<RotatedPillarBlock> APPLE_LOG = Registration.BLOCKS.register("apple_log", AppleLog::new);
    public static final RegistryObject<Block> APPLE_PLANKS = Registration.BLOCKS.register("apple_planks", ApplePlanks::new);
    public static final RegistryObject<Block> APPLE_LEAVES = Registration.BLOCKS.register("apple_leaves", AppleLeaves::new);
    public static final RegistryObject<Block> APPLE  = Registration.BLOCKS.register("apple_fruit", Apple::new);
    public static void register(){
    }


}
