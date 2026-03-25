package net.zeriko.oddlings.item.custom;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.zeriko.oddlings.MagnetTick;

public class MagnetItem extends Item {

    public MagnetItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (player == null) return InteractionResultHolder.pass(stack);

        if(!level.isClientSide) {
            double radius = 5.0;
            for(ItemEntity item : level.getEntitiesOfClass(ItemEntity.class, player.getBoundingBox().inflate(radius))) {
                MagnetTick.addActiveItem(item);
            }
            stack.hurtAndBreak(1, player, EquipmentSlot.MAINHAND);

            level.playSound(player, player.getOnPos(), SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.PLAYERS);
        }
        return InteractionResultHolder.success(stack);
    }
}

