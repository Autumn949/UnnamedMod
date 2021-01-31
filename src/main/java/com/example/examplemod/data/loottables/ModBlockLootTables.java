package com.example.examplemod.data.loottables;

import com.example.examplemod.common.UnnamedMod;
import com.example.examplemod.common.blocks.ModBlocks;
import net.minecraft.advancements.criterion.*;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.DoublePlantBlock;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Items;
import net.minecraft.loot.*;
import net.minecraft.loot.conditions.*;
import net.minecraft.loot.functions.ILootFunction;
import net.minecraft.loot.functions.SetCount;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


public class ModBlockLootTables extends BlockLootTables {
    private static final ILootCondition.IBuilder SILK_TOUCH = MatchTool.builder(
        ItemPredicate.Builder.create()
                             .enchantment(new EnchantmentPredicate(
                                 Enchantments.SILK_TOUCH, MinMaxBounds.IntBound.atLeast(1)
                             ))
    );
    private static final ILootCondition.IBuilder SHEARS = MatchTool.builder(
        ItemPredicate.Builder.create().item(Items.SHEARS)
    );
    private static final ILootCondition.IBuilder NOT_SILK_TOUCH = SILK_TOUCH.inverted();
    private static final ILootCondition.IBuilder SILK_TOUCH_OR_SHEARS = SHEARS.alternative(SILK_TOUCH);
    private static final ILootCondition.IBuilder NOT_SILK_TOUCH_OR_SHEARS = SILK_TOUCH_OR_SHEARS.inverted();
    private static final float[] DEFAULT_SAPLING_DROP_RATES = new float[]{0.05F, 0.0625F, 0.083333336F, 0.1F};


    @Override
    protected void addTables() {

        //
        //APPLE BLOCKS
        //
        registerDropSelfLootTable(ModBlocks.APPLE_TREE_LOG);
        registerLootTable(ModBlocks.APPLE_LEAVES, block-> droppingWithChancesAndSticks(block, Blocks.OAK_SAPLING,DEFAULT_SAPLING_DROP_RATES));
        registerDropSelfLootTable(ModBlocks.APPLE_PLANKS);
        registerLootTable(ModBlocks.APPLE_SLAB, block ->  droppingSlab(block));
        registerDropSelfLootTable(ModBlocks.APPLE_FENCE);
        registerDropSelfLootTable(ModBlocks.APPLE_FENCE_GATE);
        registerDropSelfLootTable(ModBlocks.APPLE_STAIRS);
        registerDropSelfLootTable(ModBlocks.APPLE_DOOR);
        registerDropSelfLootTable(ModBlocks.APPLE_TRAP_DOOR);


        //TODO:UPDATE TO DROP APPLE WHEN FULLY GROWN ROTTEN APPLE WHEN OLD AND GROWTH STAGE WHEN SILKED

        registerSilkTouch(ModBlocks.APPLE);


        //
        //FOREST BLOCK
        //
        registerDropSelfLootTable(ModBlocks.MUD_BLOCK);
    }




    protected static LootTable.Builder droppingNothing() {
        return LootTable.builder();
    }

    private static ILootFunction.IBuilder setCount(int count) {
        return SetCount.builder(ConstantRange.of(count));
    }

    private static ILootCondition.IBuilder randomChance(double chance) {
        return RandomChance.builder((float) chance);
    }

    private static StandaloneLootEntry.Builder<?> item(IItemProvider item) {
        return ItemLootEntry.builder(item);
    }

    private static StandaloneLootEntry.Builder<?> item(IItemProvider item, int count) {
        return ItemLootEntry.builder(item).acceptFunction(setCount(count));
    }

    private static StandaloneLootEntry.Builder<?> item(IItemProvider item, double chance) {
        return ItemLootEntry.builder(item).acceptCondition(randomChance(chance));
    }

    private static ILootCondition.IBuilder checkBlock(Block self, StatePropertiesPredicate.Builder state, BlockPos offset) {
        return LocationCheck.method_30151(
            LocationPredicate.Builder
                .create()
                .block(
                    BlockPredicate.Builder.create()
                                          .block(self)
                                          .state(state.build())
                                          .build()
                ),
            offset
        );
    }

    private static LootTable.Builder shearedOrSeeds(Block self, Block sheared) {
        LootEntry.Builder<?> entry
            = ItemLootEntry.builder(sheared)
                           .acceptFunction(setCount(2))
                           .acceptCondition(SHEARS)
                           .alternatively(withExplosionDecay(
                               self,
                               item(Items.WHEAT_SEEDS, 2)
                           ));

        StatePropertiesPredicate.Builder lowerHalfPredicate
            = StatePropertiesPredicate.Builder.create()
                                              .exactMatch(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER);

        StatePropertiesPredicate.Builder upperHalfPredicate
            = StatePropertiesPredicate.Builder.create()
                                              .exactMatch(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER);

        ILootCondition.IBuilder lowerHalf = BlockStateProperty.builder(self).properties(lowerHalfPredicate);
        ILootCondition.IBuilder upperHalf = BlockStateProperty.builder(self).properties(upperHalfPredicate);

        return LootTable
                   .builder()
                   .addLootPool(
                       LootPool.builder()
                               .addEntry(entry)
                               .acceptCondition(lowerHalf)
                               .acceptCondition(checkBlock(self, upperHalfPredicate, new BlockPos(0, 1, 0)))
                   )
                   .addLootPool(
                       LootPool.builder()
                               .addEntry(entry)
                               .acceptCondition(upperHalf)
                               .acceptCondition(checkBlock(self, lowerHalfPredicate, new BlockPos(0, -1, 0)))
                   );
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return StreamSupport.stream(ForgeRegistries.BLOCKS.spliterator(), false)
                            .filter(block -> Objects.requireNonNull(block.getRegistryName()).getNamespace().equals(UnnamedMod.ID))
                            .collect(Collectors.toList());
    }
}
