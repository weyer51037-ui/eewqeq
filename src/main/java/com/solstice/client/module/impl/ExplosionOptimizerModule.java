package com.solstice.client.module.impl;
import com.solstice.client.module.*;
import com.solstice.client.module.Module;

/** Skips explosion particles (crystals, respawn anchors, TNT) so big crystal/anchor fights stay smooth. */
public class ExplosionOptimizerModule extends Module {
    public ExplosionOptimizerModule() { super("Explosion Optimizer", "Hides explosion particles to cut crystal/anchor lag.", Category.PVP); }
}
