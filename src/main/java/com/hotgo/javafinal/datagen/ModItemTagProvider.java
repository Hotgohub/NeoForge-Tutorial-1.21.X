package com.hotgo.javafinal.datagen;

import com.hotgo.javafinal.JavaFinal;
import com.hotgo.javafinal.item.ModItems;
import com.hotgo.javafinal.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                              CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, JavaFinal.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ModTags.Items.TRANSFORMABLE_ITEMS)
                .add(ModItems.OBAMIUM.get())
                .add(ModItems.RAW_OBAMIUM.get())
                .add(Items.COAL)
                .add(Items.STICK)
                .add(Items.COMPASS);

        tag(ItemTags.SWORDS)
                .add(ModItems.OBAMIUM_SWORD.get());
        tag(ItemTags.PICKAXES)
                .add(ModItems.OBAMIUM_PICKAXE.get());
        tag(ItemTags.SHOVELS)
                .add(ModItems.OBAMIUM_SHOVEL.get());
        tag(ItemTags.AXES)
                .add(ModItems.OBAMIUM_AXE.get());
        tag(ItemTags.HOES)
                .add(ModItems.OBAMIUM_HOE.get());

        this.tag(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.OBAMIUM_HELMET.get())
                .add(ModItems.OBAMIUM_CHESTPLATE.get())
                .add(ModItems.OBAMIUM_LEGGINGS.get())
                .add(ModItems.OBAMIUM_BOOTS.get())
                .add(ModItems.RED_MAGIC_HELMET.get())
                .add(ModItems.RED_MAGIC_CHESTPLATE.get())
                .add(ModItems.RED_MAGIC_LEGGINGS.get())
                .add(ModItems.RED_MAGIC_BOOTS.get());

    }
}
