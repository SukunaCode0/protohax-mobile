package com.protohax.mobile
import org.cloudburstmc.math.vector.Vector3f
// Minimal local-player action emitter. Real attack = crafted serverBound packet (TODO when relay is live).
class LocalPlayer(val session: GameSession) {
  var pos: Vector3f = Vector3f.ZERO
  fun attack(runtimeEntityId: Long) {
    // TODO: picks InventoryTransactionPacket fields against the pinned CloudBurst version once relay handshake is live.
  }
}
