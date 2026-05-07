package com.hotgo.javafinal.worldgen;

import com.hotgo.javafinal.JavaFinal;
import com.hotgo.javafinal.block.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> RED_MAGIC_ORE_PLACED_KEY = registerKey("red_magic_ore_placed");
    public static final ResourceKey<PlacedFeature> NETHER_RED_MAGIC_ORE_PLACED_KEY = registerKey("nether_red_magic_ore_placed");
    public static final ResourceKey<PlacedFeature> END_RED_MAGIC_ORE_PLACED_KEY = registerKey("end_red_magic_ore_placed");

    public static final ResourceKey<PlacedFeature> BLOODWOOD_PLACED_KEY = registerKey("bloodwood_placed");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        //UNIFORM PLACEMENT
        //VerticalAnchor1+VerticalAnchor2=u; pCount/u=d; d*100=%
        //for example: 64+80=144, 12/144=0.083 or 8.3% chance per y-level that one of the PlacedFeatures spawns

        //TRIANGLE PLACEMENT
        //VerticalAnchor1 and VerticalAnchor2 = Lowest likelihood; ex. -64 or 80
        //VerticalAnchor2 - VerticalAnchor1 = Highest likelihood; ex. 80-64=16

        register(context, RED_MAGIC_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_RED_MAGIC_ORE_KEY),
                ModOrePlacement.commonOrePlacement(12, HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(80))));

        register(context, NETHER_RED_MAGIC_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.NETHER_RED_MAGIC_ORE_KEY),
                ModOrePlacement.commonOrePlacement(9, HeightRangePlacement.uniform(VerticalAnchor.absolute(1), VerticalAnchor.absolute(128))));

        register(context, END_RED_MAGIC_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.END_RED_MAGIC_ORE_KEY),
                ModOrePlacement.commonOrePlacement(12, HeightRangePlacement.uniform(VerticalAnchor.absolute(1), VerticalAnchor.absolute(80))));

        //countExtra placement
        //baseValue is how many to spawn, however there is a chance*100=percent that the addedAmmount will also spawn
        //if 1/chance is NOT an integer it will not work
        //1/0.1 = 10 -> completely fine
        //1/0.3 = 3.3... -> will not work
        register(context, BLOODWOOD_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.BLOODWOOD_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBlocks.BLOODWOOD_SAPLING.get()));


    }

    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(JavaFinal.MOD_ID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}