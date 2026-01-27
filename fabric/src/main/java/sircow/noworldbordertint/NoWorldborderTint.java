package sircow.noworldbordertint;

import com.mojang.brigadier.Command;
import io.github.notenoughupdates.moulconfig.managed.ManagedConfig;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.Minecraft;
import sircow.noworldbordertint.config.ConfigGuiManager;
import sircow.noworldbordertint.config.ConfigManager;
import sircow.noworldbordertint.config.NWTConfig;

import java.io.File;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class NoWorldborderTint implements ModInitializer {
    public static NWTConfig config;
    public static ConfigManager configManager;

    @Override
    public void onInitialize() {
        createConfig();
        CommonClass.init();
    }

    private void createConfig() {
        configManager = new ConfigManager();
        configManager.firstLoad();

        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            Command<FabricClientCommandSource> action = context -> {
                Minecraft.getInstance().execute(() -> {
                    ConfigGuiManager.openConfigGui(null);
                });
                return 1;
            };

            dispatcher.register(literal("noworldbordertint").executes(action));
            dispatcher.register(literal("nwt").executes(action));
            dispatcher.register(literal("nwbt").executes(action));
        });
    }
}
