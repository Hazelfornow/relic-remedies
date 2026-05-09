package hazel.relic;

import hazel.relic.registry.RelicsItems;
import hazel.relic.sound.ModSounds;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RelicRemedies implements ModInitializer {
	public static final String MOD_ID = "relic-remedies";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		RelicsItems.innit();

		ModSounds.registerSounds();
	}
}