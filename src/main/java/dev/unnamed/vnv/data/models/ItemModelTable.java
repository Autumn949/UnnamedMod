package dev.unnamed.vnv.data.models;

import dev.unnamed.vnv.common.blocks.VnvBlocks;
import dev.unnamed.vnv.data.models.modelgen.IModelGen;
import net.minecraft.item.Item;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;

import java.util.function.BiConsumer;
import java.util.function.Function;

import static dev.unnamed.vnv.data.models.modelgen.InheritingModelGen.*;

public final class ItemModelTable {
    private static BiConsumer<Item, IModelGen> consumer;

    public static void registerItemModels(BiConsumer<Item, IModelGen> c) {
        consumer = c;

        register(VnvBlocks.APPLE_TREE_LOG, item -> inherit(name(item, "block/%s")));
        register(VnvBlocks.APPLE_TREE_LEAVES, item -> inherit(name(item, "block/%s")));
        register(VnvBlocks.FLOWERING_APPLE_TREE_LEAVES, item -> inherit(name(item, "block/%s")));

        register(VnvBlocks.APPLE_TREE_PLANKS, item -> inherit(name(item, "block/%s")));
        register(VnvBlocks.APPLE_TREE_SLAB, item -> inherit(name(item, "block/%s")));
        register(VnvBlocks.APPLE_TREE_STAIRS, item -> inherit(name(item, "block/%s")));

        register(VnvBlocks.APPLE_TREE_FENCE, item -> fenceInventory(name(item, "block/%s_planks", "_fence")));

        register(VnvBlocks.MUD, item -> inherit(name(item, "block/%s")));
    }


    private static void register(IItemProvider provider, Function<Item, IModelGen> genFactory) {
        Item item = provider.asItem();
        IModelGen gen = genFactory.apply(item);
        consumer.accept(item, gen);
    }

    private static String name(Item item, String nameFormat) {
        ResourceLocation id = item.getRegistryName();
        assert id != null;

        return String.format("%s:%s", id.getNamespace(), String.format(nameFormat, id.getPath()));
    }

    private static String name(Item item) {
        ResourceLocation id = item.getRegistryName();
        assert id != null;
        return id.toString();
    }

    private static String name(Item item, String nameFormat, String omitSuffix) {
        ResourceLocation id = item.getRegistryName();
        assert id != null;

        String path = id.getPath();
        if (path.endsWith(omitSuffix)) {
            path = path.substring(0, path.length() - omitSuffix.length());
        }

        return String.format("%s:%s", id.getNamespace(), String.format(nameFormat, path));
    }



    private ItemModelTable() {
    }
}
