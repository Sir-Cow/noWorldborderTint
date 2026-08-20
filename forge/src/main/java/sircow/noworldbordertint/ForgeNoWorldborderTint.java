package sircow.noworldbordertint;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLEnvironment;
import sircow.noworldbordertint.config.ConfigGuiManager;

@Mod(Constants.MOD_ID)
public class ForgeNoWorldborderTint {
    public ForgeNoWorldborderTint() {
        if (FMLEnvironment.dist == Dist.CLIENT) {
            CommonClass.createConfig();
            MinecraftForge.registerConfigScreen((mc, modsScreen) -> ConfigGuiManager.createConfigScreen(modsScreen));
            RegisterClientCommandsEvent.BUS.addListener(event -> ConfigGuiManager.registerCommands(event.getDispatcher()));
        }
        CommonClass.init();
    }
}
