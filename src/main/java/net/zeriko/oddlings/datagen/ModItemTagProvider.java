package net.zeriko.oddlings.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.zeriko.oddlings.Oddlings;
import net.zeriko.oddlings.item.ModItems;
import net.zeriko.oddlings.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                              CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, Oddlings.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ModTags.Items.ODD_TRANSFORMABLE)
                .add(Items.GOLD_INGOT)
                .add(Items.IRON_INGOT);
        tag(ModTags.Items.ODD_ASHES_TRANSFORMABLE)
                .add(Items.CHARCOAL)
                .add(Items.COAL)
                .add(ModItems.ODD_STEAK.get());

    }
}
