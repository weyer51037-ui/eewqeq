package com.solstice.client.mixin;
import com.solstice.client.module.ModuleManager;
import net.minecraft.client.Camera;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(Camera.class)
public class CameraMixin {
    @ModifyVariable(method = "setRotation", at = @At("HEAD"), argsOnly = true, ordinal = 0)
    private float solstice$yaw(float yaw) {
        var f = ModuleManager.freelook; return (f != null && f.active()) ? f.yaw : yaw;
    }
    @ModifyVariable(method = "setRotation", at = @At("HEAD"), argsOnly = true, ordinal = 1)
    private float solstice$pitch(float pitch) {
        var f = ModuleManager.freelook; return (f != null && f.active()) ? f.pitch : pitch;
    }
}
