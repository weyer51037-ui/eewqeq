package com.solstice.client.module.impl;
import com.solstice.client.module.*;
import com.solstice.client.module.Module;

public class ToggleSprintModule extends Module {
    public ToggleSprintModule() { super("Toggle Sprint", "Sprint automatically while moving forward.", Category.UTILITY); }
    @Override public void onTick() {
        var p = mc().player; if (p == null) return;
        if (mc().options.keyUp.isDown() && !p.isUsingItem() && !p.horizontalCollision && p.getFoodData().getFoodLevel() > 6) p.setSprinting(true);
    }
}
