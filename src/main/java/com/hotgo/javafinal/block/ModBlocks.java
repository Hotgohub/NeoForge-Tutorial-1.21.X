package com.hotgo.javafinal.block;

import com.hotgo.javafinal.JavaFinal;
import com.hotgo.javafinal.block.custom.*;
import com.hotgo.javafinal.item.ModItems;
import com.hotgo.javafinal.sound.ModSounds;
import com.hotgo.javafinal.worldgen.tree.ModTreeGrowers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.awt.*;
import java.util.List;
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(JavaFinal.MOD_ID);

    public static final DeferredBlock<Block> OBAMIUM_BLOCK = registerBlock("obamium_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.SCULK)){
                @Override
                public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("tooltip.javafinal.obamium_block.tooltip"));
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });

    public static final DeferredBlock<Block> OBAMIUM_ORE = registerBlock("obamium_ore",
            () -> new DropExperienceBlock(UniformInt.of(2, 4),
                    BlockBehaviour.Properties.of()
                            .strength(4f)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.WET_SPONGE)));

    public static final DeferredBlock<Block> OBAMIUM_DEEPSLATE_ORE = registerBlock("obamium_deepslate_ore",
            () -> new DropExperienceBlock(UniformInt.of(3, 6),
                    BlockBehaviour.Properties.of()
                            .strength(4f)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.WET_SPONGE)));

    //public static final DeferredBlock<Block> OBAMIUM_END_ORE= registerBlock("obamium_end_ore",
    //        () -> new DropExperienceBlock(UniformInt.of(5, 9),
    //                BlockBehaviour.Properties.of()
    //                        .strength(4f)
    //                        .requiresCorrectToolForDrops()
    //                        .sound(SoundType.WET_SPONGE)));

    //public static final DeferredBlock<Block> OBAMIUM_NETHER_ORE = registerBlock("obamium_nether_ore",
    //        () -> new DropExperienceBlock(UniformInt.of(1, 5),
    //                BlockBehaviour.Properties.of()
    //                        .strength(4f)
    //                        .requiresCorrectToolForDrops()
    //                        .sound(SoundType.WET_SPONGE)));


    public static final DeferredBlock<Block> RED_MAGIC_ORE = registerBlock("red_magic_ore",
            () -> new DropExperienceBlock(UniformInt.of(2, 4),
                    BlockBehaviour.Properties.of()
                            .strength(4f)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.AMETHYST)));

    public static final DeferredBlock<Block> RED_MAGIC_DEEPSLATE_ORE = registerBlock("red_magic_deepslate_ore",
            () -> new DropExperienceBlock(UniformInt.of(3, 6),
                    BlockBehaviour.Properties.of()
                            .strength(4f)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.AMETHYST)));

    public static final DeferredBlock<Block> RED_MAGIC_END_ORE = registerBlock("red_magic_end_ore",
            () -> new DropExperienceBlock(UniformInt.of(5, 9),
                    BlockBehaviour.Properties.of()
                            .strength(4f)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.AMETHYST)));

    public static final DeferredBlock<Block> RED_MAGIC_NETHER_ORE = registerBlock("red_magic_nether_ore",
            () -> new DropExperienceBlock(UniformInt.of(1, 5),
                    BlockBehaviour.Properties.of()
                            .strength(4f)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.AMETHYST)));


    public static final DeferredBlock<Block> MAGIC_BLOCK = registerBlock("magic_block",
            () -> new MagicBlock(BlockBehaviour.Properties.of()
                    .strength(2f)
                    .noLootTable()
                    .sound(ModSounds.MAGIC_BLOCK_SOUNDS)));

    public static final DeferredBlock<StairBlock> OBAMIUM_STAIRS = registerBlock("obamium_stairs",
            () -> new StairBlock(ModBlocks.OBAMIUM_BLOCK.get().defaultBlockState(),
                    BlockBehaviour.Properties.of()
                            .strength(2f)
                            .requiresCorrectToolForDrops()));
    public static final DeferredBlock<SlabBlock> OBAMIUM_SLAB = registerBlock("obamium_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.of()
                            .strength(2f)
                            .requiresCorrectToolForDrops()));

    public static final DeferredBlock<PressurePlateBlock> OBAMIUM_PRESSURE_PLATE = registerBlock("obamium_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.IRON,
                    BlockBehaviour.Properties.of()
                            .strength(2f)
                            .requiresCorrectToolForDrops()));
    public static final DeferredBlock<ButtonBlock> OBAMIUM_BUTTON = registerBlock("obamium_button",
            () -> new ButtonBlock(BlockSetType.IRON, 20,
                    BlockBehaviour.Properties.of()
                            .strength(2f)
                            .requiresCorrectToolForDrops()
                            .noCollission()));

    public static final DeferredBlock<FenceBlock> OBAMIUM_FENCE = registerBlock("obamium_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.of()
                            .strength(2f)
                            .requiresCorrectToolForDrops()));
    public static final DeferredBlock<FenceGateBlock> OBAMIUM_FENCE_GATE = registerBlock("obamium_fence_gate",
            () -> new FenceGateBlock(WoodType.OAK, BlockBehaviour.Properties.of()
                            .strength(2f)
                            .requiresCorrectToolForDrops()));
    public static final DeferredBlock<WallBlock> OBAMIUM_WALL = registerBlock("obamium_wall",
            () -> new WallBlock(BlockBehaviour.Properties.of()
                            .strength(2f)
                            .requiresCorrectToolForDrops()));

    public static final DeferredBlock<DoorBlock> OBAMIUM_DOOR = registerBlock("obamium_door",
            () -> new DoorBlock(BlockSetType.IRON, BlockBehaviour.Properties.of()
                            .strength(2f)
                            .requiresCorrectToolForDrops()
                            .noOcclusion()));
    public static final DeferredBlock<TrapDoorBlock> OBAMIUM_TRAPDOOR = registerBlock("obamium_trapdoor",
            () -> new TrapDoorBlock(BlockSetType.IRON, BlockBehaviour.Properties.of()
                            .strength(2f)
                            .requiresCorrectToolForDrops()
                            .noOcclusion()));
    public static final DeferredBlock<Block> RED_LAMP = registerBlock("red_lamp",
            () -> new RedLampBlock(BlockBehaviour.Properties.of()
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .lightLevel(state -> state.getValue(RedLampBlock.CLICKED) ? 15 : 0)));

    public static final DeferredBlock<Block> TOMATO_CROP = BLOCKS.register("tomato_crop",
            () -> new TomatoCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.PITCHER_CROP)));

    public static final DeferredBlock<Block> GOJI_BERRY_BUSH = BLOCKS.register("goji_berry_bush",
            () -> new GojiBerryBushBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SWEET_BERRY_BUSH)));

    public static final DeferredBlock<Block> BLOODWOOD_LOG = registerBlock("bloodwood_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)));
    public static final DeferredBlock<Block> BLOODWOOD_WOOD = registerBlock("bloodwood_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD)));
    public static final DeferredBlock<Block> STRIPPED_BLOODWOOD_LOG = registerBlock("stripped_bloodwood_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG)));
    public static final DeferredBlock<Block> STRIPPED_BLOODWOOD_WOOD = registerBlock("stripped_bloodwood_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD)));

    public static final DeferredBlock<Block> BLOODWOOD_PLANKS = registerBlock("bloodwood_planks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }
            });
    public static final DeferredBlock<Block> BLOODWOOD_LEAVES = registerBlock("bloodwood_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)){
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 60;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 30;
                }
            });

    public static final DeferredBlock<Block> BLOODWOOD_SAPLING = registerBlock("bloodwood_sapling",
            () -> new SaplingBlock(ModTreeGrowers.BLOODWOOD, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));

    public static final DeferredBlock<Block> CHAIR = registerBlock("chair",
            () -> new ChairBlock(BlockBehaviour.Properties.of().noOcclusion()));

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

}
