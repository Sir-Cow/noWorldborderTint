package sircow.noworldbordertint;

import sircow.noworldbordertint.platform.Services;

public class CommonClass {
    public static void init() {
        if (Services.PLATFORM.isModLoaded("noworldbordertint")) {
            Constants.LOG.info("Initialising NoWorldborderTint");
        }
    }
}