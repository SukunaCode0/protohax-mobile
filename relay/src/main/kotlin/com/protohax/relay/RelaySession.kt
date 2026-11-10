package com.protohax.relay
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket
// Own clean-room relay facade. Wire to bedrock-connection server+client peers here.
// v1: interface + local loopback stub so :app compiles and demo APK runs without MC.
interface RelaySession {
  val protocolVersion: Int
  fun toClient(packet: BedrockPacket)
  fun toServer(packet: BedrockPacket)
  fun close()
}
class StubRelaySession(override val protocolVersion: Int = 800) : RelaySession {
  override fun toClient(packet: BedrockPacket) {}
  override fun toServer(packet: BedrockPacket) {}
  override fun close() {}
}
