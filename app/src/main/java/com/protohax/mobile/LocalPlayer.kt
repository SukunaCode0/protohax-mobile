package com.protohax.mobile
import org.cloudburstmc.math.vector.Vector3f
import org.cloudburstmc.protocol.bedrock.packet.InventoryTransactionPacket
import org.cloudburstmc.protocol.bedrock.data.inventory.InventoryTransactionType
// Minimal local-player action emitter. Real attack/movement = crafted serverBound packets.
class LocalPlayer(val session: GameSession) {
  var pos: Vector3f = Vector3f.ZERO
  fun attack(runtimeEntityId: Long) {
    val p = InventoryTransactionPacket().apply {
      transactionType = InventoryTransactionType.ITEM_USE_ON_ENTITY
    }
    session.serverBound(p)
  }
}
