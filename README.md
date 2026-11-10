# ProtohaxMobile — Horion/Borion-style Bedrock mod menu (Android)

PC vibes on mobile: left category rail, search, smooth toggles, ArrayList + watermark.

Quick start:
1. Create a new GitHub repo, upload everything from `src/mc-client/` to it.
2. On GitHub: rename folder `github-workflows/` -> `.github/workflows/` (dots aren't allowed in this editor's `src/`, so it's stored without the dot).
3. Go to Actions tab -> `build-apk` -> Run -> download `ProtohaxMobile-debug` artifact.
4. Install APK, grant overlay, tap Start, then open official Minecraft Bedrock.

Layout:
- `app/` = Android loader (overlay service, no-root demo mode)
- `app/src/main/cpp/` = your own Module system + SDK + ImGui ClickGUI
- `offsets/` = one JSON per MC version (this is how 1.26+ all-versions works)
- `docs/INJECTION.md` = how modules inject (overlay vs launcher vs root)

Live UI preview of the PC-style ClickGUI is the generator page itself (index.html) — same categories/modules as the C++ code.
