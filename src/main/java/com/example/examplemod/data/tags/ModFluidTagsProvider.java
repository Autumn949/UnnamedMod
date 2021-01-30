package com.example.examplemod.data.tags;

import com.example.examplemod.common.UnnamedMod;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.TagsProvider;
import net.minecraft.fluid.Fluid;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.nio.file.Path;

public class ModFluidTagsProvider extends TagsProvider<Fluid> {
    @SuppressWarnings("deprecation") // We need Registry.FLUID. Sorry Forge...
    public ModFluidTagsProvider(DataGenerator gen, ExistingFileHelper helper) {
        super(gen, Registry.FLUID, UnnamedMod.ID, helper);
    }

    @Override
    protected void registerTags() {
    }

    @Override
    protected Path makePath(ResourceLocation id) {
        return generator.getOutputFolder().resolve("data/" + id.getNamespace() + "/tags/fluids/" + id.getPath() + ".json");
    }

    @Override
    public String getName() {
        return "ModFluidTags";
    }
}
