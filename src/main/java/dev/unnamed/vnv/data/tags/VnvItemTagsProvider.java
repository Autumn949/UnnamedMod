package dev.unnamed.vnv.data.tags;

import dev.unnamed.vnv.common.Vnv;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.TagsProvider;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.nio.file.Path;
import java.util.function.Function;

public class VnvItemTagsProvider extends TagsProvider<Item> {
    private final Function<ITag.INamedTag<Block>, ITag.Builder> builderGetter;

    @SuppressWarnings("deprecation") // We need Registry.ITEM. Sorry Forge...
    public VnvItemTagsProvider(DataGenerator gen, VnvBlockTagsProvider blockTags, ExistingFileHelper helper) {
        super(gen, Registry.ITEM, Vnv.ID, helper);
        this.builderGetter = blockTags::getBuilder;
    }

    @Override
    protected void registerTags() {
        copy(BlockTags.LOGS_THAT_BURN, ItemTags.LOGS_THAT_BURN);
        copy(BlockTags.LEAVES,ItemTags.LEAVES);
        copy(BlockTags.WOODEN_FENCES,ItemTags.WOODEN_FENCES);
    }


    protected ITag.Builder getBuilder(ITag.INamedTag<Item> namedTag) {
        return tagToBuilder.computeIfAbsent(namedTag.getId(), id -> new ITag.Builder());
    }

    protected void copy(ITag.INamedTag<Block> blockTag, ITag.INamedTag<Item> itemTag) {
        ITag.Builder itemBuilder = getBuilder(itemTag);
        ITag.Builder blockBuilder = builderGetter.apply(blockTag);
        blockBuilder.streamEntries().forEach(itemBuilder::add);
    }

    @Override
    protected Path makePath(ResourceLocation id) {
        return generator.getOutputFolder().resolve("data/" + id.getNamespace() + "/tags/items/" + id.getPath() + ".json");
    }

    @Override
    public String getName() {
        return "VnvItemTags";
    }
}
