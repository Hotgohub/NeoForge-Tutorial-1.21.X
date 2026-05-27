package com.hotgo.javafinal.villager;

import com.google.common.collect.ImmutableSet;
import com.hotgo.javafinal.JavaFinal;
import com.hotgo.javafinal.block.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModVillagers {
    public static final DeferredRegister<PoiType> POI_TYPES =
            DeferredRegister.create(BuiltInRegistries.POINT_OF_INTEREST_TYPE, JavaFinal.MOD_ID);
    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS =
            DeferredRegister.create(BuiltInRegistries.VILLAGER_PROFESSION, JavaFinal.MOD_ID);

    public static final Holder<PoiType> DINGUS_POI = POI_TYPES.register("dingus_poi",
            () -> new PoiType(ImmutableSet.copyOf(ModBlocks.CHAIR.get().getStateDefinition().getPossibleStates()), 1, 1));

    public static final Holder<VillagerProfession> DINGUS = VILLAGER_PROFESSIONS.register("dingus",
            () -> new VillagerProfession("dingus", holder -> holder.value() == DINGUS_POI.value(),
                    poiTypeHolder -> poiTypeHolder.value() == DINGUS_POI.value(), ImmutableSet.of(), ImmutableSet.of(),
                    SoundEvents.EVOKER_PREPARE_WOLOLO));

    public static void register(IEventBus eventBus) {
        POI_TYPES.register(eventBus);
        VILLAGER_PROFESSIONS.register(eventBus);
    }
}
