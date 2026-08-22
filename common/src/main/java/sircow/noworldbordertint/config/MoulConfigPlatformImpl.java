package sircow.noworldbordertint.config;

import io.github.notenoughupdates.moulconfig.platform.MoulConfigPlatform;
import sircow.noworldbordertint.platform.Services;

public class MoulConfigPlatformImpl extends MoulConfigPlatform {
    @Override
    public boolean isDevelopmentEnvironment() {
        return Services.PLATFORM.isDevelopmentEnvironment();
    }
}
