package sircow.noworldbordertint;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = "noworldbordertint", useMetadata=true)
public class NoWorldborderTint {
    public static final Logger LOG = LogManager.getLogger("NoWorldborderTint");

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        NoWorldborderTint.LOG.info("Initialising NoWorldborderTint");
    }
}
