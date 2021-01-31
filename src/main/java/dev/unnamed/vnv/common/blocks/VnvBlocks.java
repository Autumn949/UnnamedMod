package dev.unnamed.vnv.common.blocks;

import com.google.common.collect.Lists;
import dev.unnamed.vnv.common.ValleysNVistas;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

import java.util.List;

@ObjectHolder("unnamedmod")
public abstract class VnvBlocks {
    private static final List<Block> REGISTRY = Lists.newArrayList();

    //
    // BLOCKS
    //

    public static final Block APPLE_TREE_LOG = log("apple_tree_log", MaterialColor.WOOD);
    public static final Block APPLE_TREE_LEAVES = leaves("apple_tree_leaves");
    public static final Block FLOWERING_APPLE_TREE_LEAVES = floweringLeaves("flowering_apple_tree_leaves");

    public static final Block APPLE_TREE_PLANKS = planks("apple_tree_planks", MaterialColor.WOOD);
    public static final Block APPLE_TREE_SLAB = woodenSlab("apple_tree_slab", MaterialColor.WOOD);
    public static final Block APPLE_TREE_STAIRS = woodenStairs("apple_tree_stairs", MaterialColor.WOOD);

    public static final Block APPLE_TREE_FENCE = woodenFence("apple_tree_fence", MaterialColor.WOOD);
    public static final Block APPLE_TREE_FENCE_GATE = fenceGate("apple_tree_fence_gate", MaterialColor.WOOD);
    public static final Block APPLE_TREE_DOOR = door("apple_tree_door", MaterialColor.WOOD);
    public static final Block APPLE_TREE_TRAPDOOR = trapDoor("apple_tree_trapdoor", MaterialColor.WOOD);

    public static final Block APPLE = apple("apple");
    public static final Block MUSHROOM_FAN = mushroomFan("mushroom_fan");

    public static final Block MUD = mud("mud");


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
        Minecraft.getInstance().getBlockColors().register(
            (state, world, pos, tintIndex) -> 0x50DE50,
            APPLE_TREE_LEAVES,
            FLOWERING_APPLE_TREE_LEAVES
        );
    }


    //
    // FACTORY METHODS
    //

    private static <T extends Block> T block(String id, T block) {
        block.setRegistryName(ValleysNVistas.id(id));
        REGISTRY.add(block);
        return block;
    }

    private static Block log(String id, MaterialColor color) {
        return block(id, new RotatedPillarBlock(
            AbstractBlock.Properties.create(Material.WOOD, color)
                                    .sound(SoundType.WOOD)
                                    .hardnessAndResistance(2)
                                    .harvestTool(ToolType.AXE)
        ));
    }

    private static Block leaves(String id) {
        return block(id, new LeavesBlock(
            AbstractBlock.Properties.create(Material.PLANTS, MaterialColor.FOLIAGE)
                                    .sound(SoundType.PLANT)
                                    .hardnessAndResistance(0.2f)
                                    .harvestTool(ToolType.HOE)
                                    .nonOpaque()
        ));
    }

    private static Block floweringLeaves(String id) {
        return block(id, new FloweringLeavesBlock(
            AbstractBlock.Properties.create(Material.PLANTS, MaterialColor.FOLIAGE)
                                    .sound(SoundType.PLANT)
                                    .hardnessAndResistance(0.2f)
                                    .harvestTool(ToolType.HOE)
                                    .tickRandomly()
                                    .nonOpaque()
        ));
    }

    private static Block planks(String id, MaterialColor color) {
        return block(id, new Block(
            AbstractBlock.Properties.create(Material.WOOD, color)
                                    .sound(SoundType.WOOD)
                                    .hardnessAndResistance(2)
                                    .harvestTool(ToolType.AXE)
        ));
    }

    private static SlabBlock woodenSlab(String id, MaterialColor color) {
        return block(id, new SlabBlock(
            AbstractBlock.Properties.create(Material.WOOD, color)
                                    .sound(SoundType.WOOD)
                                    .hardnessAndResistance(2)
                                    .harvestTool(ToolType.AXE)
        ));
    }

    private static FenceBlock woodenFence(String id, MaterialColor color) {
        return block(id, new FenceBlock(
            AbstractBlock.Properties.create(Material.WOOD, color)
                                    .sound(SoundType.WOOD)
                                    .hardnessAndResistance(2)
                                    .harvestTool(ToolType.AXE)
        ));
    }

    private static FenceGateBlock fenceGate(String id, MaterialColor color) {
        return block(id, new FenceGateBlock(
            AbstractBlock.Properties.create(Material.WOOD, color)
                                    .sound(SoundType.WOOD)
                                    .hardnessAndResistance(2)
                                    .harvestTool(ToolType.AXE)
        ));
    }

    private static StairsBlock woodenStairs(String id, MaterialColor color) {
        return block(id, new SimpleStairsBlock(
            AbstractBlock.Properties.create(Material.WOOD, color)
                                    .sound(SoundType.WOOD)
                                    .hardnessAndResistance(2)
                                    .harvestTool(ToolType.AXE)
        ));

    }

    private static AppleBlock apple(String id) {
        return block(id, new AppleBlock(
            AbstractBlock.Properties.create(Material.PLANTS, MaterialColor.RED)
                                    .sound(SoundType.WET_GRASS)
                                    .hardnessAndResistance(0.2f)
                                    .harvestTool(ToolType.HOE)
                                    .tickRandomly()
                                    .nonOpaque()
        ));

    }

    private static DoorBlock door(String id, MaterialColor color) {
        return block(id, new DoorBlock(
            AbstractBlock.Properties.create(Material.WOOD, color)
                                    .sound(SoundType.WOOD)
                                    .hardnessAndResistance(2)
                                    .harvestTool(ToolType.AXE)
                                    .nonOpaque()
        ));
    }

    private static TrapDoorBlock trapDoor(String id, MaterialColor color) {
        return block(id, new TrapDoorBlock(
            AbstractBlock.Properties.create(Material.WOOD, color)
                                    .sound(SoundType.WOOD)
                                    .hardnessAndResistance(2)
                                    .harvestTool(ToolType.AXE)
                                    .nonOpaque()
        ));
    }

    private static Block mud(String id) {
        return block(id, new Block(
            AbstractBlock.Properties.create(Material.ORGANIC, MaterialColor.DIRT)
                                    .sound(SoundType.GROUND)
                                    .hardnessAndResistance(1)
                                    .harvestTool(ToolType.SHOVEL)
                                    .velocityMultiplier(0.6f)
        ));
    }

    private static MushroomFanBlock mushroomFan(String id) {
        return block(id, new MushroomFanBlock(
            AbstractBlock.Properties.create(Material.ORGANIC, MaterialColor.BROWN)
                                    .sound(SoundType.PLANT)
                                    .hardnessAndResistance(0)
        ));
    }
}
