package sircow.noworldbordertint.config;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import io.github.notenoughupdates.moulconfig.gui.GuiContext;
import io.github.notenoughupdates.moulconfig.gui.GuiElementComponent;
import io.github.notenoughupdates.moulconfig.gui.MoulConfigEditor;
import io.github.notenoughupdates.moulconfig.platform.MoulConfigScreenComponent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import sircow.noworldbordertint.CommonClass;

public class ConfigGuiManager {
    public static MoulConfigEditor<NWTConfig> editor = null;

    public static void openConfigGui(String search) {
        CommonClass.configManager.ensureProcessor();
        if (editor == null) editor = new MoulConfigEditor<>(CommonClass.configManager.processor);
        if (search != null) editor.search(search);

        MoulConfigScreenComponent screen = new MoulConfigScreenComponent(Component.empty(), new GuiContext(new GuiElementComponent(editor)), null) {
            @Override
            public void onClose() {
                super.onClose();
                CommonClass.configManager.saveConfig();
            }
        };

        Minecraft.getInstance().execute(() -> Minecraft.getInstance().gui.setScreen(screen));
    }

    public static MoulConfigScreenComponent createConfigScreen(Screen previousScreen) {
        CommonClass.configManager.ensureProcessor();
        if (editor == null) editor = new MoulConfigEditor<>(CommonClass.configManager.processor);

        return new MoulConfigScreenComponent(Component.empty(), new GuiContext(new GuiElementComponent(editor)), previousScreen) {
            @Override
            public void onClose() {
                super.onClose();
                CommonClass.configManager.saveConfig();
            }
        };
    }

    public static <S> void registerCommands(CommandDispatcher<S> dispatcher) {
        dispatcher.register(LiteralArgumentBuilder.<S>literal("noworldbordertint").executes(context -> {
            openConfigGui(null);
            return 1;
        }));
        dispatcher.register(LiteralArgumentBuilder.<S>literal("nwt").executes(context -> {
            openConfigGui(null);
            return 1;
        }));
        dispatcher.register(LiteralArgumentBuilder.<S>literal("nwbt").executes(context -> {
            openConfigGui(null);
            return 1;
        }));
    }
}
