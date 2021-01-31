package dev.unnamed.vnv.common.entities;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.entity.living.AnimalTameEvent;

import javax.annotation.Nullable;

public class FrogEntity extends AnimalEntity {
    protected FrogEntity(EntityType<? extends AnimalEntity> p_i48568_1_, World p_i48568_2_) {
        super(p_i48568_1_, p_i48568_2_);
    }

    @Nullable
    @Override
    public AgeableEntity createChild(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) {
        return null;
    }



}
