package com.hotgo.javafinal.item;

import com.hotgo.javafinal.JavaFinal;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
//Basically all items are going to be a 'public static final'//
public class ModItems {
    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(JavaFinal.MOD_ID);

    public static final DeferredItem<Item> OBAMIUM = ITEMS.register("obamium",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> RAW_OBAMIUM = ITEMS.register("raw_obamium",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
