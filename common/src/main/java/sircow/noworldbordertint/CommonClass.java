package sircow.noworldbordertint;

import sircow.noworldbordertint.config.ConfigManager;
import sircow.noworldbordertint.config.NWTConfig;
import sircow.noworldbordertint.platform.Services;

public class CommonClass {
    public static NWTConfig config;
    public static ConfigManager configManager;

    public static void createConfig() {
        configManager = new ConfigManager();
        configManager.firstLoad();
    }

    public static void init() {
        if (Services.PLATFORM.isModLoaded("noworldbordertint")) {
            Constants.LOG.info("Initialising {}", Constants.MOD_NAME);
        }
    }
}
