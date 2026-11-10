# ProtohaxMobile — MITM utility client for Bedrock (Horion/Borion-style UI, PC vibes)

Own clean-room code inspired by WClient / ProtoHax / MuCute / Nova architecture. No copied module bodies, no Mojang code.

## How it works (not Horion DLL injection)

Horion PC injects a DLL into the game. Android MITM does not touch game memory:

- Phone runs local Bedrock relay (`:relay` on CloudBurst protocol + `relay/mcpedata` mappings).
- Minecraft connects through `127.0.0.1` relay. Modules read/rewrite packets (`PlayerAuthInput`, `MovePlayer`, `InventoryTransaction`, ...).
- 1.26+ all-versions = `protocolVersion` + per-version block/item mappings, not memory offsets.
- No root. Visual modules (ClickGUI / ArrayList / Watermark) work even in demo mode with `StubRelaySession`.

## Repo layout (this folder = repo root on GitHub)

- `settings.gradle.kts`, `gradle/libs.versions.toml`
- `relay/` — own relay facade on CloudBurst `bedrock-connection`
- `app/` — `GameSession`, `Module` system, 21 packet modules, overlay `MenuService`, Compose `ClickGUIScreen` (PC-vibe left rail + search + toggles + ArrayList)
- `docs/MITM.md` — architecture note
- `github-workflows/build-apk.txt` — RENAME to `.github/workflows/build-apk.yml` on GitHub (dots aren't allowed in this editor's `src/`)
- `relay/mcpedata/` — drop per-version block/item mapping tables here (source: mccheatz/mcpedata)

## GitHub setup

1. Upload this folder to `SukunaCode0/protohax-mobile` (done).
2. Rename the two files above (dots aren't allowed in this editor's `src/`).
3. Actions → `build-apk` → download `ProtohaxMobile-debug`.
4. Install, grant overlay, start menu, point MC at local relay.

Live UI pixel reference = this generator's page (same categories/modules as `Modules.kt`).
