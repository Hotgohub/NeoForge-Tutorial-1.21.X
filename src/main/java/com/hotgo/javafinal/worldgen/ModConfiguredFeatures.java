package com.hotgo.javafinal.worldgen;

import com.hotgo.javafinal.JavaFinal;
import com.hotgo.javafinal.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.ForkingTrunkPlacer;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class ModConfiguredFeatures {
    //public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_OBAMIUM_ORE_KEY = registerKey("obamium_ore");
    //public static final ResourceKey<ConfiguredFeature<?, ?>> NETHER_OBAMIUM_ORE_KEY = registerKey("nether_obamium_ore");
    //public static final ResourceKey<ConfiguredFeature<?, ?>> END_OBAMIUM_ORE_KEY = registerKey("end_obamium_ore");

    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_RED_MAGIC_ORE_KEY = registerKey("red_magic_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NETHER_RED_MAGIC_ORE_KEY = registerKey("nether_red_magic_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> END_RED_MAGIC_ORE_KEY = registerKey("end_red_magic_ore");

    public static final ResourceKey<ConfiguredFeature<?, ?>> BLOODWOOD_KEY = registerKey("bloodwood");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {

        RuleTest stoneReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest netherrackReplaceables = new BlockMatchTest(Blocks.NETHERRACK);
        RuleTest endReplaceables = new BlockMatchTest(Blocks.END_STONE);

        List<OreConfiguration.TargetBlockState> overworldRedMagicOres = List.of(
                OreConfiguration.target(stoneReplaceables, ModBlocks.RED_MAGIC_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModBlocks.RED_MAGIC_DEEPSLATE_ORE.get().defaultBlockState()));

        register(context, OVERWORLD_RED_MAGIC_ORE_KEY, Feature.ORE, new OreConfiguration(overworldRedMagicOres, 9));
        register(context, NETHER_RED_MAGIC_ORE_KEY, Feature.ORE, new OreConfiguration(netherrackReplaceables,
                ModBlocks.RED_MAGIC_NETHER_ORE.get().defaultBlockState(), 9));
        register(context, END_RED_MAGIC_ORE_KEY, Feature.ORE, new OreConfiguration(endReplaceables,
                ModBlocks.RED_MAGIC_END_ORE.get().defaultBlockState(), 9));

        register(context, BLOODWOOD_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.BLOODWOOD_LOG.get()),
                new ForkingTrunkPlacer(4, 4, 3),

                BlockStateProvider.simple(ModBlocks.BLOODWOOD_LEAVES.get()),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(3), 3),

                new TwoLayersFeatureSize(1, 0, 2)).build());
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(JavaFinal.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
