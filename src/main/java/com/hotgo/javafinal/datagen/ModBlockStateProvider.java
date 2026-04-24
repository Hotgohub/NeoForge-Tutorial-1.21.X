package com.hotgo.javafinal.datagen;

import com.hotgo.javafinal.JavaFinal;
import com.hotgo.javafinal.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
//this class helps to create .json files for mostly everything for blocks
public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, JavaFinal.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        //to add new block for data gen Ctrl+D to dupe then add the block after the ModBlocks//
        blockWithItem(ModBlocks.OBAMIUM_BLOCK);

        blockWithItem(ModBlocks.OBAMIUM_ORE);
        blockWithItem(ModBlocks.OBAMIUM_DEEPSLATE_ORE);

        blockWithItem(ModBlocks.MAGIC_BLOCK);
    }

    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }
}
