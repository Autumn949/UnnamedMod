package dev.unnamed.vnv.data.tags;

import dev.unnamed.vnv.common.ValleysNVistas;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.TagsProvider;
import net.minecraft.fluid.Fluid;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.nio.file.Path;

public class VnvFluidTagsProvider extends TagsProvider<Fluid> {
    @SuppressWarnings("deprecation") // We need Registry.FLUID. Sorry Forge...
    public VnvFluidTagsProvider(DataGenerator gen, ExistingFileHelper helper) {
        super(gen, Registry.FLUID, ValleysNVistas.ID, helper);
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
        return "VnvFluidTags";
    }
}
