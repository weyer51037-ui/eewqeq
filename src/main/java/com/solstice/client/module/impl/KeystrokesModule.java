package com.solstice.client.module.impl;
import com.solstice.client.module.*;
import com.solstice.client.module.Module;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;

public class KeystrokesModule extends Module {
    public KeystrokesModule() { super("Keystrokes", "Shows WASD and jump key state.", Category.PVP); }
    private void key(GuiGraphics g, String label, KeyMapping k, int x, int y, int w) {
        int bg = k.isDown() ? 0xAAFFFFFF : 0x66000000, fg = k.isDown() ? 0xFF000000 : 0xFFFFFFFF;
        g.fill(x, y, x + w, y + 18, bg);
        g.drawCenteredString(mc().font, label, x + w / 2, y + 5, fg);
    }
    @Override public int renderHud(GuiGraphics g, int x, int y) {
        var o = Minecraft.getInstance().options;
        key(g, "W", o.keyUp, x + 20, y, 18);
        key(g, "A", o.keyLeft, x, y + 20, 18);
        key(g, "S", o.keyDown, x + 20, y + 20, 18);
        key(g, "D", o.keyRight, x + 40, y + 20, 18);
        key(g, "SPACE", o.keyJump, x, y + 40, 58);
        return 62;
    }
}
