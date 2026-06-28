package net.zeriko.oddlings.datagen;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.server.ReloadableServerRegistries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.zeriko.oddlings.block.ModBlocks;
import net.zeriko.oddlings.item.ModItems;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags() , registries);
    }

    @Override
    protected void generate() {
        // Drop-self
        dropSelf(ModBlocks.ODD_BLOCK.get());
        dropSelf(ModBlocks.RESTSTONE.get());
        dropSelf(ModBlocks.ODD_STONE.get());

        // Ore Drop
        add(ModBlocks.ODD_ORE.get(),
                block -> createOreDrop(ModBlocks.ODD_ORE.get(), ModItems.ODD_CHUNK.get()));
        add(ModBlocks.DEEPSLATE_ODD_ORE.get(),
                block -> createMultipleOreDrop(ModBlocks.DEEPSLATE_ODD_ORE.get(), ModItems.ODD_CHUNK.get(), 2, 4));
    }

    protected LootTable.Builder createMultipleOreDrop(Block pBlock, Item item, float minDrops, float maxDrops) {
        HolderLookup.RegistryLookup<Enchantment> registryLookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock, LootItem.lootTableItem(item)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))
                        .apply(ApplyBonusCount.addOreBonusCount(registryLookup.getOrThrow(Enchantments.FORTUNE)))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
