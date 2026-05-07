package com.hotgo.javafinal.datagen;

import com.hotgo.javafinal.JavaFinal;
import com.hotgo.javafinal.block.ModBlocks;
import com.hotgo.javafinal.block.custom.GojiBerryBushBlock;
import com.hotgo.javafinal.block.custom.RedLampBlock;
import com.hotgo.javafinal.block.custom.TomatoCropBlock;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.function.Function;

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
        //blockWithItem(ModBlocks.OBAMIUM_NETHER_ORE);
        //blockWithItem(ModBlocks.OBAMIUM_END_ORE);

        blockWithItem(ModBlocks.RED_MAGIC_ORE);
        blockWithItem(ModBlocks.RED_MAGIC_DEEPSLATE_ORE);
        blockWithItem(ModBlocks.RED_MAGIC_NETHER_ORE);
        blockWithItem(ModBlocks.RED_MAGIC_END_ORE);

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

        customLamp();

        makeCrop(((CropBlock) ModBlocks.TOMATO_CROP.get()), "tomato_crop_stage", "tomato_crop_stage");
        makeBush(((SweetBerryBushBlock) ModBlocks.GOJI_BERRY_BUSH.get()), "goji_berry_bush_stage", "goji_berry_bush_stage");

        logBlock(((RotatedPillarBlock) ModBlocks.BLOODWOOD_LOG.get()));
        axisBlock(((RotatedPillarBlock) ModBlocks.BLOODWOOD_WOOD.get()), blockTexture(ModBlocks.BLOODWOOD_LOG.get()), blockTexture(ModBlocks.BLOODWOOD_LOG.get()));
        logBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_BLOODWOOD_LOG.get()));
        axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_BLOODWOOD_WOOD.get()), blockTexture(ModBlocks.STRIPPED_BLOODWOOD_LOG.get()), blockTexture(ModBlocks.STRIPPED_BLOODWOOD_LOG.get()));

        blockItem(ModBlocks.BLOODWOOD_LOG);
        blockItem(ModBlocks.BLOODWOOD_WOOD);
        blockItem(ModBlocks.STRIPPED_BLOODWOOD_LOG);
        blockItem(ModBlocks.STRIPPED_BLOODWOOD_WOOD);

        blockWithItem(ModBlocks.BLOODWOOD_PLANKS);

        leavesBlock(ModBlocks.BLOODWOOD_LEAVES);
        saplingBlock(ModBlocks.BLOODWOOD_SAPLING);
    }

    private void saplingBlock(DeferredBlock<Block> blockRegistryObject) {
        simpleBlock(blockRegistryObject.get(),
                models().cross(BuiltInRegistries.BLOCK.getKey(blockRegistryObject.get()).getPath(), blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    private void leavesBlock(DeferredBlock<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(),
                models().singleTexture(BuiltInRegistries.BLOCK.getKey(blockRegistryObject.get()).getPath(), ResourceLocation.parse("minecraft:block/leaves"),
                        "all", blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }
    public void makeBush(SweetBerryBushBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> states(state, modelName, textureName);

        getVariantBuilder(block).forAllStates(function);
    }

    private ConfiguredModel[] states(BlockState state, String modelName, String textureName) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().cross(modelName + state.getValue(GojiBerryBushBlock.AGE),
                ResourceLocation.fromNamespaceAndPath(JavaFinal.MOD_ID, "block/" + textureName + state.getValue(GojiBerryBushBlock.AGE))).renderType("cutout"));

        return models;
    }

    public void makeCrop(CropBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> states(state, block, modelName, textureName);

        getVariantBuilder(block).forAllStates(function);
    }

    private ConfiguredModel[] states(BlockState state, CropBlock block, String modelName, String textureName) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().crop(modelName + state.getValue(((TomatoCropBlock) block).getAgeProperty()),
                ResourceLocation.fromNamespaceAndPath(JavaFinal.MOD_ID, "block/" + textureName + state.getValue(((TomatoCropBlock) block).getAgeProperty()))).renderType("cutout"));

        return models;
    }



    private void customLamp() {
        getVariantBuilder(ModBlocks.RED_LAMP.get()).forAllStates(state -> {
            if(state.getValue(RedLampBlock.CLICKED)) {
                return new ConfiguredModel[]{new ConfiguredModel(models().cubeAll("red_lamp_on",
                        ResourceLocation.fromNamespaceAndPath(JavaFinal.MOD_ID, "block/" + "red_lamp_on")))};
            } else {
                return new ConfiguredModel[]{new ConfiguredModel(models().cubeAll("red_lamp_off",
                        ResourceLocation.fromNamespaceAndPath(JavaFinal.MOD_ID, "block/" + "red_lamp_off")))};
            }
        });
        simpleBlockItem(ModBlocks.RED_LAMP.get(), models().cubeAll("red_lamp_on",
                ResourceLocation.fromNamespaceAndPath(JavaFinal.MOD_ID, "block/" + "red_lamp_on")));
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
