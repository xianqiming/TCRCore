package com.p1nero.tcrcore.mixin;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.MobSpawnEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import reascer.wom.events.WOMLivingEntityEvents;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;

import static reascer.wom.events.WOMLivingEntityEvents.*;

@Mixin(WOMLivingEntityEvents.class)
public class WOMLivingEntityEventsMixin {

    @Inject(method = "onSpawnEvent", at = @At("HEAD"), cancellable = true, remap = false)
    private static void tcr$onLivingSpawn(MobSpawnEvent event, CallbackInfo ci) {
        ci.cancel();
    }

    /**
     * 加非玩家的判断，防止bug
     */
    @Inject(method = "onUpdateEvent", at = @At("HEAD"), cancellable = true, remap = false)
    private static void tcr$onLivingTick(LivingEvent.LivingTickEvent event, CallbackInfo ci) {
        ci.cancel();
        Entity entity = event.getEntity();
        if(entity.isInvulnerable() && !entity.level().isClientSide) {
            if(entity instanceof Player player && !player.getAbilities().invulnerable) {
                entity.setInvulnerable(false);
            }
            LivingEntityPatch<?> entityPatch = EpicFightCapabilities.getEntityPatch(entity, LivingEntityPatch.class);
            if (entityPatch != null) {
                for(String tag : event.getEntity().getTags()) {
                    if (tag.contains("wom_ultimate_Invulnerable")) {
                        entity.setInvulnerable(false);
                        event.getEntity().getTags().remove(tag);
                        break;
                    }
                }
            }
        }
        AntiStunlock(event, entity);
        TimedSakuraSlashes(event, entity);
        LunarEclipse(event, entity);
        SolarIgnited(event);
        Blackout(event);
    }
}
