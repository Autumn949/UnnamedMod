package com.example.examplemod.init;

import com.example.examplemod.UnnamedMod;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.item.MinecartItem;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockDisplayReader;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber(modid = UnnamedMod.MOD_ID,bus = Mod.EventBusSubscriber.Bus.MOD,value = Dist.CLIENT)
public class ClientStartup {
    @SubscribeEvent
    public static void registerBlockColors(final ColorHandlerEvent.Block event){
        event.getBlockColors().register((IBlockColor) ModBlocks.APPLE_LEAVES.get(), ModBlocks.APPLE_LEAVES.get());
    }

}
