package dev.unnamed.vnv.data.recipes;

import dev.unnamed.vnv.common.ValleysNVistas;
import dev.unnamed.vnv.common.blocks.VnvBlocks;
import net.minecraft.data.*;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;

import java.util.function.Consumer;

public class VnvRecipeProvider extends RecipeProvider {
    private Consumer<IFinishedRecipe> consumer;

    public VnvRecipeProvider(DataGenerator gen) {
        super(gen);
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
        this.consumer = consumer;

        shapeless("apple_tree/planks_from_log", VnvBlocks.APPLE_TREE_LOG, VnvBlocks.APPLE_TREE_PLANKS, 4);
        generic3x1("apple_tree/slab_from_planks", VnvBlocks.APPLE_TREE_PLANKS, VnvBlocks.APPLE_TREE_SLAB, 6);
        stairs("apple_tree/stairs_from_planks", VnvBlocks.APPLE_TREE_PLANKS, VnvBlocks.APPLE_TREE_STAIRS, 4);
        fence("apple_tree/fence_from_planks_sticks", VnvBlocks.APPLE_TREE_PLANKS, VnvBlocks.APPLE_TREE_FENCE, 3);
        fenceGate("apple_tree/fence_gate_from_planks_sticks", VnvBlocks.APPLE_TREE_PLANKS, VnvBlocks.APPLE_TREE_FENCE_GATE, 1);
        generic2x3("apple_tree/door_from_planks", VnvBlocks.APPLE_TREE_PLANKS, VnvBlocks.APPLE_TREE_DOOR, 3);
        generic2x2("apple_tree/trapdoor_from_planks", VnvBlocks.APPLE_TREE_PLANKS, VnvBlocks.APPLE_TREE_TRAPDOOR, 2);

        smelting("mud_block/bake",VnvBlocks.MUD_WET,VnvBlocks.MUD_DRY,0);
    }

    private void generic2x2(String id, IItemProvider from, IItemProvider to, int count) {
        ShapedRecipeBuilder.shapedRecipe(to, count)
                           .key('#', from)
                           .patternLine("##")
                           .patternLine("##")
                           .addCriterion("has_ingredient", hasItem(from))
                           .build(consumer, ValleysNVistas.id(id));
    }

    private void generic2x3(String id, IItemProvider from, IItemProvider to, int count) {
        ShapedRecipeBuilder.shapedRecipe(to, count)
                           .key('#', from)
                           .patternLine("##")
                           .patternLine("##")
                           .patternLine("##")
                           .addCriterion("has_ingredient", hasItem(from))
                           .build(consumer, ValleysNVistas.id(id));
    }

    private void shapeless(String id, IItemProvider from, IItemProvider to, int count) {
        ShapelessRecipeBuilder.shapelessRecipe(to, count)
                              .addIngredient(from)
                              .addCriterion("has_ingredient", hasItem(from))
                              .build(consumer, ValleysNVistas.id(id));
    }

    private void fence(String id, IItemProvider from, IItemProvider to, int count) {
        ShapedRecipeBuilder.shapedRecipe(to, count)
                           .key('#', from)
                           .key('/', Items.STICK)
                           .patternLine("#/#")
                           .patternLine("#/#")
                           .addCriterion("has_ingredient", hasItem(from))
                           .build(consumer, ValleysNVistas.id(id));
    }

    private void fenceGate(String id, IItemProvider from, IItemProvider to, int count) {
        ShapedRecipeBuilder.shapedRecipe(to, count)
                           .key('#', from)
                           .key('/', Items.STICK)
                           .patternLine("/#/")
                           .patternLine("/#/")
                           .addCriterion("has_ingredient", hasItem(from))
                           .build(consumer, ValleysNVistas.id(id));
    }

    private void generic1x2(String id, IItemProvider from, IItemProvider to, int count) {
        ShapedRecipeBuilder.shapedRecipe(to, count)
                           .key('#', from)
                           .patternLine("#")
                           .patternLine("#")
                           .addCriterion("has_ingredient", hasItem(from))
                           .build(consumer, ValleysNVistas.id(id));
    }

    private void generic1x3(String id, IItemProvider from, IItemProvider to, int count) {
        ShapedRecipeBuilder.shapedRecipe(to, count)
                           .key('#', from)
                           .patternLine("#")
                           .patternLine("#")
                           .patternLine("#")
                           .addCriterion("has_ingredient", hasItem(from))
                           .build(consumer, ValleysNVistas.id(id));
    }

    private void generic3x1(String id, IItemProvider from, IItemProvider to, int count) {
        ShapedRecipeBuilder.shapedRecipe(to, count)
                           .key('#', from)
                           .patternLine("###")
                           .addCriterion("has_ingredient", hasItem(from))
                           .build(consumer, ValleysNVistas.id(id));
    }

    private void generic3x2(String id, IItemProvider from, IItemProvider to, int count) {
        ShapedRecipeBuilder.shapedRecipe(to, count)
                           .key('#', from)
                           .patternLine("###")
                           .patternLine("###")
                           .addCriterion("has_ingredient", hasItem(from))
                           .build(consumer, ValleysNVistas.id(id));
    }

    private void stairs(String id, IItemProvider from, IItemProvider to, int count) {
        ShapedRecipeBuilder.shapedRecipe(to, count)
                           .key('#', from)
                           .patternLine("#  ")
                           .patternLine("## ")
                           .patternLine("###")
                           .addCriterion("has_ingredient", hasItem(from))
                           .build(consumer, ValleysNVistas.id(id));
    }

    private void step(String id, IItemProvider from, IItemProvider to, int count) {
        ShapedRecipeBuilder.shapedRecipe(to, count)
                           .key('#', from)
                           .patternLine("#  ")
                           .patternLine("## ")
                           .addCriterion("has_ingredient", hasItem(from))
                           .build(consumer, ValleysNVistas.id(id));
    }

    private void smelting(String id, IItemProvider from, IItemProvider to, double xp) {
        CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(from), to, (float) xp, 200)
                            .addCriterion("has_ingredient", hasItem(from))
                            .build(consumer, ValleysNVistas.id(id));
    }

    @Override
    public String getName() {
        return "ModRecipes";
    }
}
