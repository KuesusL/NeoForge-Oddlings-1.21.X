package net.zeriko.oddlings.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.zeriko.oddlings.Oddlings;
import net.zeriko.oddlings.block.ModBlocks;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Oddlings.MOD_ID);

    public static final Supplier<CreativeModeTab> ODDLINGS_ITEMS_TAB = CREATIVE_MODE_TAB.register("oddlings_items_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.ODD_BAR.get()))
                    .title(Component.translatable("creativetab.oddlings.oddlings_items"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.ODD_BAR);
                        output.accept(ModItems.ODD_CHUNK);
                    }).build());

    public static final Supplier<CreativeModeTab> CREATIVE_BLOCKS_TAB = CREATIVE_MODE_TAB.register("oddlings_blocks_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModBlocks.ODD_BLOCK.get()))
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(Oddlings.MOD_ID, "oddlings_items_tab"))
                    .title(Component.translatable("creativetab.oddlings.oddlings_blocks"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModBlocks.ODD_BLOCK);
                        output.accept(ModBlocks.ODD_ORE);
                        output.accept(ModBlocks.DEEPSLATE_ODD_ORE);
                    }).build());

    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
