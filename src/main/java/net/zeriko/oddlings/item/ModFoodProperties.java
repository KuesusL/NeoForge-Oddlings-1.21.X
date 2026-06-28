package net.zeriko.oddlings.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoodProperties {
    public static final FoodProperties ODD_STEAK = new FoodProperties.Builder().nutrition(5).saturationModifier(1f)
            .alwaysEdible().effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 600), 1f).build();
}
