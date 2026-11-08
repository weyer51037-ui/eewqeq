package com.solstice.client.ui;

import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.fabricmc.fabric.api.client.screen.v1.Screens;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.options.OptionsScreen;
import net.minecraft.client.gui.screens.PauseScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.network.chat.Component;

/** Adds a "Solstice" button to the title screen, pause menu and options screen. */
public final class MenuIntegration {
    private static final int W = 78, H = 20;
    private MenuIntegration() {}

    public static void register() {
        ScreenEvents.AFTER_INIT.register((client, screen, w, h) -> {
            if (!SolsticeConfig.menuButtons) return;
            if (!(screen instanceof TitleScreen || screen instanceof PauseScreen || screen instanceof OptionsScreen)) return;
            // Top-left corner is free on all three screens and never collides with vanilla buttons.
            int x = 6, y = 6;
            Button b = Button.builder(Component.literal("Solstice"), btn -> client.setScreen(new SolsticeScreen(screen)))
                .bounds(x, y, W, H).build();
            Screens.getButtons(screen).add(b);
            ScreenEvents.afterRender(screen).register((s, g, mx, my, delta) -> {
                g.fill(x, y + H - 2, x + W, y + H, 0xFFF5A623);
                SolsticeScreen.drawSun(g, x + 4, y + 2);
            });
        });
    }
}
