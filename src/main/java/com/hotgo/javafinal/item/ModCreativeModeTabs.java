package com.hotgo.javafinal.item;

import com.hotgo.javafinal.JavaFinal;
import com.hotgo.javafinal.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, JavaFinal.MOD_ID);
//how old are you? 30? No.
    public static final Supplier<CreativeModeTab> OBAMA_ITEMS_TAB = CREATIVE_MODE_TAB.register("obama_items_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.OBAMIUM.get()))
                    .title(Component.translatable("creativetab.javafinal.obama_items"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.OBAMIUM);
                        output.accept(ModItems.RAW_OBAMIUM);

                        output.accept(ModItems.CHISEL);
                        output.accept(ModItems.ORANGE_JUICE);

                        output.accept(ModItems.FIRE_PNG);
                        output.accept(ModItems.THE_WHITE_HOUSE);

                        output.accept(ModItems.OBAMIUM_SWORD);
                        output.accept(ModItems.OBAMIUM_PICKAXE);
                        output.accept(ModItems.OBAMIUM_SHOVEL);
                        output.accept(ModItems.OBAMIUM_AXE);
                        output.accept(ModItems.OBAMIUM_HOE);

                        output.accept(ModItems.OBAMIUM_HAMMER);

                        output.accept(ModItems.OBAMIUM_HELMET);
                        output.accept(ModItems.OBAMIUM_CHESTPLATE);
                        output.accept(ModItems.OBAMIUM_LEGGINGS);
                        output.accept(ModItems.OBAMIUM_BOOTS);

                        output.accept(ModItems.RED_MAGIC);
                        output.accept(ModItems.RAW_RED_MAGIC);

                        output.accept(ModItems.RED_MAGIC_HELMET);
                        output.accept(ModItems.RED_MAGIC_CHESTPLATE);
                        output.accept(ModItems.RED_MAGIC_LEGGINGS);
                        output.accept(ModItems.RED_MAGIC_BOOTS);

                        output.accept(ModItems.RED_MAGIC_HORSE_ARMOR);


                    }).build());

    public static final Supplier<CreativeModeTab> OBAMA_BLOCKS_TAB = CREATIVE_MODE_TAB.register("obama_blocks_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.OBAMIUM_BLOCK))
                    //DO NOT FORGET THIS LINE \/ IT TELLS THE GAME TO PUT THIS TAB AFTER WHATEVER THE PATH SPECIFIED IS
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(JavaFinal.MOD_ID, "obama_items_tab"))
                    .title(Component.translatable("creativetab.javafinal.obama_blocks"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModBlocks.OBAMIUM_BLOCK);
                        output.accept(ModBlocks.OBAMIUM_ORE);
                        output.accept(ModBlocks.OBAMIUM_DEEPSLATE_ORE);

                        output.accept(ModBlocks.MAGIC_BLOCK);

                        output.accept(ModBlocks.OBAMIUM_STAIRS);
                        output.accept(ModBlocks.OBAMIUM_SLAB);

                        output.accept(ModBlocks.OBAMIUM_PRESSURE_PLATE);
                        output.accept(ModBlocks.OBAMIUM_BUTTON);

                        output.accept(ModBlocks.OBAMIUM_FENCE);
                        output.accept(ModBlocks.OBAMIUM_FENCE_GATE);
                        output.accept(ModBlocks.OBAMIUM_WALL);

                        output.accept(ModBlocks.OBAMIUM_DOOR);
                        output.accept(ModBlocks.OBAMIUM_TRAPDOOR);

                        output.accept(ModBlocks.RED_LAMP);

                        output.accept(ModBlocks.RED_MAGIC_ORE);



                    }).build());



    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
