package net.zeriko.oddlings.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.zeriko.oddlings.Oddlings;
import net.zeriko.oddlings.block.ModBlocks;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, Oddlings.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        // Needs Pickaxe
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.ODD_BLOCK.get())
                .add(ModBlocks.ODD_ORE.get())
                .add(ModBlocks.DEEPSLATE_ODD_ORE.get())
                .add(ModBlocks.RESTSTONE.get())
                .add(ModBlocks.ODD_STONE.get());

        // Needs iron tools
        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.DEEPSLATE_ODD_ORE.get())
                .add(ModBlocks.ODD_ORE.get());
    }
}
