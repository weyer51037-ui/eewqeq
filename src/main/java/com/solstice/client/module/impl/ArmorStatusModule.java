package com.solstice.client.module.impl;
import com.solstice.client.module.*;
import com.solstice.client.module.Module;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;

public class ArmorStatusModule extends Module {
    private static final EquipmentSlot[] SLOTS = { EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET, EquipmentSlot.MAINHAND };
    public ArmorStatusModule() { super("Armor Status", "Shows armor and held item durability.", Category.PVP); }
    @Override public int renderHud(GuiGraphics g, int x, int y) {
        var p = mc().player; if (p == null) return 0;
        int used = 0;
        for (EquipmentSlot s : SLOTS) {
            ItemStack st = p.getItemBySlot(s);
            if (st.isEmpty()) continue;
            g.renderItem(st, x, y + used);
            String txt = st.isDamageableItem() ? String.valueOf(st.getMaxDamage() - st.getDamageValue()) : (st.getCount() > 1 ? "x" + st.getCount() : "");
            g.drawString(mc().font, txt, x + 20, y + used + 4, 0xFFFFFFFF, true);
            used += 18;
        }
        return used;
    }
}
