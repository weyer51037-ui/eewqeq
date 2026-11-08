package com.solstice.client.module.impl;
import com.solstice.client.module.*;
import com.solstice.client.module.Module;
import net.minecraft.client.gui.GuiGraphics;

public class FpsModule extends Module {
    private int frames, shown; private long last = System.nanoTime();
    public FpsModule() { super("FPS Counter", "Shows your frames per second.", Category.HUD); }
    @Override public int renderHud(GuiGraphics g, int x, int y) {
        frames++;
        long now = System.nanoTime();
        if (now - last >= 1_000_000_000L) { shown = frames; frames = 0; last = now; }
        g.drawString(mc().font, "FPS: " + shown, x, y, 0xFFFFFFFF, true);
        return 11;
    }
}
