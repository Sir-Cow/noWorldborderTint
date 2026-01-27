package sircow.noworldbordertint.config;

import io.github.notenoughupdates.moulconfig.gui.GuiContext;
import io.github.notenoughupdates.moulconfig.gui.GuiElementComponent;
import io.github.notenoughupdates.moulconfig.gui.MoulConfigEditor;
import io.github.notenoughupdates.moulconfig.platform.MoulConfigScreenComponent;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import sircow.noworldbordertint.NoWorldborderTint;

public class ConfigGuiManager {
    public static MoulConfigEditor<NWTConfig> editor = null;

    public static void openConfigGui(String search) {
        if (editor == null) editor = new MoulConfigEditor<>(NoWorldborderTint.configManager.processor);
        if (search != null) editor.search(search);

        MoulConfigScreenComponent screen = new MoulConfigScreenComponent(Component.empty(), new GuiContext(new GuiElementComponent(editor)), null) {
            @Override
            public void onClose() {
                super.onClose();
                NoWorldborderTint.configManager.saveConfig();
            }
        };

        Minecraft.getInstance().setScreen(screen);
    }
}
