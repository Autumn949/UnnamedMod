package com.example.examplemod.common.blocks;

import com.example.examplemod.common.UnnamedMod;
import com.google.common.collect.Lists;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

import java.util.List;

@ObjectHolder("unnamedmod")
public abstract class ModBlocks {
    private static final List<Block> REGISTRY = Lists.newArrayList();

    //
    // BLOCKS
    //

    public static final Block APPLE_TREE_LOG = appleTreeLog("apple_tree_log");


    //
    // REGISTRY
    //

    public static void register(IForgeRegistry<Block> registry) {
        REGISTRY.forEach(registry::register);
    }

    public static void setup() {

    }

    @OnlyIn(Dist.CLIENT)
    public static void setupClient() {

    }


    //
    // FACTORY METHODS
    //

    private static <T extends Block> T block(String id, T block) {
        block.setRegistryName(UnnamedMod.id(id));
        REGISTRY.add(block);
        return block;
    }

    private static Block appleTreeLog(String id) {
        return block(id, new RotatedPillarBlock(
            AbstractBlock.Properties.create(Material.WOOD, MaterialColor.WOOD)
                                    .sound(SoundType.WOOD)
                                    .hardnessAndResistance(2f)
                                    .harvestTool(ToolType.AXE)
        ));
    }
}
