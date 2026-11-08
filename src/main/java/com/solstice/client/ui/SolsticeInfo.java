package com.solstice.client.ui;

import net.fabricmc.loader.api.FabricLoader;

public final class SolsticeInfo {
    private SolsticeInfo() {}
    public static String version() {
        return FabricLoader.getInstance().getModContainer("solstice")
            .map(c -> c.getMetadata().getVersion().getFriendlyString()).orElse("dev");
    }
}
