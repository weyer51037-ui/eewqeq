package com.solstice.client.ui;

import com.google.gson.*;
import net.fabricmc.loader.api.FabricLoader;
import java.io.IOException;
import java.nio.file.*;

/** UI-level settings (separate from per-module on/off state in solstice.json). */
public final class SolsticeConfig {
    private static final Path FILE = FabricLoader.getInstance().getConfigDir().resolve("solstice-ui.json");

    /** Show the "Solstice" button on the title, pause and options screens. */
    public static boolean menuButtons = true;
    /** Draw the Solstice hub with a translucent backdrop instead of the dimmed world blur. */
    public static boolean compactHub = false;

    private SolsticeConfig() {}

    public static void load() {
        try {
            if (!Files.exists(FILE)) return;
            JsonObject o = JsonParser.parseString(Files.readString(FILE)).getAsJsonObject();
            if (o.has("menuButtons")) menuButtons = o.get("menuButtons").getAsBoolean();
            if (o.has("compactHub")) compactHub = o.get("compactHub").getAsBoolean();
        } catch (Exception ignored) {}
    }

    public static void save() {
        JsonObject o = new JsonObject();
        o.addProperty("menuButtons", menuButtons);
        o.addProperty("compactHub", compactHub);
        try { Files.writeString(FILE, new GsonBuilder().setPrettyPrinting().create().toJson(o)); } catch (IOException ignored) {}
    }
}
