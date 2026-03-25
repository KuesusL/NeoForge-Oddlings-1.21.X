package net.zeriko.oddlings.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.MaceItem;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.zeriko.oddlings.Oddlings;
import net.zeriko.oddlings.item.custom.ChiselItem;
import net.zeriko.oddlings.item.custom.MagnetItem;

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

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
