package com.example.examplemod.common.items;

import net.minecraft.item.Food;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public abstract class ModFoods {
    public static final Food ROTTEN_APPLE = (new Food.Builder()).hunger(4).saturation(0.1F).effect(new EffectInstance(Effects.HUNGER, 600, 0), 0.8F).build();
}
