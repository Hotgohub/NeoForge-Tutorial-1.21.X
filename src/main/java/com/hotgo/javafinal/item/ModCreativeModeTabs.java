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

                    }).build());



    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
