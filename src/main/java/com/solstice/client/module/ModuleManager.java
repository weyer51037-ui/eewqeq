package com.solstice.client.module;

import com.google.gson.*;
import com.solstice.client.module.impl.*;
import net.fabricmc.loader.api.FabricLoader;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public final class ModuleManager {
    private static final List<Module> MODULES = new ArrayList<>();
    private static final Path FILE = FabricLoader.getInstance().getConfigDir().resolve("solstice.json");

    public static FreelookModule freelook;
    public static ZoomModule zoom;
    public static CpsModule cps;
    public static SkyColorModule sky;
    public static CrystalOptimizerModule crystal;
    public static ExplosionOptimizerModule explosions;

    public static void init() {
        freelook = new FreelookModule();
        zoom = new ZoomModule();
        cps = new CpsModule();
        sky = new SkyColorModule();
        crystal = new CrystalOptimizerModule();
        explosions = new ExplosionOptimizerModule();
        MODULES.addAll(List.of(
            new FpsModule(), cps, new KeystrokesModule(), new CoordinatesModule(), new ClockModule(),
            new ArmorStatusModule(), new ToggleSprintModule(), zoom, freelook, sky, new PvpPerformanceModule(), crystal, explosions
        ));
        load();
    }

    public static List<Module> all() { return MODULES; }
    public static void tick() { for (Module m : MODULES) if (m.isEnabled()) m.onTick(); }

    public static void load() {
        try {
            if (!Files.exists(FILE)) { defaults(); return; }
            JsonObject o = JsonParser.parseString(Files.readString(FILE)).getAsJsonObject();
            for (Module m : MODULES) if (o.has(m.name) && o.get(m.name).getAsBoolean()) m.setEnabled(true);
        } catch (Exception e) { defaults(); }
    }
    private static void defaults() { for (Module m : MODULES) if (m.category == Category.HUD && !(m instanceof ClockModule)) m.setEnabled(true); }

    public static void save() {
        JsonObject o = new JsonObject();
        for (Module m : MODULES) o.addProperty(m.name, m.isEnabled());
        try { Files.writeString(FILE, new GsonBuilder().setPrettyPrinting().create().toJson(o)); } catch (IOException ignored) {}
    }
}
