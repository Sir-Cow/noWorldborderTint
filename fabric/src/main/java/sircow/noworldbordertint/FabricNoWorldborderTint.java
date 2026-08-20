package sircow.noworldbordertint;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import sircow.noworldbordertint.config.ConfigGuiManager;

public class FabricNoWorldborderTint implements ModInitializer {
    @Override
    public void onInitialize() {
        CommonClass.createConfig();
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> ConfigGuiManager.registerCommands(dispatcher));
        CommonClass.init();
    }
}
