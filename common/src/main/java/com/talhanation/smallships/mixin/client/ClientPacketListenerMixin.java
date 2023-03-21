package com.talhanation.smallships.mixin.client;

import com.talhanation.smallships.duck.BoatLeashAccess;
import com.talhanation.smallships.world.entity.ship.abilities.Leashable;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.protocol.game.ClientboundSetEntityLinkPacket;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.vehicle.Boat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(ClientPacketListener.class)
public class ClientPacketListenerMixin {
    //LEASH FEATURE

    @SuppressWarnings("DataFlowIssue")
    @Inject(method = "handleEntityLinkPacket", at = @At(value = "TAIL"), locals = LocalCapture.CAPTURE_FAILHARD)
    private void handleEntityLinkPacketForShips(ClientboundSetEntityLinkPacket clientboundSetEntityLinkPacket, CallbackInfo ci, Entity entity) {
        if (entity instanceof Leashable || entity.getClass().equals(Boat.class)) {
            ((BoatLeashAccess)entity).setDelayedLeashHolderId(clientboundSetEntityLinkPacket.getDestId());
        }
    }
}
