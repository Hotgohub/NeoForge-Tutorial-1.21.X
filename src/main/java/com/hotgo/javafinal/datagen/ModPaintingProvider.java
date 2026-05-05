package com.hotgo.javafinal.datagen;

import com.hotgo.javafinal.JavaFinal;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModPaintingProvider extends DatapackBuiltinEntriesProvider {

    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.PAINTING_VARIANT, ModPaintingProvider::bootstrap);

    public ModPaintingProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(JavaFinal.MOD_ID));
    }

    private static void bootstrap(BootstrapContext<PaintingVariant> context) {
        register(context, "pochita", 4, 4);
        register(context, "me", 2, 2);
    }

    private static void register(BootstrapContext<PaintingVariant> context, String name, int width, int height) {
        ResourceLocation location = ResourceLocation.fromNamespaceAndPath(JavaFinal.MOD_ID, name);
        ResourceKey<PaintingVariant> key = ResourceKey.create(Registries.PAINTING_VARIANT, location);
        context.register(key, new PaintingVariant(width, height, location));
    }

    @Override
    public String getName() {
        return "Mod Paintings";
    }

    public static final ResourceKey<PaintingVariant> POCHITA = ResourceKey.create(
            Registries.PAINTING_VARIANT, ResourceLocation.fromNamespaceAndPath(JavaFinal.MOD_ID, "pochita"));

    public static final ResourceKey<PaintingVariant> ME = ResourceKey.create(
            Registries.PAINTING_VARIANT, ResourceLocation.fromNamespaceAndPath(JavaFinal.MOD_ID, "me"));
}