package com.solstice.client.module;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;

/** Base class for every Solstice module. Add a subclass and register it in ModuleManager. */
public abstract class Module {
    public final String name;
    public final String description;
    public final Category category;
    private boolean enabled;

    protected Module(String name, String description, Category category) {
        this.name = name; this.description = description; this.category = category;
    }
    protected static Minecraft mc() { return Minecraft.getInstance(); }

    public boolean isEnabled() { return enabled; }
    public void setEnabled(boolean on) {
        if (on == enabled) return;
        enabled = on;
        if (on) onEnable(); else onDisable();
    }
    public void toggle() { setEnabled(!enabled); }

    protected void onEnable() {}
    protected void onDisable() {}
    /** Called every client tick while enabled. */
    public void onTick() {}
    /** HUD modules return the text/line height they draw; others return 0. */
    public int renderHud(GuiGraphics g, int x, int y) { return 0; }
}
