package com.example.examplemod;

import com.example.examplemod.client.UnnamedModClient;
import com.example.examplemod.common.UnnamedMod;
import com.example.examplemod.data.DataGenerationHandler;
import com.example.examplemod.server.UnnamedModServer;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("unnamedmod")
public class UnnamedModBootstrap {
    public UnnamedModBootstrap() {
        DistExecutor.safeRunForDist(
            () -> UnnamedModClient::new,
            () -> UnnamedModServer::new
        );

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::complete);
        FMLJavaModLoadingContext.get().getModEventBus().register(new RegistryHandler());
        FMLJavaModLoadingContext.get().getModEventBus().register(new DataGenerationHandler());

        UnnamedMod.getInstance().preInit();
    }

    private void setup(FMLCommonSetupEvent event) {
        UnnamedMod.getInstance().init();
    }

    private void complete(FMLLoadCompleteEvent event) {
        UnnamedMod.getInstance().postInit();
    }
}
