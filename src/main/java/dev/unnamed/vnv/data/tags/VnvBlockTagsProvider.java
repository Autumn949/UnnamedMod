package dev.unnamed.vnv.data.tags;

import dev.unnamed.vnv.common.ValleysNVistas;
import dev.unnamed.vnv.common.blocks.VnvBlocks;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.TagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.nio.file.Path;

public class VnvBlockTagsProvider extends TagsProvider<Block> {
    @SuppressWarnings("deprecation") // We need Registry.BLOCK. Sorry Forge...
    public VnvBlockTagsProvider(DataGenerator gen, ExistingFileHelper helper) {
        super(gen, Registry.BLOCK, ValleysNVistas.ID, helper);
    }

    @Override
    protected void registerTags() {
        getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN)
            .add(VnvBlocks.APPLE_TREE_LOG);

        getOrCreateTagBuilder(BlockTags.LEAVES)
            .add(VnvBlocks.APPLE_TREE_LEAVES)
            .add(VnvBlocks.FLOWERING_APPLE_TREE_LEAVES);

        getOrCreateTagBuilder(BlockTags.PLANKS)
            .add(VnvBlocks.APPLE_TREE_PLANKS);

        getOrCreateTagBuilder(BlockTags.WOODEN_SLABS)
            .add(VnvBlocks.APPLE_TREE_SLAB);

        getOrCreateTagBuilder(BlockTags.WOODEN_STAIRS)
            .add(VnvBlocks.APPLE_TREE_STAIRS);

        getOrCreateTagBuilder(BlockTags.WOODEN_DOORS)
            .add(VnvBlocks.APPLE_TREE_DOOR);

        getOrCreateTagBuilder(BlockTags.WOODEN_TRAPDOORS)
            .add(VnvBlocks.APPLE_TREE_TRAPDOOR);

        getOrCreateTagBuilder(BlockTags.WOODEN_FENCES)
            .add(VnvBlocks.APPLE_TREE_FENCE);

        getOrCreateTagBuilder(BlockTags.FENCE_GATES)
            .add(VnvBlocks.APPLE_TREE_FENCE_GATE);
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
        return "VnvBlockTags";
    }
}
