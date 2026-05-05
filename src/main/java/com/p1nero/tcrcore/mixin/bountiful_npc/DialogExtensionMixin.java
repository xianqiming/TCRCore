package com.p1nero.tcrcore.mixin.bountiful_npc;

import com.p1nero.bountiful_npc.dialog.ReceptionistDialogExtension;
import com.p1nero.dialog_lib.api.entity.IEntityDialogueExtension;
import com.p1nero.tcrcore.capability.TCRQuests;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ReceptionistDialogExtension.class)
public abstract class DialogExtensionMixin implements IEntityDialogueExtension<Villager> {

    @Inject(method = "onPlayerInteract(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/entity/npc/Villager;Lnet/minecraft/world/InteractionHand;)V", at = @At("HEAD"), remap = false)
    private void tcr$onPlayerInteract(Player player, Villager currentTalking, InteractionHand hand, CallbackInfo ci) {
        if(player instanceof ServerPlayer serverPlayer) {
            TCRQuests.TALK_TO_BOUNTIFUL_VILLAGER.finish(serverPlayer);
        }
    }

}
