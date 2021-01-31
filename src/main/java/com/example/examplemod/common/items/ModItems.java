package com.example.examplemod.common.items;

import com.example.examplemod.common.UnnamedMod;
import com.example.examplemod.common.blocks.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.ComposterBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.*;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.IItemProvider;
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


    //
    //APPLE ITEMS
    //
    public static final Item APPLE_TREE_LOG = block(ModBlocks.APPLE_TREE_LOG, ItemGroup.BUILDING_BLOCKS);
    public static final Item APPLE_LEAVES = block(ModBlocks.APPLE_LEAVES,ItemGroup.BUILDING_BLOCKS);
    public static final Item APPLE_LEAVES_FLOWERABLE = block(ModBlocks.APPLE_LEAVES_FLOWERABLE,ItemGroup.BUILDING_BLOCKS);
    public static final Item APPLE_PLANKS = block(ModBlocks.APPLE_PLANKS,ItemGroup.BUILDING_BLOCKS);
    public static final Item APPLE_SLAB = block(ModBlocks.APPLE_SLAB,ItemGroup.BUILDING_BLOCKS);
    public static final Item APPLE_FENCE_GATE = block(ModBlocks.APPLE_FENCE_GATE,ItemGroup.BUILDING_BLOCKS);
    public static final Item APPLE_FENCE = block(ModBlocks.APPLE_FENCE,ItemGroup.BUILDING_BLOCKS);
    public static final Item APPLE_STAIRS = block(ModBlocks.APPLE_STAIRS,ItemGroup.BUILDING_BLOCKS);
    public static final Item APPLE = block(ModBlocks.APPLE,ItemGroup.FOOD);
    public static final Item APPLE_DOOR = block(ModBlocks.APPLE_DOOR,ItemGroup.BUILDING_BLOCKS);
    public static final Item APPLE_TRAP_DOOR=block(ModBlocks.APPLE_TRAP_DOOR,ItemGroup.BUILDING_BLOCKS);
    public static final Item ROTTEN_APPLE = item("rotten_apple", new Item((new Item.Properties()).group(ItemGroup.FOOD).food(ModFoods.ROTTEN_APPLE)));

    //
    //FOREST ITEMS
    //
    public static final Item MUD_BLOCK = block(ModBlocks.MUD_BLOCK,ItemGroup.BUILDING_BLOCKS);
    public static final Item FAN_MUSHROOMS = block(ModBlocks.FAN_MUSHROOMS,ItemGroup.BUILDING_BLOCKS);
    //
    // REGISTRY
    //

    public static void register(IForgeRegistry<Item> registry) {
        REGISTRY.forEach(registry::register);
        //
        //REGISTER COMPOSTABLES
        //
        registerCompostable(0.65f,ROTTEN_APPLE);
        registerCompostable(0.35f,APPLE_LEAVES);
        registerCompostable(0.35f,APPLE_LEAVES_FLOWERABLE);
    }

    public static void setup() {

    }

    @OnlyIn(Dist.CLIENT)
    public static void setupClient() {
        Minecraft.getInstance().getItemColors().register(new IItemColor() {
            @Override
            public int getColor(ItemStack p_getColor_1_, int p_getColor_2_) {
                return 0x009933;
            }
        },APPLE_LEAVES);
        Minecraft.getInstance().getItemColors().register(new IItemColor() {
            @Override
            public int getColor(ItemStack p_getColor_1_, int p_getColor_2_) {
                return 0x009933;
            }
        },APPLE_LEAVES_FLOWERABLE);
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
    private static void registerCompostable(float p_220290_0_, IItemProvider p_220290_1_) {
        ComposterBlock.CHANCES.put(p_220290_1_.asItem(), p_220290_0_);
    }
}
