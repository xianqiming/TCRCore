package com.p1nero.tcrcore.mixin.multiplayer_boss_fight;

import com.p1nero.multiplayer_boss_fight.MultiplayerBossManager;
import com.p1nero.tcrcore.TCRCoreMod;
import net.minecraft.ChatFormatting;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MultiplayerBossManager.class)
public class BossManagerMixin {

    @Inject(method = "refreshBossAttributes", at = @At(value = "INVOKE", target = "Lcom/p1nero/multiplayer_boss_fight/MultiplayerBossManager;applyAttributeModifiers(Lnet/minecraft/world/entity/LivingEntity;Lcom/p1nero/multiplayer_boss_fight/MultiplayerBossManager$ResolvedBossConfig;I)V"), remap = false)
    private static void tcr$refreshBossAttributes(Entity target, CallbackInfo ci) {
        target.level().players().forEach(player -> {
            player.displayClientMessage(TCRCoreMod.getInfo("boss_health_modified_by_players").withStyle(ChatFormatting.RED), true);
        });
    }

}
