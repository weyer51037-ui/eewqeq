package com.solstice.client.module.impl;
import com.solstice.client.module.*;
import com.solstice.client.module.Module;
import net.minecraft.client.gui.GuiGraphics;

public class CoordinatesModule extends Module {
    public CoordinatesModule() { super("Coordinates", "Shows your XYZ position.", Category.HUD); }
    @Override public int renderHud(GuiGraphics g, int x, int y) {
        var p = mc().player; if (p == null) return 0;
        g.drawString(mc().font, String.format("XYZ: %.1f / %.1f / %.1f", p.getX(), p.getY(), p.getZ()), x, y, 0xFFFFFFFF, true);
        return 11;
    }
}
