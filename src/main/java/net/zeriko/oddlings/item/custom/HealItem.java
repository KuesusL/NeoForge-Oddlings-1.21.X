package net.zeriko.oddlings.item.custom;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.zeriko.oddlings.item.ModItems;

public class HealItem extends Item {

    public HealItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        ItemStack stack = player.getItemInHand(usedHand);

        player.heal(6.0f);

        player.getCooldowns().addCooldown(stack.getItem(), 60);

        // Cek apakah durability item sisa 1 kali pakai
        if(stack.getDamageValue() >= stack.getMaxDamage() - 1) {
            // Ganti item menjadi Odd Ashes
            player.setItemInHand(
                    usedHand,
                    new ItemStack(ModItems.ODD_ASHES.get())
            );
            // Play suara item break
            level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ITEM_BREAK, SoundSource.PLAYERS, 1.0f, 1.0f);

        } else {
            // Kurangi 1 durability
            stack.hurtAndBreak(1, player, EquipmentSlot.MAINHAND);
        }

        return InteractionResultHolder.success(player.getItemInHand(usedHand));
    }
}
