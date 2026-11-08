package com.solstice.client.module.impl;
import com.solstice.client.module.*;
import com.solstice.client.module.Module;
import net.minecraft.client.GraphicsStatus;
import net.minecraft.client.ParticleStatus;

/** Lowers particle load and graphics mode for steadier FPS in fights; restores your settings when disabled. */
public class PvpPerformanceModule extends Module {
    private ParticleStatus oldParticles; private GraphicsStatus oldGraphics;
    public PvpPerformanceModule() { super("PvP Performance", "Minimal particles + fast graphics for stable FPS.", Category.PVP); }
    @Override protected void onEnable() {
        var o = mc().options;
        oldParticles = o.particles().get(); oldGraphics = o.graphicsMode().get();
        o.particles().set(ParticleStatus.MINIMAL); o.graphicsMode().set(GraphicsStatus.FAST);
    }
    @Override protected void onDisable() {
        var o = mc().options;
        if (oldParticles != null) o.particles().set(oldParticles);
        if (oldGraphics != null) o.graphicsMode().set(oldGraphics);
    }
}
