package dev.unnamed.vnv;

import dev.unnamed.vnv.client.ValleysNVistasClient;
import dev.unnamed.vnv.common.ValleysNVistas;
import dev.unnamed.vnv.data.DataGenerationHandler;
import dev.unnamed.vnv.server.ValleysNVistasServer;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ValleysNVistas.ID)
public class VnvMod {
    public VnvMod() {
        DistExecutor.safeRunForDist(
            () -> ValleysNVistasClient::new,
            () -> ValleysNVistasServer::new
        );

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::complete);
        FMLJavaModLoadingContext.get().getModEventBus().register(new RegistryHandler());
        FMLJavaModLoadingContext.get().getModEventBus().register(new DataGenerationHandler());

        ValleysNVistas.getInstance().preInit();
    }

    private void setup(FMLCommonSetupEvent event) {
        ValleysNVistas.getInstance().init();
    }

    private void complete(FMLLoadCompleteEvent event) {
        ValleysNVistas.getInstance().postInit();
    }
}
