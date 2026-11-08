# Solstice Client Mod (Fabric, Minecraft 1.21.1)

In-game modules for Solstice Client. Press **Right Shift** to open the module menu.
Hold **C** to zoom, hold **V** for freelook (both rebindable in Controls).

Modules: FPS Counter, CPS Counter, Keystrokes, Coordinates, Clock, Armor Status,
Toggle Sprint, Zoom, Freelook, Sky Color, PvP Performance, Crystal Optimizer, Explosion Optimizer.

## Build
Needs JDK 21. In this folder:  `gradlew build`  (or `gradle build`)  → `build/libs/solstice-client-mod-1.0.0.jar`
Put the jar in an instance's `mods` folder with Fabric Loader + Fabric API for 1.21.1.

## Add a module
1. Create a class extending `Module` in `module/impl/` (name, description, category; override `onTick`/`renderHud`).
2. Add it to the list in `ModuleManager.init()`. It appears in the menu automatically.

STATUS: written without being able to compile or test. Expect to fix small compile errors on first build.
