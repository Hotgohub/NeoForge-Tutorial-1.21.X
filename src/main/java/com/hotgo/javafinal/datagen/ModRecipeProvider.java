package com.hotgo.javafinal.datagen;

import com.hotgo.javafinal.JavaFinal;
import com.hotgo.javafinal.block.ModBlocks;
import com.hotgo.javafinal.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        List<ItemLike> OBAMIUM_SMELTABLES = List.of(ModItems.RAW_OBAMIUM,
                ModBlocks.OBAMIUM_ORE, ModBlocks.OBAMIUM_DEEPSLATE_ORE);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.OBAMIUM_BLOCK.get())
                .pattern("OOO")
                .pattern("OOO")
                .pattern("OOO")
                .define('O', ModItems.OBAMIUM.get())
                .unlockedBy("has_obamium", has(ModItems.OBAMIUM)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.OBAMIUM.get(), 9)
                .requires(ModBlocks.OBAMIUM_BLOCK)
                .unlockedBy("has_obamium_block", has(ModBlocks.OBAMIUM_BLOCK)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.OBAMIUM.get(), 18)
                .requires(ModBlocks.MAGIC_BLOCK)
                .unlockedBy("has_obamium_block", has(ModBlocks.MAGIC_BLOCK))
                .save(recipeOutput, "javafinal:obamium_from_magic_block");

        oreSmelting(recipeOutput, OBAMIUM_SMELTABLES, RecipeCategory.MISC, ModItems.OBAMIUM.get(), 0.25f, 200, "obamium");
        oreBlasting(recipeOutput, OBAMIUM_SMELTABLES, RecipeCategory.MISC, ModItems.OBAMIUM.get(), 0.25f, 100, "obamium");
    }
    protected static void oreSmelting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTime, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput recipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> factory,
                                                                       List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(recipeOutput, JavaFinal.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }
}
