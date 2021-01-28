package com.example.examplemod.init;

import com.example.examplemod.common.blocks.AppleLog;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final RegistryObject<Block> APPLE_LOG = Registration.BLOCKS.register("apple_log", AppleLog::new);
    public static void register(){}

}
