package com.p1nero.tcrcore.mixin.multiplayer_boss_fight;

import com.p1nero.multiplayer_boss_fight.MultiplayerBossManager;
import com.p1nero.tcrcore.TCRCoreMod;
import net.minecraft.ChatFormatting;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MultiplayerBossManager.class)
public class BossManagerMixin {

    @Inject(method = "applyAttributeModifiers", at = @At("HEAD"), remap = false)
    private static void tcr$refreshBossAttributes(LivingEntity livingEntity, MultiplayerBossManager.ResolvedBossConfig config, int nearbyPlayers, CallbackInfo ci) {
        if(nearbyPlayers > 1) {
            livingEntity.level().players().forEach(player -> {
                player.displayClientMessage(TCRCoreMod.getInfo("boss_health_modified_by_players").withStyle(ChatFormatting.RED), true);
            });
        }
    }

}
