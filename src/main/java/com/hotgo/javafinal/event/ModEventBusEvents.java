package com.hotgo.javafinal.event;

import com.hotgo.javafinal.JavaFinal;
import com.hotgo.javafinal.entity.ModEntities;
import com.hotgo.javafinal.entity.client.ClippyModel;
import com.hotgo.javafinal.entity.custom.ClippyEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

@EventBusSubscriber(modid = JavaFinal.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ClippyModel.LAYER_LOCATION, ClippyModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.CLIPPY.get(), ClippyEntity.createAttributes().build());
    }
}
