package com.protohax.mobile
import com.protohax.relay.RelaySession
import com.protohax.relay.StubRelaySession
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket
import org.cloudburstmc.protocol.bedrock.packet.TextPacket
// Own GameSession: packet bus + local player + level state. MITM-safe, no memory writes.
class GameSession(val relay: RelaySession = StubRelaySession()) {
  val localPlayer = LocalPlayer(this)
  val modules = ModuleManager()
  val protocolVersion: Int get() = relay.protocolVersion
  fun clientBound(p: BedrockPacket) = relay.toClient(p)
  fun serverBound(p: BedrockPacket) = relay.toServer(p)
  fun displayClientMessage(msg: String) {
    val t = TextPacket().apply { type = TextPacket.Type.SYSTEM; message = msg; xuid = ""; sourceName = "" }
    clientBound(t)
  }
  fun handleServerPacket(p: BedrockPacket): Boolean {
    var cancelled = false
    for (m in modules.all) if (m.isEnabled) { if (m.beforeServerBound(InterceptablePacket(p))) { cancelled = true } }
    return cancelled
  }
  fun handleClientPacket(p: BedrockPacket): Boolean {
    var cancelled = false
    for (m in modules.all) if (m.isEnabled) { if (m.beforeClientBound(InterceptablePacket(p))) { cancelled = true } }
    return cancelled
  }
}
