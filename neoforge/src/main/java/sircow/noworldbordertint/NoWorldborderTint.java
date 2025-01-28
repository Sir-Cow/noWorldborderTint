package sircow.noworldbordertint;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(Constants.MOD_ID)
public class NoWorldborderTint {
    public NoWorldborderTint(IEventBus eventBus) {
        CommonClass.init();
    }
}
