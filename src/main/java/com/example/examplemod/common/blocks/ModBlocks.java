package com.example.examplemod.common.blocks;

import com.example.examplemod.common.UnnamedMod;
import com.google.common.collect.Lists;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockDisplayReader;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;
import org.apache.commons.codec.net.BCodec;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Supplier;

@ObjectHolder("unnamedmod")
public abstract class ModBlocks {
    private static final List<Block> REGISTRY = Lists.newArrayList();

    //
    // BLOCKS
    //

    //
    //APPLE BLOCKS
    //
    public static final Block APPLE_TREE_LOG = appleTreeLog("apple_tree_log");
    public static final Block APPLE_LEAVES_FLOWERABLE = appleLeafflowerable("apple_leaves_flowerable");
    public static final Block APPLE_LEAVES = appleLeaf("apple_leaves");
    public static final Block APPLE_PLANKS = applePlanks("apple_planks");
    public static final SlabBlock APPLE_SLAB = appleslabs("apple_slab");
    public static final FenceGateBlock APPLE_FENCE_GATE = applegate("apple_fence_gate");
    public static final FenceBlock APPLE_FENCE = applefence("apple_fence");
    public static final StairsBlock APPLE_STAIRS = applestairs("apple_stairs");
    public static final FruitBlock APPLE = apple("apple");
    public static final DoorBlock APPLE_DOOR = appledoor("apple_door");
    public static final TrapDoorBlock APPLE_TRAP_DOOR = appletrapdoor("apple_trap_door");


    //
    // REGISTRY
    //

    public static void register(IForgeRegistry<Block> registry) {
        REGISTRY.forEach(registry::register);
    }

    public static void setup() {

    }

    @OnlyIn(Dist.CLIENT)
    public static void setupClient() {
        Minecraft.getInstance().getBlockColors().register(new IBlockColor() {
            @Override
            public int getColor(BlockState p_getColor_1_, @Nullable IBlockDisplayReader p_getColor_2_, @Nullable BlockPos p_getColor_3_, int p_getColor_4_) {
                if(p_getColor_1_.getBlock() instanceof FruitLeaves){
                    if(((FruitLeaves) p_getColor_1_.getBlock()).getFlowering(p_getColor_1_)==1){
                        return 0xFFFFFF;
                    }
                }
                return 0x50DE50;
            }
        },APPLE_LEAVES_FLOWERABLE);
        Minecraft.getInstance().getBlockColors().register(new IBlockColor() {
            @Override
            public int getColor(BlockState p_getColor_1_, @Nullable IBlockDisplayReader p_getColor_2_, @Nullable BlockPos p_getColor_3_, int p_getColor_4_) {
                return 0x50DE50;
            }
        },APPLE_LEAVES);

    }


    //
    // FACTORY METHODS
    //

    private static <T extends Block> T block(String id, T block) {
        block.setRegistryName(UnnamedMod.id(id));
        REGISTRY.add(block);
        return block;
    }

    private static Block appleTreeLog(String id) {
        return block(id, new RotatedPillarBlock(
            AbstractBlock.Properties.create(Material.WOOD, MaterialColor.WOOD)
                                    .sound(SoundType.WOOD)
                                    .hardnessAndResistance(2f)
                                    .harvestTool(ToolType.AXE)
        ));
    }
    //TODO: make seperate class and add flowered variations
    private static Block appleLeafflowerable(String id) {
        return block(id, new FruitLeaves(
                AbstractBlock.Properties.create(Material.PLANTS, MaterialColor.GRASS)
                        .sound(SoundType.PLANT)
                        .hardnessAndResistance(.2f)
                        .harvestTool(ToolType.HOE).nonOpaque().noDrops()
        ));
    }
    private static Block appleLeaf(String id) {
        return block(id, new LeavesBlock(
                AbstractBlock.Properties.create(Material.PLANTS, MaterialColor.GRASS)
                        .sound(SoundType.PLANT)
                        .hardnessAndResistance(.2f)
                        .harvestTool(ToolType.HOE).nonOpaque()
        ));
    }
    private static Block applePlanks(String id){
        return block(id, new Block(
                AbstractBlock.Properties.create(Material.WOOD,MaterialColor.BROWN).sound(SoundType.WOOD).hardnessAndResistance(2f).harvestTool(ToolType.AXE)
        ));
    }
    private static SlabBlock appleslabs(String id){
        return block(id, new SlabBlock(AbstractBlock.Properties.create(Material.WOOD,MaterialColor.BROWN).sound(SoundType.WOOD).hardnessAndResistance(2f).harvestTool(ToolType.AXE)));
    }
    private static FenceBlock applefence(String id){
        return block(id, new FenceBlock(AbstractBlock.Properties.create(Material.WOOD,MaterialColor.BROWN).sound(SoundType.WOOD).hardnessAndResistance(2f).harvestTool(ToolType.AXE)));
    }
    private static FenceGateBlock applegate(String id){
        return block(id, new FenceGateBlock(AbstractBlock.Properties.create(Material.WOOD,MaterialColor.BROWN).sound(SoundType.WOOD).hardnessAndResistance(2f).harvestTool(ToolType.AXE)));
    }
    private static StairsBlock applestairs(String id){
        return block(id, new StairsBlock(()-> ModBlocks.APPLE_PLANKS.getDefaultState(),AbstractBlock.Properties.create(Material.WOOD, MaterialColor.BROWN).sound(SoundType.WOOD).hardnessAndResistance(2f).harvestTool(ToolType.AXE)));

    }
    private static FruitBlock apple(String id){
        return block(id, new FruitBlock(AbstractBlock.Properties.create(Material.PLANTS,MaterialColor.GRASS).sound(SoundType.PLANT).hardnessAndResistance(0.5f).harvestTool(ToolType.AXE).nonOpaque()));

    }
    private static DoorBlock appledoor(String id){
        return block(id, new DoorBlock(AbstractBlock.Properties.create(Material.PLANTS,MaterialColor.GRASS).sound(SoundType.PLANT).hardnessAndResistance(0.5f).harvestTool(ToolType.AXE).nonOpaque()));
    }
    private static TrapDoorBlock appletrapdoor(String id){
        return block(id, new TrapDoorBlock(AbstractBlock.Properties.create(Material.PLANTS,MaterialColor.GRASS).sound(SoundType.PLANT).hardnessAndResistance(0.5f).harvestTool(ToolType.AXE).nonOpaque()));
    }
}
