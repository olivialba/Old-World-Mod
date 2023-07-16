package net.alba.oldworld.registry;

import net.alba.oldworld.OldWorld;
import net.alba.oldworld.entity.mobs.BlackSpiderEntity;
import net.alba.oldworld.entity.projectiles.BallBasicEntity;
import net.alba.oldworld.entity.projectiles.CrystalProjectileEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class OldEntities {

    // Entities MOB
    public static final EntityType<BlackSpiderEntity> BLACK_SPIDER = Registry.register(Registries.ENTITY_TYPE, new Identifier(OldWorld.MOD_ID, "black_spider"), FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, BlackSpiderEntity::new).dimensions(EntityDimensions.fixed(1.5F, 1.75F)).build());

    // Entities PROJECTILE
    public static final EntityType<CrystalProjectileEntity> CRYSTAL_PROJECTILE = Registry.register(Registries.ENTITY_TYPE, new Identifier(OldWorld.MOD_ID, "crystal_projectile"), FabricEntityTypeBuilder.<CrystalProjectileEntity>create(SpawnGroup.MISC, CrystalProjectileEntity::new).dimensions(EntityDimensions.fixed(0.5F, 0.5F)).trackRangeChunks(5).trackedUpdateRate(10).build());
    public static final EntityType<BallBasicEntity> BASIC_BALL_PROJECTILE = Registry.register(Registries.ENTITY_TYPE, new Identifier(OldWorld.MOD_ID, "ball_basic"), FabricEntityTypeBuilder.<BallBasicEntity>create(SpawnGroup.MISC, BallBasicEntity::new).dimensions(EntityDimensions.fixed(0.5F, 0.5F)).trackRangeChunks(5).trackedUpdateRate(10).build());

    public static void registerEntities() {
        FabricDefaultAttributeRegistry.register(OldEntities.BLACK_SPIDER, BlackSpiderEntity.setAttributes());
    }
}       
