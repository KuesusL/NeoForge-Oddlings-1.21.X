package net.zeriko.oddlings.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.zeriko.oddlings.Oddlings;
import net.zeriko.oddlings.block.ModBlocks;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Oddlings.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.ODD_BLOCK);
        blockWithItem(ModBlocks.RESTSTONE);

        blockWithItem(ModBlocks.ODD_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_ODD_ORE);

        blockWithItemBottomTop(ModBlocks.ODD_STONE);
    }

    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }

    private void blockWithItemBottomTop(DeferredBlock<?> deferredBlock) {
        String name = deferredBlock.getId().getPath();

        simpleBlockWithItem(deferredBlock.get(),
                models().cubeBottomTop(
                        name,
                        blockTexture(deferredBlock.get()), // Side
                        modLoc("block/" + name + "_bottom"), // Bottom
                        modLoc("block/" + name + "_top") // Top
                ));
    }
}
