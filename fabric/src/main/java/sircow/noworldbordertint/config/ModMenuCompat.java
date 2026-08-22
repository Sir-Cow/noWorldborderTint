package sircow.noworldbordertint.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import io.github.notenoughupdates.moulconfig.gui.GuiContext;
import io.github.notenoughupdates.moulconfig.gui.GuiElementComponent;
import io.github.notenoughupdates.moulconfig.gui.MoulConfigEditor;
import io.github.notenoughupdates.moulconfig.platform.MoulConfigScreenComponent;
import net.minecraft.network.chat.Component;
import sircow.noworldbordertint.CommonClass;

public class ModMenuCompat implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> {
            CommonClass.configManager.ensureProcessor();
            MoulConfigEditor<NWTConfig> editor = new MoulConfigEditor<>(CommonClass.configManager.processor);
            return new MoulConfigScreenComponent(Component.empty(), new GuiContext(new GuiElementComponent(editor)), null) {
                @Override
                public void onClose() {
                    super.onClose();
                    CommonClass.configManager.saveConfig();
                }
            };
        };
    }
}
