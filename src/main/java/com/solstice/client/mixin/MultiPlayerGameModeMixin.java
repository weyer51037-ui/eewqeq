package com.solstice.client.mixin;
import com.solstice.client.module.ModuleManager;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.boss.enderdragon.EndCrystal;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MultiPlayerGameMode.class)
public class MultiPlayerGameModeMixin {
    /** After the attack packet is sent, predict the crystal's removal locally. */
    @Inject(method = "attack", at = @At("TAIL"))
    private void solstice$crystal(Player player, Entity target, CallbackInfo ci) {
        var c = ModuleManager.crystal;
        if (c != null && c.isEnabled() && target instanceof EndCrystal && !player.isSpectator() && !target.isRemoved()) {
            target.discard();
        }
    }
}
