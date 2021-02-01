package dev.unnamed.vnv.client;

import dev.unnamed.vnv.common.ValleysNVistas;
import dev.unnamed.vnv.common.blocks.VnvBlocks;
import dev.unnamed.vnv.common.items.VnvItems;

public class ValleysNVistasClient extends ValleysNVistas {

    @Override
    public void init() {
        super.init();
        VnvBlocks.setupClient();
        VnvItems.setupClient();
    }


}
