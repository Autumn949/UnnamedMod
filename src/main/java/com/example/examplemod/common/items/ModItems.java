package com.example.examplemod.common.items;

import com.example.examplemod.common.UnnamedMod;
import com.example.examplemod.common.blocks.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.List;

public abstract class ModItems {
    private static final List<Item> REGISTRY = new ArrayList<>();

    //
    // ITEMS
    //

    public static final Item APPLE_TREE_LOG = block(ModBlocks.APPLE_TREE_LOG, ItemGroup.BUILDING_BLOCKS);


    //
    // REGISTRY
    //

    public static void register(IForgeRegistry<Item> registry) {
        REGISTRY.forEach(registry::register);
    }

    public static void setup() {

    }

    @OnlyIn(Dist.CLIENT)
    public static void setupClient() {

    }


    //
    // FACTORY METHODS
    //

    private static <T extends Item> T item(String id, T item) {
        item.setRegistryName(UnnamedMod.id(id));
        REGISTRY.add(item);
        return item;
    }

    private static BlockItem block(Block block, Item.Properties props) {
        return item(
            block.getRegistryName() + "",
            new BlockItem(block, props)
        );
    }

    private static BlockItem block(Block block, ItemGroup group) {
        return block(block, new Item.Properties().group(group));
    }
}
