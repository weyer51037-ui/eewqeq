package com.solstice.client.mixin;
import com.solstice.client.module.ModuleManager;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public class EntityMixin {
    /** While freelook is held, mouse movement turns the camera instead of the player. */
    @Inject(method = "turn", at = @At("HEAD"), cancellable = true)
    private void solstice$freelook(double yRot, double xRot, CallbackInfo ci) {
        var f = ModuleManager.freelook;
        if (f != null && f.active() && (Object) this instanceof LocalPlayer) {
            f.yaw += (float) yRot * 0.15f;
            f.pitch = Mth.clamp(f.pitch + (float) xRot * 0.15f, -90f, 90f);
            ci.cancel();
        }
    }
}
