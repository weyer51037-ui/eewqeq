package com.solstice.client.module.impl;
import com.solstice.client.module.*;
import com.solstice.client.module.Module;

public class ZoomModule extends Module {
    public boolean held;      // set by the zoom keybind
    public double factor = 4; // 4x zoom
    public ZoomModule() { super("Zoom", "Hold the zoom key (C) to zoom in.", Category.VISUAL); }
    public boolean active() { return isEnabled() && held; }
}
