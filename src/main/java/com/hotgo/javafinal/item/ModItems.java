package com.hotgo.javafinal.item;

import com.hotgo.javafinal.JavaFinal;
import com.hotgo.javafinal.item.custom.ChiselItem;
import com.hotgo.javafinal.item.custom.FuelItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
//Basically all items are going to be a 'public static final'//
public class ModItems {
    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(JavaFinal.MOD_ID);
    //Blocks
    public static final DeferredItem<Item> OBAMIUM = ITEMS.register("obamium",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> RAW_OBAMIUM = ITEMS.register("raw_obamium",
            () -> new Item(new Item.Properties()));

    //Custom Items
    public static final DeferredItem<Item> CHISEL = ITEMS.register("chisel",
            () -> new ChiselItem(new Item.Properties()
                    .durability(32)
            ));

    //Consumable Items
    public static final DeferredItem<Item> ORANGE_JUICE = ITEMS.register("orange_juice",
            () -> new Item(new Item.Properties().food(ModFoodProperties.ORANGE_JUICE)));

    //Fuels
    public static final DeferredItem<Item> FIRE_PNG = ITEMS.register("fire_png",
            () -> new FuelItem(new Item.Properties(), 800));
    public static final DeferredItem<Item> THE_WHITE_HOUSE = ITEMS.register("the_white_house",
            () -> new Item(new Item.Properties()));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
