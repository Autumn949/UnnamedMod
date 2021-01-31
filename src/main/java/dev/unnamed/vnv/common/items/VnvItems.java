package dev.unnamed.vnv.common.items;

import dev.unnamed.vnv.common.ValleysNVistas;
import dev.unnamed.vnv.common.blocks.VnvBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.ComposterBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.*;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.List;

public abstract class VnvItems {
    private static final List<Item> REGISTRY = new ArrayList<>();

    //
    // BLOCKS
    //

    public static final Item APPLE_TREE_LOG = block(VnvBlocks.APPLE_TREE_LOG, ItemGroup.BUILDING_BLOCKS);
    public static final Item APPLE_TREE_LEAVES = block(VnvBlocks.APPLE_TREE_LEAVES, ItemGroup.DECORATIONS);
    public static final Item FLOWERING_APPLE_TREE_LEAVES = block(VnvBlocks.FLOWERING_APPLE_TREE_LEAVES, ItemGroup.DECORATIONS);

    public static final Item APPLE_TREE_PLANKS = block(VnvBlocks.APPLE_TREE_PLANKS, ItemGroup.BUILDING_BLOCKS);
    public static final Item APPLE_TREE_SLAB = block(VnvBlocks.APPLE_TREE_SLAB, ItemGroup.BUILDING_BLOCKS);
    public static final Item APPLE_TREE_STAIRS = block(VnvBlocks.APPLE_TREE_STAIRS, ItemGroup.BUILDING_BLOCKS);
    public static final Item APPLE_TREE_FENCE = block(VnvBlocks.APPLE_TREE_FENCE, ItemGroup.DECORATIONS);
    public static final Item APPLE_TREE_FENCE_GATE = block(VnvBlocks.APPLE_TREE_FENCE_GATE, ItemGroup.REDSTONE);

    public static final Item APPLE_TREE_DOOR = block(VnvBlocks.APPLE_TREE_DOOR, ItemGroup.REDSTONE);
    public static final Item APPLE_TREE_TRAPDOOR = block(VnvBlocks.APPLE_TREE_TRAPDOOR, ItemGroup.REDSTONE);

    public static final Item MUD = block(VnvBlocks.MUD, ItemGroup.BUILDING_BLOCKS);
    public static final Item MUSHROOM_FAN = block(VnvBlocks.MUSHROOM_FAN, ItemGroup.DECORATIONS);


    //
    // ITEMS
    //

    // Re-register apple, so we can place it
    public static final Item APPLE = apple("minecraft:apple", VnvBlocks.APPLE);
    public static final Item ROTTEN_APPLE = rottenApple("rotten_apple");



    public static void register(IForgeRegistry<Item> registry) {
        REGISTRY.forEach(registry::register);
    }

    public static void setup() {
        registerCompostable(0.65f, ROTTEN_APPLE);
        registerCompostable(0.35f, APPLE_TREE_LEAVES);
        registerCompostable(0.35f, FLOWERING_APPLE_TREE_LEAVES);
    }

    @OnlyIn(Dist.CLIENT)
    public static void setupClient() {
        ItemColors colors = Minecraft.getInstance().getItemColors();
        colors.register(
            (stack, tintIndex) -> 0x009933,
            APPLE_TREE_LEAVES, FLOWERING_APPLE_TREE_LEAVES
        );
    }


    //
    // FACTORY METHODS
    //

    private static <T extends Item> T item(String id, T item) {
        item.setRegistryName(ValleysNVistas.id(id));
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

    private static BlockItem apple(String id, Block block) {
        return item(id, new BlockNamedItem(
            block,
            new Item.Properties().group(ItemGroup.FOOD)
                                 .food(Foods.APPLE)
        ));
    }

    private static Item rottenApple(String id) {
        return item(id, new Item(
            new Item.Properties().group(ItemGroup.FOOD)
                                 .food(VnvFoods.ROTTEN_APPLE)
        ));
    }


    //
    // MISC
    //

    private static void registerCompostable(float chance, IItemProvider item) {
        ComposterBlock.CHANCES.put(item.asItem(), chance);
    }
}
