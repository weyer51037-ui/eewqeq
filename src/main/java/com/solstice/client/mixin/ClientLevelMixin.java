package com.solstice.client.mixin;
import com.solstice.client.module.ModuleManager;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientLevel.class)
public class ClientLevelMixin {
    @Inject(method = "getSkyColor", at = @At("RETURN"), cancellable = true)
    private void solstice$sky(Vec3 pos, float partialTick, CallbackInfoReturnable<Vec3> cir) {
        var s = ModuleManager.sky;
        if (s != null && s.isEnabled()) cir.setReturnValue(s.color);
    }
}
