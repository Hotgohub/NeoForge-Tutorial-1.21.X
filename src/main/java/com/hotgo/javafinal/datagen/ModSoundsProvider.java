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

    private void registerSound(Supplier<SoundEvent> soundEvent, String soundFile, float volume, float pitch) {
        add(soundEvent, SoundDefinition.definition()
                .with(sound("javafinal:" + soundFile)
                        .volume(volume)
                        .pitch(pitch))
                .subtitle("sound.javafinal" + soundFile)
        );
    }

    @Override
    public void registerSounds() {
        registerSound(ModSounds.CHISEL_USE, "chisel_use", 1f, 1f);

        registerSound(ModSounds.MAGIC_BLOCK_BREAK, "magic_block_break", 1f, 1f);
        registerSound(ModSounds.MAGIC_BLOCK_FALL, "magic_block_fall", 1f, 1f);
        registerSound(ModSounds.MAGIC_BLOCK_HIT, "magic_block_hit", 1f, 1f);
        registerSound(ModSounds.MAGIC_BLOCK_PLACE, "magic_block_place", 1f, 1f);
        registerSound(ModSounds.MAGIC_BLOCK_STEP, "magic_block_step", 1f, 1f);
    }
}
