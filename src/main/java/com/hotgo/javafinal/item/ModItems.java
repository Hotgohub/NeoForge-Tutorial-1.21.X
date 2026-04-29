package com.hotgo.javafinal.item;

import com.hotgo.javafinal.JavaFinal;
import com.hotgo.javafinal.item.custom.ChiselItem;
import com.hotgo.javafinal.item.custom.FuelItem;
import com.hotgo.javafinal.item.custom.HammerItem;
import com.hotgo.javafinal.item.custom.ModArmorItem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;

//Basically all items are going to be a 'public static final'//
public class ModItems {
    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(JavaFinal.MOD_ID);
    //Blocks
    public static final DeferredItem<Item> OBAMIUM = ITEMS.register("obamium",
            () -> new Item(new Item.Properties()){
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("tooltip.javafinal.obamium.tooltip"));
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });
    public static final DeferredItem<Item> RAW_OBAMIUM = ITEMS.register("raw_obamium",
            () -> new Item(new Item.Properties()){
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("tooltip.javafinal.raw_obamium.tooltip"));
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });

    public static final DeferredItem<Item> RED_MAGIC = ITEMS.register("red_magic",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> RAW_RED_MAGIC = ITEMS.register("raw_red_magic",
            () -> new Item(new Item.Properties()));

    //Custom Items
    public static final DeferredItem<Item> CHISEL = ITEMS.register("chisel",
            () -> new ChiselItem(new Item.Properties()
                    .durability(32)
            ));

    //Consumable Items
    public static final DeferredItem<Item> ORANGE_JUICE = ITEMS.register("orange_juice",
            () -> new Item(new Item.Properties().food(ModFoodProperties.ORANGE_JUICE)) {
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("tooltip.javafinal.orange_juice.tooltip"));
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });

    //Fuels
    public static final DeferredItem<Item> FIRE_PNG = ITEMS.register("fire_png",
            () -> new FuelItem(new Item.Properties(), 800){
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("tooltip.javafinal.fire_png.tooltip"));
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });
    public static final DeferredItem<Item> THE_WHITE_HOUSE = ITEMS.register("the_white_house",
            () -> new Item(new Item.Properties()){
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("tooltip.javafinal.the_white_house.tooltip"));
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });

    public static final DeferredItem<SwordItem> OBAMIUM_SWORD = ITEMS.register("obamium_sword",
            () -> new SwordItem(ModToolTiers.OBAMIUM, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.OBAMIUM, 5, -2.4f))));

    public static final DeferredItem<PickaxeItem> OBAMIUM_PICKAXE = ITEMS.register("obamium_pickaxe",
            () -> new PickaxeItem(ModToolTiers.OBAMIUM, new Item.Properties()
                    .attributes(PickaxeItem.createAttributes(ModToolTiers.OBAMIUM, 1.0f, -2.8f))));

    public static final DeferredItem<ShovelItem> OBAMIUM_SHOVEL = ITEMS.register("obamium_shovel",
            () -> new ShovelItem(ModToolTiers.OBAMIUM, new Item.Properties()
                    .attributes(ShovelItem.createAttributes(ModToolTiers.OBAMIUM, 1.5f, -3.0f))));

    public static final DeferredItem<AxeItem> OBAMIUM_AXE = ITEMS.register("obamium_axe",
            () -> new AxeItem(ModToolTiers.OBAMIUM, new Item.Properties()
                    .attributes(AxeItem.createAttributes(ModToolTiers.OBAMIUM, 6.0f, -3.2f))));

    public static final DeferredItem<HoeItem> OBAMIUM_HOE = ITEMS.register("obamium_hoe",
            () -> new HoeItem(ModToolTiers.OBAMIUM, new Item.Properties()
                    .attributes(HoeItem.createAttributes(ModToolTiers.OBAMIUM, 0f, -3.0f))));

    public static final DeferredItem<HammerItem> OBAMIUM_HAMMER = ITEMS.register("obamium_hammer",
            () -> new HammerItem(ModToolTiers.OBAMIUM, new Item.Properties()
                    .attributes(HammerItem.createAttributes(ModToolTiers.OBAMIUM, 7f, -3.5f))));


    public static final DeferredItem<ArmorItem> OBAMIUM_HELMET = ITEMS.register("obamium_helmet",
            () -> new ArmorItem(ModArmorMaterials.OBAMIUM_ARMOR_MATERIAL, ArmorItem.Type.HELMET,
                    new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(19))));

    public static final DeferredItem<ArmorItem> OBAMIUM_CHESTPLATE = ITEMS.register("obamium_chestplate",
            () -> new ArmorItem(ModArmorMaterials.OBAMIUM_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(19))));

    public static final DeferredItem<ArmorItem> OBAMIUM_LEGGINGS = ITEMS.register("obamium_leggings",
            () -> new ArmorItem(ModArmorMaterials.OBAMIUM_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(19))));

    public static final DeferredItem<ArmorItem> OBAMIUM_BOOTS = ITEMS.register("obamium_boots",
            () -> new ArmorItem(ModArmorMaterials.OBAMIUM_ARMOR_MATERIAL, ArmorItem.Type.BOOTS,
                    new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(19))));


    public static final DeferredItem<ArmorItem> RED_MAGIC_HELMET = ITEMS.register("red_magic_helmet",
            () -> new ModArmorItem(ModArmorMaterials.RED_MAGIC_ARMOR_MATERIAL, ArmorItem.Type.HELMET,
                    new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(19))));

    public static final DeferredItem<ArmorItem> RED_MAGIC_CHESTPLATE = ITEMS.register("red_magic_chestplate",
            () -> new ArmorItem(ModArmorMaterials.RED_MAGIC_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(19))));

    public static final DeferredItem<ArmorItem> RED_MAGIC_LEGGINGS = ITEMS.register("red_magic_leggings",
            () -> new ArmorItem(ModArmorMaterials.RED_MAGIC_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(19))));

    public static final DeferredItem<ArmorItem> RED_MAGIC_BOOTS = ITEMS.register("red_magic_boots",
            () -> new ArmorItem(ModArmorMaterials.RED_MAGIC_ARMOR_MATERIAL, ArmorItem.Type.BOOTS,
                    new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(19))));

    public static final DeferredItem<Item> RED_MAGIC_HORSE_ARMOR = ITEMS.register("red_magic_horse_armor",
            () -> new AnimalArmorItem(ModArmorMaterials.RED_MAGIC_ARMOR_MATERIAL, AnimalArmorItem.BodyType.EQUESTRIAN,
                    false, new Item.Properties().stacksTo(1)));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
