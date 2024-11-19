package dev.sophie;

import eu.pb4.polymer.resourcepack.api.PolymerResourcePackUtils;
import net.fabricmc.api.ModInitializer;
import dev.sophie.block.LavaSpongesBlocks;

public class LavaSponges implements ModInitializer {
    public static final String MOD_ID = "lava-sponges";

    @Override
    public void onInitialize() {
        PolymerResourcePackUtils.addModAssets(MOD_ID);
        PolymerResourcePackUtils.markAsRequired();
        LavaSpongesBlocks.initialize();
    }
}
