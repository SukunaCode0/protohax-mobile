# How modules get injected on mobile (Horion idea, Android way)

Horion PC = DLL injected into Minecraft.Windows.exe, hooks render + game fns.

Android Bedrock has no DLL injection. Same effect, 3 paths:

A. Overlay demo (no-root, v1 default)
- Foreground service + floating ImGui view OVER Minecraft.
- Visual modules (ClickGUI, ArrayList, Watermark) work fully.
- Gameplay modules stay stubbed until B/C (show NEEDS INJECTOR).

B. Launcher method (real hooks, no root)
- Launcher loads libmodmenu.so first, then dlopens official libminecraftpe.so same process.
- Dobby hooks: ClientInstance::tick, Actor::move, EGL swap for ESP.
- You point it at your legally installed MC. We never ship Mojang code.

C. Root injector (Zygisk/ptrace)
- Inject libmodmenu.so into com.mojang.minecraftpe. Same hooks as B.

Why 1.26+ needs offsets/*.json: every MC update moves addresses.
We scan signatures at startup. Unknown version = hooks stay OFF, menu still opens.
