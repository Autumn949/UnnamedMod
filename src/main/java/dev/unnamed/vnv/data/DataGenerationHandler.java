package dev.unnamed.vnv.data;

import dev.unnamed.vnv.data.loottables.VnvLootTableProvider;
import dev.unnamed.vnv.data.models.VnvStateModelProvider;
import dev.unnamed.vnv.data.recipes.VnvRecipeProvider;
import dev.unnamed.vnv.data.recipes.VnvStonecuttingRecipeProvider;
import dev.unnamed.vnv.data.tags.VnvBlockTagsProvider;
import dev.unnamed.vnv.data.tags.VnvFluidTagsProvider;
import dev.unnamed.vnv.data.tags.VnvItemTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

public class DataGenerationHandler {

    @SubscribeEvent
    public void onGatherData(GatherDataEvent evt) {
        DataGenerator gen = evt.getGenerator();
        ExistingFileHelper efh = evt.getExistingFileHelper();

        if (evt.includeClient()) {
            gen.addProvider(new VnvStateModelProvider(gen));
        }
        if (evt.includeServer()) {
            gen.addProvider(new VnvLootTableProvider(gen));

            VnvBlockTagsProvider blockTags = new VnvBlockTagsProvider(gen, efh);
            gen.addProvider(blockTags);
            gen.addProvider(new VnvItemTagsProvider(gen, blockTags, efh));
            gen.addProvider(new VnvFluidTagsProvider(gen, efh));

            gen.addProvider(new VnvRecipeProvider(gen));
            gen.addProvider(new VnvStonecuttingRecipeProvider(gen));
        }
    }
}
