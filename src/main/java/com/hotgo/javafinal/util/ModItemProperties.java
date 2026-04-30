package com.hotgo.javafinal.util;

import com.hotgo.javafinal.JavaFinal;
import com.hotgo.javafinal.component.ModDataComponents;
import com.hotgo.javafinal.item.ModItems;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;

public class ModItemProperties {
    public static void addCustomItemProperties() {
        ItemProperties.register(ModItems.CHISEL.get(), ResourceLocation.fromNamespaceAndPath(JavaFinal.MOD_ID, "used"),
                (stack, level, entity, seed) -> stack.get(ModDataComponents.COORDINATES) != null ? 1f : 0f);
    }
}
