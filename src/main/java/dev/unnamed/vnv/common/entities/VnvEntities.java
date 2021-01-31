package dev.unnamed.vnv.common.entities;

import dev.unnamed.vnv.common.ValleysNVistas;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.monster.piglin.PiglinAction;
import net.minecraft.entity.monster.piglin.PiglinEntity;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.List;

public abstract class VnvEntities {
    //TODO:MAKE WORK
    private static final List<EntityType<?>> REGISTRY = new ArrayList<>();

    public static final EntityType<FrogEntity> FROG = mob("frog_mob",  EntityType.Builder.create(FrogEntity::new, EntityClassification.CREATURE)
            .size(.5f, .5f)
            .setShouldReceiveVelocityUpdates(false)
            .build("frog_mob"));;

    public static void register(IForgeRegistry<EntityType<?>> registry) {
        REGISTRY.forEach(registry::register);
    }

    private static <T extends EntityType> T mob(String id, T mob) {
        mob.setRegistryName(ValleysNVistas.id(id));
        REGISTRY.add(mob);
        return mob;
    }

    public static void setup() {

    }


}
