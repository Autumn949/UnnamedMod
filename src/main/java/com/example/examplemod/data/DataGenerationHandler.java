package com.example.examplemod.data;

import com.example.examplemod.data.loottables.ModLootTableProvider;
import com.example.examplemod.data.models.ModStateModelProvider;
import com.example.examplemod.data.recipes.ModRecipeProvider;
import com.example.examplemod.data.recipes.ModStonecuttingRecipeProvider;
import com.example.examplemod.data.tags.ModBlockTagsProvider;
import com.example.examplemod.data.tags.ModFluidTagsProvider;
import com.example.examplemod.data.tags.ModItemTagsProvider;
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
            gen.addProvider(new ModStateModelProvider(gen));
        }
        if (evt.includeServer()) {
            gen.addProvider(new ModLootTableProvider(gen));

            ModBlockTagsProvider blockTags = new ModBlockTagsProvider(gen, efh);
            gen.addProvider(blockTags);
            gen.addProvider(new ModItemTagsProvider(gen, blockTags, efh));
            gen.addProvider(new ModFluidTagsProvider(gen, efh));

            gen.addProvider(new ModRecipeProvider(gen));
            gen.addProvider(new ModStonecuttingRecipeProvider(gen));
        }
    }
}
