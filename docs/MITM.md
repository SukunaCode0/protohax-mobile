# MITM vs Horion-style injection
Horion PC = DLL injected into Minecraft.Windows.exe, hooks render + actor fns.
Mobile MITM (WClient / ProtoHax 2.x / MuCute / Nova / this project) = NO injection.
Phone runs a local Bedrock relay (this repo :relay on CloudBurst protocol).
MC connects through 127.0.0.1 relay, modules read/rewrite packets (AuthInput, MovePlayer, InventoryTransaction...).
Why 1.26+ all-versions works: protocolVersion + mcpedata runtime block/item mappings per version, NOT memory offsets.
Visual modules (ClickGUI/ArrayList/Watermark) run even with StubRelaySession demo.
Gameplay modules need relay connected + mappings for that protocol.
