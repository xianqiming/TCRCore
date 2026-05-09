package com.p1nero.tcrcore.mixin.bountiful;

import io.ejekta.bountiful.content.BountyCreator;
import io.ejekta.bountiful.data.PoolEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Mixin(BountyCreator.class)
public class BountyCreatorMixin {

    /**
     * 不满足就随机选一个，适用于Boss
     */
    @Inject(method = "pickObjective", at = @At("HEAD"), cancellable = true, remap = false)
    private void tcr$pickObjective(List<PoolEntry> objs, double worth, CallbackInfoReturnable<PoolEntry> cir) {
        double variance = 0.25;
        List<PoolEntry> inVariance = tcr$getObjectivesWithinVariance(objs, worth, variance);

        if (inVariance.isEmpty()) {
            cir.setReturnValue(objs.get(ThreadLocalRandom.current().nextInt(objs.size())));
        }
    }

    @Unique
    private List<PoolEntry> tcr$getObjectivesWithinVariance(List<PoolEntry> objs, double worth, double variance) {
        double wRange = Math.ceil(worth * variance);
        return objs.stream()
                .filter(entry -> entry.worthDistanceFrom(worth) <= wRange)
                .collect(Collectors.toList());
    }


}
