package com.solstice.client.module.impl;
import com.solstice.client.module.*;
import com.solstice.client.module.Module;
import net.minecraft.world.phys.Vec3;

/** Tints the sky. (Full cubemap skyboxes are a planned follow-up.) */
public class SkyColorModule extends Module {
    public Vec3 color = new Vec3(0.30, 0.55, 1.0); // Solstice blue; values are 0..1 RGB
    public SkyColorModule() { super("Sky Color", "Tints the sky Solstice blue.", Category.VISUAL); }
}
