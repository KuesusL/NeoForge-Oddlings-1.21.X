package net.zeriko.oddlings.item;

import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.zeriko.oddlings.Oddlings;
import net.zeriko.oddlings.item.custom.*;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Oddlings.MOD_ID);

    public static final DeferredItem<Item> ODD_BAR = ITEMS.register("odd_bar",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> ODD_CHUNK = ITEMS.register("odd_chunk",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> ODD_NUGGET = ITEMS.register("odd_nugget",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> CHISEL = ITEMS.register("chisel",
            () -> new ChiselItem(new Item.Properties().durability(32)));
    public static final DeferredItem<Item> MAGNET_SHARD = ITEMS.register("magnet_shard",
            () -> new MagnetItem(new Item.Properties().durability(64)));

    public static final DeferredItem<Item> ODD_STEAK = ITEMS.register("odd_steak",
            () -> new Item(new Item.Properties().food(ModFoodProperties.ODD_STEAK)));

    public static final DeferredItem<Item> ODD_ESSENCE = ITEMS.register("odd_essence",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> ODD_ASHES = ITEMS.register("odd_ashes",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> REGEN_SHARD = ITEMS.register("regen_shard",
            () -> new RegenItem(new Item.Properties().durability(10)));

    public static final DeferredItem<Item> HEAL_SHARD = ITEMS.register("heal_shard",
            () -> new HealItem(new Item.Properties().durability(5)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
