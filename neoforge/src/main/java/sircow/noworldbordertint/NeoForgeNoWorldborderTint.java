package sircow.noworldbordertint;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterClientCommandsEvent;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.common.NeoForge;
import sircow.noworldbordertint.config.ConfigGuiManager;

@Mod(Constants.MOD_ID)
public class NeoForgeNoWorldborderTint {
    public NeoForgeNoWorldborderTint(IEventBus eventBus) {
        CommonClass.init();
    }

    @Mod(value = Constants.MOD_ID, dist = Dist.CLIENT)
    public static class Client {
        public Client(ModContainer container) {
            CommonClass.createConfig();
            container.registerExtensionPoint(IConfigScreenFactory.class, (modContainer, modListScreen) -> ConfigGuiManager.createConfigScreen(modListScreen));
            NeoForge.EVENT_BUS.addListener(RegisterClientCommandsEvent.class, event -> ConfigGuiManager.registerCommands(event.getDispatcher()));
        }
    }
}
