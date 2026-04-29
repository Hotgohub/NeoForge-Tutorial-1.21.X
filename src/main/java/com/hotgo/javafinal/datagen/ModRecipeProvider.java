package com.hotgo.javafinal.datagen;

import com.hotgo.javafinal.JavaFinal;
import com.hotgo.javafinal.block.ModBlocks;
import com.hotgo.javafinal.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
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
        List<ItemLike> RED_MAGIC_SMELTABLES = List.of(ModItems.RAW_RED_MAGIC,
                ModBlocks.RED_MAGIC_ORE);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.OBAMIUM_BLOCK.get())
                .pattern("OOO")
                .pattern("OOO")
                .pattern("OOO")
                .define('O', ModItems.OBAMIUM.get())
                .unlockedBy("has_obamium", has(ModItems.OBAMIUM)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.RED_LAMP.get())
                .pattern(" G ")
                .pattern("GRG")
                .pattern(" G ")
                .define('R', ModItems.RED_MAGIC.get())
                .define('G', Items.GLASS)
                .unlockedBy("has_red_magic", has(ModItems.RED_MAGIC)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.OBAMIUM_SWORD.get())
                .pattern(" O ")
                .pattern(" O ")
                .pattern(" S ")
                .define('O', ModItems.OBAMIUM.get())
                .define('S', Items.STICK)
                .unlockedBy("has_obamium", has(ModItems.OBAMIUM)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.OBAMIUM_PICKAXE.get())
                .pattern("OOO")
                .pattern(" S ")
                .pattern(" S ")
                .define('O', ModItems.OBAMIUM.get())
                .define('S', Items.STICK)
                .unlockedBy("has_obamium", has(ModItems.OBAMIUM)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.OBAMIUM_AXE.get())
                .pattern(" OO")
                .pattern(" SO")
                .pattern(" S ")
                .define('O', ModItems.OBAMIUM.get())
                .define('S', Items.STICK)
                .unlockedBy("has_obamium", has(ModItems.OBAMIUM)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.OBAMIUM_SHOVEL.get())
                .pattern(" O ")
                .pattern(" S ")
                .pattern(" S ")
                .define('O', ModItems.OBAMIUM.get())
                .define('S', Items.STICK)
                .unlockedBy("has_obamium", has(ModItems.OBAMIUM)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.OBAMIUM_HOE.get())
                .pattern(" OO")
                .pattern(" S ")
                .pattern(" S ")
                .define('O', ModItems.OBAMIUM.get())
                .define('S', Items.STICK)
                .unlockedBy("has_obamium", has(ModItems.OBAMIUM)).save(recipeOutput);

       ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.RED_MAGIC_HELMET.get())
               .pattern("RRR")
               .pattern("R R")
               .define('R', ModItems.RED_MAGIC.get())
               .unlockedBy("has_red_magic", has(ModItems.RED_MAGIC)).save(recipeOutput);
       ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.RED_MAGIC_CHESTPLATE.get())
               .pattern("R R")
               .pattern("RRR")
               .pattern("RRR")
               .define('R', ModItems.RED_MAGIC.get())
               .unlockedBy("has_red_magic", has(ModItems.RED_MAGIC)).save(recipeOutput);
       ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.RED_MAGIC_LEGGINGS.get())
               .pattern("RRR")
               .pattern("R R")
               .pattern("R R")
               .define('R', ModItems.RED_MAGIC.get())
               .unlockedBy("has_red_magic", has(ModItems.RED_MAGIC)).save(recipeOutput);
       ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.RED_MAGIC_BOOTS.get())
               .pattern("R R")
               .pattern("R R")
               .define('R', ModItems.RED_MAGIC.get())
               .unlockedBy("has_red_magic", has(ModItems.RED_MAGIC)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.OBAMIUM.get(), 9)
                .requires(ModBlocks.OBAMIUM_BLOCK)
                .unlockedBy("has_obamium_block", has(ModBlocks.OBAMIUM_BLOCK)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.OBAMIUM.get(), 18)
                .requires(ModBlocks.MAGIC_BLOCK)
                .unlockedBy("has_obamium_block", has(ModBlocks.MAGIC_BLOCK))
                .save(recipeOutput, "javafinal:obamium_from_magic_block");

        oreSmelting(recipeOutput, OBAMIUM_SMELTABLES, RecipeCategory.MISC, ModItems.OBAMIUM.get(), 0.25f, 200, "obamium");
        oreBlasting(recipeOutput, OBAMIUM_SMELTABLES, RecipeCategory.MISC, ModItems.OBAMIUM.get(), 0.25f, 100, "obamium");

        oreSmelting(recipeOutput, RED_MAGIC_SMELTABLES, RecipeCategory.MISC, ModItems.RED_MAGIC.get(), 0.25f, 200, "red_magic");
        oreBlasting(recipeOutput, RED_MAGIC_SMELTABLES, RecipeCategory.MISC, ModItems.RED_MAGIC.get(), 0.25f, 100, "red_magic");

        stairBuilder(ModBlocks.OBAMIUM_STAIRS.get(), Ingredient.of(ModItems.OBAMIUM)).group("obamium")
                .unlockedBy("has_obamium", has(ModItems.OBAMIUM)).save(recipeOutput);
        slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.OBAMIUM_SLAB.get(), ModItems.OBAMIUM.get());

        buttonBuilder(ModBlocks.OBAMIUM_BUTTON.get(), Ingredient.of(ModItems.OBAMIUM.get())).group("obamium")
                .unlockedBy("has_obamium", has(ModItems.OBAMIUM.get())).save(recipeOutput);
        pressurePlate(recipeOutput, ModBlocks.OBAMIUM_PRESSURE_PLATE.get(), ModItems.OBAMIUM.get());

        fenceBuilder(ModBlocks.OBAMIUM_FENCE.get(), Ingredient.of(ModItems.OBAMIUM.get())).group("obamium")
                .unlockedBy("has_obamium", has(ModItems.OBAMIUM.get())).save(recipeOutput);
        fenceGateBuilder(ModBlocks.OBAMIUM_FENCE_GATE.get(), Ingredient.of(ModItems.OBAMIUM.get())).group("obamium")
                .unlockedBy("has_obamium", has(ModItems.OBAMIUM.get())).save(recipeOutput);
        wall(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.OBAMIUM_WALL.get(), ModItems.OBAMIUM.get());

        doorBuilder(ModBlocks.OBAMIUM_DOOR.get(), Ingredient.of(ModItems.OBAMIUM.get())).group("obamium")
                .unlockedBy("has_obamium", has(ModItems.OBAMIUM.get())).save(recipeOutput);
        trapdoorBuilder(ModBlocks.OBAMIUM_TRAPDOOR.get(), Ingredient.of(ModItems.OBAMIUM.get())).group("obamium")
                .unlockedBy("has_obamium", has(ModItems.OBAMIUM.get())).save(recipeOutput);

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
