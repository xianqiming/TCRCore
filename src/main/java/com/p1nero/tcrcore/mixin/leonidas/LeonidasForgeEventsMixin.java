package com.p1nero.tcrcore.mixin.leonidas;

import net.minecraftforge.event.entity.living.LivingDropsEvent;
import org.merlin204.leonidas.event.ForgeEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ForgeEvents.class)
public class LeonidasForgeEventsMixin {

    /**
     * 取消原来的掉落
     */
    @Inject(method = "onLivingDrops", at = @At("HEAD"), cancellable = true, remap = false)
    private static void tcr$onLivingDrops(LivingDropsEvent event, CallbackInfo ci) {
        ci.cancel();
    }


}
