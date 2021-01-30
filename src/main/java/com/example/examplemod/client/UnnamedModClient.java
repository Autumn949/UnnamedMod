package com.example.examplemod.client;

import com.example.examplemod.common.UnnamedMod;
import com.example.examplemod.common.blocks.ModBlocks;
import com.example.examplemod.common.items.ModItems;

public class UnnamedModClient extends UnnamedMod {

    @Override
    public void init() {
        super.init();
        ModBlocks.setupClient();
        ModItems.setupClient();
    }
}
