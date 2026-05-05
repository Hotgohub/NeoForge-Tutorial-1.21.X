package com.hotgo.javafinal.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.PaintingVariantTags;
import net.minecraft.world.entity.decoration.PaintingVariant;

import java.util.concurrent.CompletableFuture;

public class ModPaintingTagProvider extends TagsProvider<PaintingVariant> {

    public ModPaintingTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, Registries.PAINTING_VARIANT, lookupProvider);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(PaintingVariantTags.PLACEABLE)
                .add(ModPaintingProvider.POCHITA)
                .add(ModPaintingProvider.ME);
    }

    @Override
    public String getName() {
        return "Mod Painting Tags";
    }
}