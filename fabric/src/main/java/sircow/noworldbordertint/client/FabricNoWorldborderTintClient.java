package sircow.noworldbordertint.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import sircow.noworldbordertint.config.ConfigGuiManager;

public class FabricNoWorldborderTintClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientCommandRegistrationCallback.EVENT.register(
                (dispatcher, registryAccess) -> ConfigGuiManager.registerCommands(dispatcher)
        );
    }
}
