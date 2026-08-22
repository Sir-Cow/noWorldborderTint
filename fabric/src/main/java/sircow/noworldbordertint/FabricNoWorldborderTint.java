package sircow.noworldbordertint;

import net.fabricmc.api.ModInitializer;

public class FabricNoWorldborderTint implements ModInitializer {
    @Override
    public void onInitialize() {
        CommonClass.createConfig();
        CommonClass.init();
    }
}
