package com.example.examplemod.data.models.stategen;

import com.example.examplemod.data.models.modelgen.IModelGen;
import com.google.gson.JsonElement;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;

import java.util.function.BiConsumer;

public interface IBlockStateGen {
    JsonElement makeJson(ResourceLocation id, Block block);
    void getModels(BiConsumer<String, IModelGen> consumer);
}
