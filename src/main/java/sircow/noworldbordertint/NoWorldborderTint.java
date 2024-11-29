package sircow.noworldbordertint;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NoWorldborderTint implements ModInitializer {
	public static final String MOD_ID = "no-worldborder-tint";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Initialising NoWorldborderTint");
	}
}