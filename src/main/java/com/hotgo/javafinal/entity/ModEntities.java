package com.hotgo.javafinal.entity;

import com.hotgo.javafinal.JavaFinal;
import com.hotgo.javafinal.entity.custom.ClippyEntity;
import com.hotgo.javafinal.entity.custom.SkineaterEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, JavaFinal.MOD_ID);

    //.sized parameter is for the size of the hitbox
    public static final Supplier<EntityType<ClippyEntity>> CLIPPY =
            ENTITY_TYPES.register("clippy", () -> EntityType.Builder.of(ClippyEntity::new, MobCategory.CREATURE)
                    .sized(0.75f, 0.85f).build("clippy"));

    public static final Supplier<EntityType<SkineaterEntity>> SKINEATER =
            ENTITY_TYPES.register("skineater", () ->
                    EntityType.Builder.<SkineaterEntity>of(SkineaterEntity::new, MobCategory.MONSTER)
                            .sized(0.6f, 1.9f)
                            .build("skineater")
            );

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
