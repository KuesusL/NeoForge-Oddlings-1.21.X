package net.zeriko.oddlings.item;

import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.zeriko.oddlings.Oddlings;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Oddlings.MOD_ID);

    public static final DeferredItem<Item> ODD_BAR = ITEMS.register("odd_bar",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> ODD_CHUNK = ITEMS.register("odd_chunk",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
