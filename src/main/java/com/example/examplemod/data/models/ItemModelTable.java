package com.example.examplemod.data.models;

import com.example.examplemod.common.blocks.ModBlocks;
import com.example.examplemod.common.items.ModItems;
import com.example.examplemod.data.models.modelgen.IModelGen;
import com.google.gson.JsonElement;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.function.BiConsumer;
import java.util.function.Function;

import static com.example.examplemod.data.models.modelgen.InheritingModelGen.*;

public final class ItemModelTable {
    private static BiConsumer<Item, IModelGen> consumer;

    public static void registerItemModels(BiConsumer<Item, IModelGen> c) {
        consumer = c;

        register(ModBlocks.APPLE_TREE_LOG, item -> inherit(name(item, "block/%s")));
        register(ModBlocks.APPLE_LEAVES,item->inherit(name(item,"block/%s")));
        register(ModBlocks.APPLE_PLANKS, item->inherit(name(item,"block/%s")));
        register(ModBlocks.APPLE_SLAB, item->inherit(name(item,"block/%s")));
        register(ModBlocks.APPLE_FENCE,item->inherit(name(item,"block/%s")));
        register(ModBlocks.APPLE_STAIRS,item->inherit(name(item,"block/%s")));

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
