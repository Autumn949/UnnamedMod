package com.example.examplemod.data.tags;

import com.example.examplemod.common.UnnamedMod;
import com.example.examplemod.common.blocks.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.TagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.nio.file.Path;

public class ModBlockTagsProvider extends TagsProvider<Block> {
    @SuppressWarnings("deprecation") // We need Registry.BLOCK. Sorry Forge...
    public ModBlockTagsProvider(DataGenerator gen, ExistingFileHelper helper) {
        super(gen, Registry.BLOCK, UnnamedMod.ID, helper);
    }

    @Override
    protected void registerTags() {
        getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN)
            .add(ModBlocks.APPLE_TREE_LOG);
    }

    protected ITag.Builder getBuilder(ITag.INamedTag<Block> namedTag) {
        return tagToBuilder.computeIfAbsent(namedTag.getId(), id -> new ITag.Builder());
    }

    @Override
    protected Path makePath(ResourceLocation id) {
        return generator.getOutputFolder().resolve("data/" + id.getNamespace() + "/tags/blocks/" + id.getPath() + ".json");
    }

    @Override
    public String getName() {
        return "ModBlockTags";
    }
}
