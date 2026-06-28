package net.zeriko.oddlings.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.zeriko.oddlings.item.ModItems;
import net.zeriko.oddlings.util.ModTags;

public class ExchangerBlock extends Block {

    public ExchangerBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        level.playSound(player, pos, SoundEvents.AMETHYST_BLOCK_PLACE, SoundSource.BLOCKS, 1f, 1f);

        return InteractionResult.SUCCESS;
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        if(entity instanceof ItemEntity item) {
            if(item.getItem().getItem() == ModItems.ODD_BAR.get()) {
                item.setItem(new ItemStack(Items.GOLD_INGOT, item.getItem().getCount()));
            } else if (item.getItem().getItem() == Items.CLAY_BALL) {
                item.setItem(new ItemStack(Items.BRICK, item.getItem().getCount()));
            } else if (item.getItem().getItem() == Items.BONE) {
                item.setItem(new ItemStack(Items.BONE_MEAL, item.getItem().getCount()));
            } else if (isOddableItem(item.getItem())) {
                item.setItem(new ItemStack(ModItems.ODD_NUGGET.get(), item.getItem().getCount()));
            }
        }

        super.stepOn(level, pos, state, entity);
    }

    private boolean isOddableItem(ItemStack item) {
        return item.is(ModTags.Items.ODD_TRANSFORMABLE);
    }
}
