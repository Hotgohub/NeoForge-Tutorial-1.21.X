package com.hotgo.javafinal.datagen;

import com.hotgo.javafinal.JavaFinal;
import com.hotgo.javafinal.sound.ModSounds;
import net.minecraft.data.PackOutput;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.SoundDefinition;
import net.neoforged.neoforge.common.data.SoundDefinitionsProvider;

import java.util.function.Supplier;

public class ModSoundsProvider extends SoundDefinitionsProvider {
    protected ModSoundsProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, JavaFinal.MOD_ID, existingFileHelper);
    }



    @Override
    public void registerSounds() {
        registerSound(ModSounds.CHISEL_USE, 1f, 1f, false);

        registerSound(ModSounds.MAGIC_BLOCK_BREAK, 1f, 1f, false);
        registerSound(ModSounds.MAGIC_BLOCK_FALL, 1f, 1f, false);
        registerSound(ModSounds.MAGIC_BLOCK_HIT, 1f, 1f, false);
        registerSound(ModSounds.MAGIC_BLOCK_PLACE, 1f, 1f, false);
        registerSound(ModSounds.MAGIC_BLOCK_STEP, 1f, 1f, false);

        registerSound(ModSounds.BAR_BRAWL, 1f, 1f, true);
    }

    private void registerSound(Supplier<SoundEvent> soundEvent, float volume, float pitch, boolean stream) {
        String soundName = soundEvent.get().getLocation().getPath();
        add(soundEvent, SoundDefinition.definition()
                .with(sound("javafinal:" + soundName)
                        .volume(volume)
                        .pitch(pitch)
                        .stream(stream))
                .subtitle("sounds.javafinal." + soundName)
        );
    }
}