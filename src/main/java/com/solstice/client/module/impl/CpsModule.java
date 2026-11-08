package com.solstice.client.module.impl;
import com.solstice.client.module.*;
import com.solstice.client.module.Module;
import net.minecraft.client.gui.GuiGraphics;
import java.util.ArrayDeque;

public class CpsModule extends Module {
    private final ArrayDeque<Long> left = new ArrayDeque<>(), right = new ArrayDeque<>();
    public CpsModule() { super("CPS Counter", "Clicks per second (left | right).", Category.PVP); }
    public void click(int button) {
        if (!isEnabled()) return;
        long now = System.currentTimeMillis();
        if (button == 0) left.add(now); else if (button == 1) right.add(now);
    }
    private static int count(ArrayDeque<Long> q) {
        long cutoff = System.currentTimeMillis() - 1000;
        while (!q.isEmpty() && q.peekFirst() < cutoff) q.pollFirst();
        return q.size();
    }
    @Override public int renderHud(GuiGraphics g, int x, int y) {
        g.drawString(mc().font, "CPS: " + count(left) + " | " + count(right), x, y, 0xFFFFFFFF, true);
        return 11;
    }
}
