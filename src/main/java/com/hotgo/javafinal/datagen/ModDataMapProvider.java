package com.hotgo.javafinal.datagen;

import com.hotgo.javafinal.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.Compostable;
import net.neoforged.neoforge.registries.datamaps.builtin.FurnaceFuel;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;

import java.util.concurrent.CompletableFuture;

public class ModDataMapProvider extends DataMapProvider {
    protected ModDataMapProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @Override
    protected void gather() {
        this.builder(NeoForgeDataMaps.FURNACE_FUELS)
                .add(ModItems.THE_WHITE_HOUSE.getId(), new FurnaceFuel(1200), false)
                .add(ModItems.FIRE_PNG.getId(), new FurnaceFuel(2400), false);

        this.builder(NeoForgeDataMaps.COMPOSTABLES)
                .add(ModItems.TOMATO_SEEDS.getId(), new Compostable(0.25f), false)
                .add(ModItems.TOMATO.getId(), new Compostable(0.45f), false);
    }
}
