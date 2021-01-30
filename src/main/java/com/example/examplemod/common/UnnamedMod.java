package com.example.examplemod.common;

import com.example.examplemod.common.blocks.ModBlocks;
import com.example.examplemod.common.items.ModItems;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class UnnamedMod {
    public static final String ID = "unnamedmod";
    public static final IEventBus FORGE_EVENT_BUS = MinecraftForge.EVENT_BUS;
    public static final IEventBus MOD_EVENT_BUS = FMLJavaModLoadingContext.get().getModEventBus();
    public static final Logger LOGGER = LogManager.getLogger("UnnamedMod");


    public UnnamedMod() {
        instance = this;
    }

    public void preInit() {
        setupEventHandlers();
    }

    public void init() {
        ModBlocks.setup();
        ModItems.setup();
    }

    public void postInit() {

    }


    protected void setupEventHandlers() {

    }


    private static UnnamedMod instance;

    public static UnnamedMod getInstance() {
        return instance;
    }

    public static ResourceLocation id(String path) {
        int colon = path.indexOf(':');
        if (colon >= 0) {
            return new ResourceLocation(path.substring(0, colon), path.substring(colon + 1));
        }
        return new ResourceLocation(ID, path);
    }

    public static String idstr(String path) {
        if (path.indexOf(':') >= 0) {
            return path;
        }
        return ID + ":" + path;
    }
}
