package net.alba.oldworld.magic.effect;

import java.util.UUID;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributeModifier.Operation;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.math.Vec3d;

public class PetrifyStatus extends StatusEffect {
    private static final UUID PETRIFY_ID = UUID.fromString("87f36a96-662f-4796-b035-22e16be9e038");
    private static final EntityAttributeModifier PETRIFY = new EntityAttributeModifier(PETRIFY_ID, "Petrify Movement", -10, Operation.MULTIPLY_TOTAL);

    public PetrifyStatus() {
        super(StatusEffectCategory.HARMFUL, 0xC0C0C0);
    }

    @Override 
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        entity.setVelocity(Vec3d.ZERO.getX(), -3, Vec3d.ZERO.getZ());
    }

    public void onApplied(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        super.onApplied(entity, attributes, amplifier);
        entity.setJumping(false);
        entity.setSilent(true);
        EntityAttributeInstance entityAttributeInstance = entity.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED);
        if (entityAttributeInstance.getModifier(PETRIFY_ID) == null) {
            entityAttributeInstance.addTemporaryModifier(PETRIFY);
        }
     }

    public void onRemoved(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        super.onRemoved(entity, attributes, amplifier);
        entity.setJumping(true);
        entity.setSilent(false);
        EntityAttributeInstance entityAttributeInstance = entity.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED);
        if (entityAttributeInstance.getModifier(PETRIFY_ID) != null) {
            entityAttributeInstance.removeModifier(PETRIFY);
        }
    }
}




