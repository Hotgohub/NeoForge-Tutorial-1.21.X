package com.hotgo.javafinal.datagen;

import com.hotgo.javafinal.JavaFinal;
import com.hotgo.javafinal.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, JavaFinal.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.OBAMIUM_BLOCK.get())
                .add(ModBlocks.OBAMIUM_ORE.get())
                .add(ModBlocks.OBAMIUM_DEEPSLATE_ORE.get());

        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.OBAMIUM_DEEPSLATE_ORE.get());

        tag(BlockTags.FENCES).add(ModBlocks.OBAMIUM_FENCE.get());
        tag(BlockTags.FENCE_GATES).add(ModBlocks.OBAMIUM_FENCE_GATE.get());
        tag(BlockTags.WALLS).add(ModBlocks.OBAMIUM_WALL.get());

    }
}
