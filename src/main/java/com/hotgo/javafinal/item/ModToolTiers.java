package com.hotgo.javafinal.item;

import com.hotgo.javafinal.util.ModTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;

public class ModToolTiers {
    public static final Tier OBAMIUM = new SimpleTier(ModTags.Blocks.INCORRECT_FOR_OBAMIUM_TOOL,
            1400, 4f, 3f, 28, () -> Ingredient.of(ModItems.OBAMIUM));
}
