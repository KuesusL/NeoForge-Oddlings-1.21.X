package net.zeriko.oddlings.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.ShapelessRecipe;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import net.zeriko.oddlings.block.ModBlocks;
import net.zeriko.oddlings.item.ModItems;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        List<ItemLike> ODD_SMELTABLES = List.of(
                ModItems.ODD_CHUNK,
                ModBlocks.ODD_ORE,
                ModBlocks.DEEPSLATE_ODD_ORE
        );

        // Shaped
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ODD_BLOCK.get())
                .pattern("OOO")
                .pattern("OOO")
                .pattern("OOO")
                .define('O', ModItems.ODD_BAR)
                .unlockedBy("has_odd_bar", has(ModItems.ODD_BAR)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.CHISEL.get())
                .pattern("  O")
                .pattern(" S ")
                .pattern("G  ")
                .define('O', ModItems.ODD_BAR)
                .define('S', Items.STICK)
                .define('G', Items.GOLD_INGOT)
                .unlockedBy("has_odd_bar", has(ModItems.ODD_BAR)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.MAGNET_SHARD.get())
                .pattern("RR ")
                .pattern("RAL")
                .pattern("OLL")
                .define('R', Items.REDSTONE)
                .define('A', Items.AMETHYST_SHARD)
                .define('O', ModItems.ODD_BAR)
                .define('L',Items.LAPIS_LAZULI)
                .unlockedBy("has_odd_bar", has(ModItems.ODD_BAR)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ODD_BAR.get())
                .pattern("OOO")
                .pattern("OOO")
                .pattern("OOO")
                .define('O', ModItems.ODD_NUGGET)
                .unlockedBy("has_odd_nugget", has(ModItems.ODD_NUGGET)).save(recipeOutput, "odd_bar_from_odd_nugget");

        // Shapeless
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.ODD_BAR.get(), 9)
                .requires(ModBlocks.ODD_BLOCK)
                .unlockedBy("has_odd_block",has(ModBlocks.ODD_BLOCK)).save(recipeOutput, "odd_bar_from_odd_block");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.ODD_NUGGET.get(), 9)
                .requires(ModItems.ODD_BAR)
                .unlockedBy("has_odd_bar", has(ModItems.ODD_BAR)).save(recipeOutput);

        // Smelting
        oreSmelting(recipeOutput, ODD_SMELTABLES, RecipeCategory.MISC, ModItems.ODD_BAR.get(), 0.25f, 200, "odd_bar");

        // Blasting
        oreBlasting(recipeOutput, ODD_SMELTABLES, RecipeCategory.MISC, ModItems.ODD_BAR.get(), 0.25f, 100, "odd_bar");
    }
}
