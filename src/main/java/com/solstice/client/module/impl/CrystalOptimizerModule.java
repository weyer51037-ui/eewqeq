package com.solstice.client.module.impl;
import com.solstice.client.module.*;
import com.solstice.client.module.Module;

/**
 * Client-side prediction: when you hit an end crystal, it disappears on YOUR screen immediately
 * instead of waiting for the server to confirm. Makes crystal fights feel faster and hides lag.
 * Trade-off: if the server rejects the hit, the crystal may look gone until the server re-syncs.
 */
public class CrystalOptimizerModule extends Module {
    public CrystalOptimizerModule() { super("Crystal Optimizer", "Removes crystals instantly on hit (client-side) to hide lag.", Category.PVP); }
}
