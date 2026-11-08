package com.solstice.client.module.impl;
import com.solstice.client.module.*;
import com.solstice.client.module.Module;
import net.minecraft.client.gui.GuiGraphics;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ClockModule extends Module {
    private static final DateTimeFormatter F = DateTimeFormatter.ofPattern("HH:mm");
    public ClockModule() { super("Clock", "Shows your real-world time.", Category.HUD); }
    @Override public int renderHud(GuiGraphics g, int x, int y) {
        g.drawString(mc().font, LocalTime.now().format(F), x, y, 0xFFFFFFFF, true);
        return 11;
    }
}
