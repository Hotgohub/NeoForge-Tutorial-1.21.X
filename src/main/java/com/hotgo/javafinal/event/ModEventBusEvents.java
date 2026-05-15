package com.hotgo.javafinal.event;

import com.hotgo.javafinal.JavaFinal;
import com.hotgo.javafinal.entity.ModEntities;
import com.hotgo.javafinal.entity.client.ClippyModel;
import com.hotgo.javafinal.entity.client.SkineaterModel;
import com.hotgo.javafinal.entity.custom.ClippyEntity;
import com.hotgo.javafinal.entity.custom.SkineaterEntity;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;

@EventBusSubscriber(modid = JavaFinal.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ClippyModel.LAYER_LOCATION, ClippyModel::createBodyLayer);
        event.registerLayerDefinition(SkineaterModel.LAYER_LOCATION, SkineaterModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.CLIPPY.get(), ClippyEntity.createAttributes().build());
        event.put(ModEntities.SKINEATER.get(), SkineaterEntity.createAttributes().build());
    }

    @SubscribeEvent
    public static void registerSpawnPlacements(RegisterSpawnPlacementsEvent event) {
        event.register(ModEntities.CLIPPY.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
    }
}
