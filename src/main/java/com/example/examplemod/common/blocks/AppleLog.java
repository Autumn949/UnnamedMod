package com.example.examplemod.common.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.material.Material;

public class AppleLog extends RotatedPillarBlock {
    public AppleLog() {
        super(AbstractBlock.Properties.create(Material.WOOD));
    }
}
