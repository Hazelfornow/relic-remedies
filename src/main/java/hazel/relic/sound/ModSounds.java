package hazel.relic.sound;

import hazel.relic.RelicRemedies;
import net.minecraft.block.jukebox.JukeboxSong;
import net.minecraft.client.sound.Sound;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {

    public static final SoundEvent MYTH = registerSoundEvent("myth");
    public static final RegistryKey<JukeboxSong> MYTH_KEY =
            RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Identifier.of(RelicRemedies.MOD_ID, "myth"));



    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = Identifier.of(RelicRemedies.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerSounds() {
        RelicRemedies.LOGGER.info("Registering Mod Sounds for " + RelicRemedies.MOD_ID);
    }
}
