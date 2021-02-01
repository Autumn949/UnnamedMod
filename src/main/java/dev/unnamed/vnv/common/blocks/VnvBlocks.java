package dev.unnamed.vnv.common.blocks;

import com.google.common.collect.Lists;
import dev.unnamed.vnv.common.ValleysNVistas;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
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

    //APPLE BLOCKS
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


    //FOREST BLOCKS
    public static final Block MUSHROOM_FAN = mushroomFan("shelf_mushroom");
    public static final Block MUD_DRY = mud("mud_dry");
    public static final Block MUD_WET = mudwet("mud_wet");
    public static final Block LICHEN = moss("lichen");
    public static final Block MOSS = moss("moss");
    public static final Block ROTTEN_WOOD = log("rotten_wood",MaterialColor.ADOBE);

    //MUSHROOM
    public static final Block MUSHROOM_LOG_RED = log("mushroom_log_red",MaterialColor.RED);
    public static final Block MUSHROOM_LOG_BROWN = log("mushroom_log_brown",MaterialColor.BROWN);
    public static final Block MUSHROOM_CAP_RED = log("mushroom_cap_red",MaterialColor.RED);
    public static final Block MUSHROOM_CAP_BROWN = log("mushroom_cap_brown",MaterialColor.BROWN);

    //JUNGLE
    public static final Block PINEAPPLE_PLANT = pineapplePlant("pineapple_plant");
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
        RenderTypeLookup.setRenderLayer(MUSHROOM_FAN, RenderType.getCutout());
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

    private static MudBlock mud(String id) {
        return block(id, new MudBlock(
            AbstractBlock.Properties.create(Material.ORGANIC, MaterialColor.DIRT)
                                    .sound(SoundType.GROUND)
                                    .hardnessAndResistance(1)
                                    .harvestTool(ToolType.SHOVEL)
        ));
    }
    private static Block mudwet(String id) {
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
                                    .hardnessAndResistance(0).nonOpaque().noDrops()
                //WARNING HAS DROPS
                //TODO: FIX GEN TO CHECK FOR MANUAL DROPS
        ));
    }
    private static MossBlock moss(String id){
        return block(id, new MossBlock(
                AbstractBlock.Properties.create(Material.ORGANIC, MaterialColor.BROWN)
                        .sound(SoundType.PLANT)
                        .hardnessAndResistance(0).nonOpaque()
        ));
    }
    private static PineapplePlant pineapplePlant(String id){
        return block(id, new PineapplePlant(
                AbstractBlock.Properties.create(Material.ORGANIC, MaterialColor.BROWN)
                        .sound(SoundType.PLANT)
                        .hardnessAndResistance(0).nonOpaque()
        ));
    }
}
