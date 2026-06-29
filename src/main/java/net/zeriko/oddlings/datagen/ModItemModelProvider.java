package net.zeriko.oddlings.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.zeriko.oddlings.Oddlings;
import net.zeriko.oddlings.item.ModItems;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Oddlings.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.CHISEL.get());
        basicItem(ModItems.MAGNET_SHARD.get());

        basicItem(ModItems.ODD_CHUNK.get());
        basicItem(ModItems.ODD_NUGGET.get());
        basicItem(ModItems.ODD_BAR.get());

        basicItem(ModItems.ODD_ASHES.get());

        basicItem(ModItems.ODD_STEAK.get());

    }
}
