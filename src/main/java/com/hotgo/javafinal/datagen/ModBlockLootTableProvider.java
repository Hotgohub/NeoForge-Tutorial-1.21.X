package com.hotgo.javafinal.datagen;

import com.hotgo.javafinal.block.ModBlocks;
import com.hotgo.javafinal.block.custom.TomatoCropBlock;
import com.hotgo.javafinal.item.ModItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {

    protected ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        dropSelf(ModBlocks.OBAMIUM_BLOCK.get());
        // dropSelf(ModBlocks.MAGIC_BLOCK.get());

        add(ModBlocks.OBAMIUM_ORE.get(),
                block -> createOreDrop(ModBlocks.OBAMIUM_ORE.get(), ModItems.RAW_OBAMIUM.get()));
        add(ModBlocks.OBAMIUM_DEEPSLATE_ORE.get(),
                block -> createMultipleOreDrops(ModBlocks.OBAMIUM_DEEPSLATE_ORE.get(), ModItems.RAW_OBAMIUM.get(), 2, 5));
        //add(ModBlocks.OBAMIUM_NETHER_ORE.get(),
        //        block -> createMultipleOreDrops(ModBlocks.OBAMIUM_NETHER_ORE.get(), ModItems.RAW_OBAMIUM.get(), 4, 8));
        //add(ModBlocks.OBAMIUM_END_ORE.get(),
        //        block -> createMultipleOreDrops(ModBlocks.OBAMIUM_END_ORE.get(), ModItems.RAW_OBAMIUM.get(), 3, 6));

        add(ModBlocks.RED_MAGIC_ORE.get(),
                block -> createMultipleOreDrops(ModBlocks.RED_MAGIC_ORE.get(), ModItems.RAW_RED_MAGIC.get(), 2, 5));
        add(ModBlocks.RED_MAGIC_DEEPSLATE_ORE.get(),
                block -> createMultipleOreDrops(ModBlocks.RED_MAGIC_DEEPSLATE_ORE.get(), ModItems.RAW_RED_MAGIC.get(), 2, 5));
        add(ModBlocks.RED_MAGIC_NETHER_ORE.get(),
                block -> createMultipleOreDrops(ModBlocks.RED_MAGIC_NETHER_ORE.get(), ModItems.RAW_RED_MAGIC.get(), 4, 8));
        add(ModBlocks.RED_MAGIC_END_ORE.get(),
                block -> createMultipleOreDrops(ModBlocks.RED_MAGIC_END_ORE.get(), ModItems.RAW_RED_MAGIC.get(), 3, 6));

        dropSelf(ModBlocks.OBAMIUM_STAIRS.get());
        add(ModBlocks.OBAMIUM_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.OBAMIUM_SLAB.get()));

        dropSelf(ModBlocks.OBAMIUM_PRESSURE_PLATE.get());
        dropSelf(ModBlocks.OBAMIUM_BUTTON.get());

        dropSelf(ModBlocks.OBAMIUM_FENCE.get());
        dropSelf(ModBlocks.OBAMIUM_FENCE_GATE.get());
        dropSelf(ModBlocks.OBAMIUM_WALL.get());
        dropSelf(ModBlocks.OBAMIUM_TRAPDOOR.get());

        add(ModBlocks.OBAMIUM_DOOR.get(),
                block -> createDoorTable(ModBlocks.OBAMIUM_DOOR.get()));

        dropSelf(ModBlocks.RED_LAMP.get());

        LootItemCondition.Builder lootItemConditionBuilder = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.TOMATO_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(TomatoCropBlock.AGE, 4));

        this.add(ModBlocks.TOMATO_CROP.get(), this.createCropDrops(ModBlocks.TOMATO_CROP.get(),
                ModItems.TOMATO.get(), ModItems.TOMATO_SEEDS.get(), lootItemConditionBuilder));

        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);

        this.add(ModBlocks.GOJI_BERRY_BUSH.get(), block -> this.applyExplosionDecay(
                block,LootTable.lootTable().withPool(LootPool.lootPool().when(
                                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.GOJI_BERRY_BUSH.get())
                                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SweetBerryBushBlock.AGE, 3))
                                ).add(LootItem.lootTableItem(ModItems.GOJI_BERRIES.get()))
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 3.0F)))
                                .apply(ApplyBonusCount.addUniformBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                ).withPool(LootPool.lootPool().when(
                                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.GOJI_BERRY_BUSH.get())
                                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SweetBerryBushBlock.AGE, 2))
                                ).add(LootItem.lootTableItem(ModItems.GOJI_BERRIES.get()))
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                                .apply(ApplyBonusCount.addUniformBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                )));
    }
    protected LootTable.Builder createMultipleOreDrops(Block pBlock, Item item, float minDrops, float maxDrops) {
        HolderLookup.RegistryLookup<Enchantment> registryLookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock, LootItem.lootTableItem(item)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))
                        .apply(ApplyBonusCount.addOreBonusCount(registryLookup.getOrThrow(Enchantments.FORTUNE)))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
