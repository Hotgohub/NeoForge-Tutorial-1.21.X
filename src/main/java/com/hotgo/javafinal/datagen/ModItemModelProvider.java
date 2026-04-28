package com.hotgo.javafinal.datagen;

import com.hotgo.javafinal.JavaFinal;
import com.hotgo.javafinal.block.ModBlocks;
import com.hotgo.javafinal.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;

//This class helps to create .json files for Item Models
public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, JavaFinal.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        //to add new item for data gen Ctrl+D to dupe then add the item after the ModItems//
        basicItem(ModItems.OBAMIUM.get());
        basicItem(ModItems.RAW_OBAMIUM.get());

        basicItem(ModItems.CHISEL.get());
        basicItem(ModItems.ORANGE_JUICE.get());
        basicItem(ModItems.FIRE_PNG.get());
        basicItem(ModItems.THE_WHITE_HOUSE.get());

        buttonItem(ModBlocks.OBAMIUM_BUTTON, ModBlocks.OBAMIUM_BLOCK);
        fenceItem(ModBlocks.OBAMIUM_FENCE, ModBlocks.OBAMIUM_BLOCK);
        wallItem(ModBlocks.OBAMIUM_WALL, ModBlocks.OBAMIUM_BLOCK);

        basicItem(ModBlocks.OBAMIUM_DOOR.asItem());

        handHeldItem(ModItems.OBAMIUM_SWORD);
        handHeldItem(ModItems.OBAMIUM_PICKAXE);
        handHeldItem(ModItems.OBAMIUM_SHOVEL);
        handHeldItem(ModItems.OBAMIUM_AXE);
        handHeldItem(ModItems.OBAMIUM_HOE);
        handHeldItem(ModItems.OBAMIUM_HAMMER);
    }
    public void buttonItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/button_inventory"))
                .texture("texture",  ResourceLocation.fromNamespaceAndPath(JavaFinal.MOD_ID,
                        "block/" + baseBlock.getId().getPath()));
    }

    public void fenceItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/fence_inventory"))
                .texture("texture",  ResourceLocation.fromNamespaceAndPath(JavaFinal.MOD_ID,
                        "block/" + baseBlock.getId().getPath()));
    }

    public void wallItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/wall_inventory"))
                .texture("wall",  ResourceLocation.fromNamespaceAndPath(JavaFinal.MOD_ID,
                        "block/" + baseBlock.getId().getPath()));
    }

    private ItemModelBuilder handHeldItem(DeferredItem<?> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/handheld")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(JavaFinal.MOD_ID, "item/" + item.getId().getPath()));
    }
}
