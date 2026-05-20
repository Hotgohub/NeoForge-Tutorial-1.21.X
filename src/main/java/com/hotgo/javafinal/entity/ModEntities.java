package com.hotgo.javafinal.entity;

import com.hotgo.javafinal.JavaFinal;
import com.hotgo.javafinal.entity.custom.ChairEntity;
import com.hotgo.javafinal.entity.custom.ClippyEntity;
import com.hotgo.javafinal.entity.custom.SkineaterEntity;
import com.hotgo.javafinal.entity.custom.TomahawkProjectileEntity;
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

    public static final Supplier<EntityType<TomahawkProjectileEntity>> TOMAHAWK =
            ENTITY_TYPES.register("tomahawk", () -> EntityType.Builder.<TomahawkProjectileEntity>of(TomahawkProjectileEntity::new, MobCategory.MISC)
                    .sized(0.5f, 1.15f)
                    .build("tomahawk"));

    public static final Supplier<EntityType<ChairEntity>> CHAIR_ENTITY =
            ENTITY_TYPES.register("chair_entity", () -> EntityType.Builder.of(ChairEntity::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f)
                    .build("chair_entity"));


    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
