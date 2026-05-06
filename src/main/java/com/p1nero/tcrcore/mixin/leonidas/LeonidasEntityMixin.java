package com.p1nero.tcrcore.mixin.leonidas;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import org.merlin204.leonidas.entity.LeonidasEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import yesman.epicfight.world.entity.ai.attribute.EpicFightAttributes;

@Mixin(LeonidasEntity.class)
public class LeonidasEntityMixin {
    @Inject(method = "getDefaultAttribute", at = @At("HEAD"), cancellable = true, remap = false)
    private static void tcr$getDefaultAttribute(CallbackInfoReturnable<AttributeSupplier> cir) {
        cir.setReturnValue(Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 150.0F)
                .add(Attributes.ATTACK_DAMAGE, 6.0F)
                .add(Attributes.ARMOR, 20.0F)
                .add(Attributes.FOLLOW_RANGE, 72.0F)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1000.0F)
                .add(EpicFightAttributes.MAX_STRIKES.get(), 50.0F)
                .add(EpicFightAttributes.ARMOR_NEGATION.get(), 20.0F)
                .add(EpicFightAttributes.WEIGHT.get(), 2000.0F).build());
    }
}
