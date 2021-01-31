package dev.unnamed.vnv.client;

import dev.unnamed.vnv.common.Vnv;
import dev.unnamed.vnv.common.blocks.VnvBlocks;
import dev.unnamed.vnv.common.items.VnvItems;

public class VnvClient extends Vnv {

    @Override
    public void init() {
        super.init();
        VnvBlocks.setupClient();
        VnvItems.setupClient();
    }
}
