package net.zeriko.oddlings.item.custom;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class RegenItem extends Item {

    public RegenItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        ItemStack stack = player.getItemInHand(usedHand);
        if (player == null) return InteractionResultHolder.pass(stack);
        if(!level.isClientSide) {
            player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 40, 2));
        }
        stack.hurtAndBreak(1, player, EquipmentSlot.MAINHAND);

        return InteractionResultHolder.success(stack);
    }
}
