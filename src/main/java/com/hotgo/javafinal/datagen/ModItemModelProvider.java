package com.hotgo.javafinal.datagen;

import com.hotgo.javafinal.JavaFinal;
import com.hotgo.javafinal.item.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
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
    }
}
