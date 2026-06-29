package net.zeriko.oddlings.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.zeriko.oddlings.item.ModItems;
import net.zeriko.oddlings.util.ModTags;

import java.util.Map;

public class ExchangerBlock extends Block {

    public ExchangerBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        level.playSound(player, pos, SoundEvents.AMETHYST_BLOCK_PLACE, SoundSource.BLOCKS, 1f, 1f);

        return InteractionResult.SUCCESS;
    }

    private static Map<Item, Item> ITEM_TRANSFORMATIONS() {
        return Map.of(
                ModItems.ODD_BAR.get(), ModItems.ODD_CHUNK.get(),
                Items.CLAY_BALL, Items.BRICK,
                Items.BONE, Items.BONE_MEAL
        );
    }

    private static Map<TagKey<Item>, Item> TAG_TRANSFORMATIONS() {
        return Map.of(
                ModTags.Items.ODD_TRANSFORMABLE, ModItems.ODD_NUGGET.get(),
                ModTags.Items.ODD_ASHES_TRANSFORMABLE, ModItems.ODD_ASHES.get()
        );
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        if(entity instanceof ItemEntity item) {
            ItemStack stack = item.getItem();

            // Normal Item Transformation
            Item result = ITEM_TRANSFORMATIONS().get(stack.getItem());
            if(result != null) {
                item.setItem(new ItemStack(result, stack.getCount()));
                return;
            }

            // Tag checking
            for(var entry : TAG_TRANSFORMATIONS().entrySet()){
                if(stack.is(entry.getKey())) {
                    // Replacing the old items included in the tag to their value
                    item.setItem(new ItemStack(entry.getValue(), stack.getCount()));
                    return;
                }
            }
        }


        super.stepOn(level, pos, state, entity);
    }

}
