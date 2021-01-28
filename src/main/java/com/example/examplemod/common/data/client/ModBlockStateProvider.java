package com.example.examplemod.common.data.client;


import com.example.examplemod.UnnamedMod;
import com.example.examplemod.init.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockStateProvider extends BlockStateProvider {
        public ModBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
            super(gen, UnnamedMod.MOD_ID, exFileHelper);
        }

        @Override
        protected void registerStatesAndModels() {
            simpleBlock(ModBlocks.APPLE_LOG.get());

        }
    }