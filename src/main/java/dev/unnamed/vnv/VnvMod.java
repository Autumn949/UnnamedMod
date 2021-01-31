package dev.unnamed.vnv;

import dev.unnamed.vnv.client.VnvClient;
import dev.unnamed.vnv.common.Vnv;
import dev.unnamed.vnv.data.DataGenerationHandler;
import dev.unnamed.vnv.server.VnvServer;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Vnv.ID)
public class VnvMod {
    public VnvMod() {
        DistExecutor.safeRunForDist(
            () -> VnvClient::new,
            () -> VnvServer::new
        );

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::complete);
        FMLJavaModLoadingContext.get().getModEventBus().register(new RegistryHandler());
        FMLJavaModLoadingContext.get().getModEventBus().register(new DataGenerationHandler());

        Vnv.getInstance().preInit();
    }

    private void setup(FMLCommonSetupEvent event) {
        Vnv.getInstance().init();
    }

    private void complete(FMLLoadCompleteEvent event) {
        Vnv.getInstance().postInit();
    }
}
