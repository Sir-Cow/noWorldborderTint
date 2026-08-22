package sircow.noworldbordertint;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import sircow.noworldbordertint.config.ConfigGuiManager;

@Mod(Constants.MOD_ID)
public class ForgeNoWorldborderTint {
    public ForgeNoWorldborderTint() {
        CommonClass.createConfig();
        CommonClass.init();
    }

    @Mod.EventBusSubscriber(modid = Constants.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class ClientEvents {
        static {
            MinecraftForge.registerConfigScreen((mc, modsScreen) -> ConfigGuiManager.createConfigScreen(modsScreen));
        }

        @SubscribeEvent
        public static void registerClientCommands(RegisterClientCommandsEvent event) {
            ConfigGuiManager.registerCommands(event.getDispatcher());
        }
    }
}
