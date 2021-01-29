package com.example.examplemod.init;

import com.example.examplemod.common.blocks.AppleLog;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;

public class ModItems {
    public static final RegistryObject<Item> APPLE_LOG = Registration.ITEMS.register("apple_log", () -> new BlockItem(ModBlocks.APPLE_LOG.get(), new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)));
    public static final RegistryObject<Item> APPLE_PLANKS = Registration.ITEMS.register("apple_planks",()->new BlockItem(ModBlocks.APPLE_PLANKS.get(), new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)));
    public static final RegistryObject<Item> APPLE_LEAVES = Registration.ITEMS.register("apple_leaves",()->new BlockItem(ModBlocks.APPLE_LEAVES.get(), new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)));
    public static void register(){

    }
}
