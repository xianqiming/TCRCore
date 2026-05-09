package com.p1nero.tcrcore.mixin.bountiful;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import io.ejekta.bountiful.content.BountyCreator;
import io.ejekta.bountiful.data.Decree;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;

import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 限制不混合
 */
@Mixin(BountyCreator.Companion.class)
public class BountyCreatorCompanionMixin {

    @WrapMethod(method = "createBountyItem", remap = false)
    private ItemStack tcr$createBountyItem(ServerLevel world, BlockPos pos, Set<Decree> decrees, int rep, long startTime, Operation<ItemStack> original) {
        if (!decrees.isEmpty()) {
            Decree randomDecree = new ArrayList<>(decrees).get(ThreadLocalRandom.current().nextInt(decrees.size()));
            if (randomDecree != null) {
                Set<Decree> newDecrees = Set.of(randomDecree);
                return original.call(world, pos, newDecrees, rep, startTime);
            }
        }
        return original.call(world, pos, decrees, rep, startTime);
    }

}
