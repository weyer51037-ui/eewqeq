package com.solstice.client;

import com.mojang.blaze3d.platform.InputConstants;
import com.solstice.client.module.*;
import com.solstice.client.module.Module;
import com.solstice.client.ui.MenuIntegration;
import com.solstice.client.ui.SolsticeConfig;
import com.solstice.client.ui.SolsticeScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import org.lwjgl.glfw.GLFW;

public class SolsticeMod implements ClientModInitializer {
    private static final String CAT = "key.categories.solstice";
    @Override public void onInitializeClient() {
        ModuleManager.init();
        SolsticeConfig.load();
        MenuIntegration.register();
        KeyMapping menu = KeyBindingHelper.registerKeyBinding(new KeyMapping("key.solstice.menu", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_RIGHT_SHIFT, CAT));
        KeyMapping zoom = KeyBindingHelper.registerKeyBinding(new KeyMapping("key.solstice.zoom", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_C, CAT));
        KeyMapping look = KeyBindingHelper.registerKeyBinding(new KeyMapping("key.solstice.freelook", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_V, CAT));

        ClientTickEvents.END_CLIENT_TICK.register(mc -> {
            ModuleManager.zoom.held = zoom.isDown();
            ModuleManager.freelook.held = look.isDown();
            while (menu.consumeClick()) mc.setScreen(new SolsticeScreen(mc.screen));
            ModuleManager.tick();
        });

        HudRenderCallback.EVENT.register((g, delta) -> {
            Minecraft mc = Minecraft.getInstance();
            if (mc.player == null || mc.options.hideGui) return;
            int y = 6;
            for (Module m : ModuleManager.all()) {
                if (!m.isEnabled()) continue;
                int used = m.renderHud(g, 6, y);
                if (used > 0) y += used + 4;
            }
        });
    }
}
