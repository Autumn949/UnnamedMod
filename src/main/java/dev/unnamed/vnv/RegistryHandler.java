package dev.unnamed.vnv;

import dev.unnamed.vnv.common.blocks.VnvBlocks;
import dev.unnamed.vnv.common.entities.VnvEntities;
import dev.unnamed.vnv.common.items.VnvItems;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class RegistryHandler {
    @SubscribeEvent
    public void registerBlocks(RegistryEvent.Register<Block> event) {
        VnvBlocks.register(event.getRegistry());
    }

    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> event) {
        VnvItems.register(event.getRegistry());
    }

    @SubscribeEvent
    public void registerEntities(RegistryEvent.Register<EntityType<?>> event) {
        VnvEntities.register(event.getRegistry());
    }


}
