package com.hotgo.javafinal.datagen;

import com.hotgo.javafinal.JavaFinal;
import com.hotgo.javafinal.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
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

        stairsBlock(ModBlocks.OBAMIUM_STAIRS.get(), blockTexture(ModBlocks.OBAMIUM_BLOCK.get()));
        slabBlock(ModBlocks.OBAMIUM_SLAB.get(), blockTexture(ModBlocks.OBAMIUM_BLOCK.get()), blockTexture(ModBlocks.OBAMIUM_BLOCK.get()));

        buttonBlock(ModBlocks.OBAMIUM_BUTTON.get(), blockTexture(ModBlocks.OBAMIUM_BLOCK.get()));
        pressurePlateBlock(ModBlocks.OBAMIUM_PRESSURE_PLATE.get(), blockTexture(ModBlocks.OBAMIUM_BLOCK.get()));

        fenceBlock(ModBlocks.OBAMIUM_FENCE.get(), blockTexture(ModBlocks.OBAMIUM_BLOCK.get()));
        fenceGateBlock(ModBlocks.OBAMIUM_FENCE_GATE.get(), blockTexture(ModBlocks.OBAMIUM_BLOCK.get()));
        wallBlock(ModBlocks.OBAMIUM_WALL.get(), blockTexture(ModBlocks.OBAMIUM_BLOCK.get()));

        doorBlockWithRenderType(ModBlocks.OBAMIUM_DOOR.get(), modLoc("block/obamium_door_bottom"), modLoc("block/obamium_door_top"), "cutout");
        trapdoorBlockWithRenderType(ModBlocks.OBAMIUM_TRAPDOOR.get(), modLoc("block/obamium_trapdoor"), true, "cutout");

        blockItem(ModBlocks.OBAMIUM_STAIRS);
        blockItem(ModBlocks.OBAMIUM_SLAB);
        blockItem(ModBlocks.OBAMIUM_PRESSURE_PLATE);
        blockItem(ModBlocks.OBAMIUM_FENCE_GATE);
        blockItem(ModBlocks.OBAMIUM_TRAPDOOR, "_bottom");
    }

    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }
    private void blockItem(DeferredBlock<?> deferredBlock) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("javafinal:block/" + deferredBlock.getId().getPath()));
    }
    private void blockItem(DeferredBlock<?> deferredBlock, String appendix) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("javafinal:block/" + deferredBlock.getId().getPath() + appendix));
    }
}
