package com.solstice.client.module.impl;
import com.solstice.client.module.*;
import com.solstice.client.module.Module;
import net.minecraft.client.CameraType;

public class FreelookModule extends Module {
    public boolean held;          // set by the freelook keybind
    public float yaw, pitch;
    private boolean wasActive;
    private CameraType prev;
    public FreelookModule() { super("Freelook", "Hold the freelook key (V) to look around without turning.", Category.VISUAL); }
    public boolean active() { return isEnabled() && held; }
    @Override public void onTick() {
        boolean now = active();
        var p = mc().player; if (p == null) return;
        if (now && !wasActive) { yaw = p.getYRot(); pitch = p.getXRot(); prev = mc().options.getCameraType(); mc().options.setCameraType(CameraType.THIRD_PERSON_BACK); }
        if (!now && wasActive && prev != null) mc().options.setCameraType(prev);
        wasActive = now;
    }
    @Override protected void onDisable() { if (wasActive && prev != null) mc().options.setCameraType(prev); wasActive = false; held = false; }
}
