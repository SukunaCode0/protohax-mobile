package com.protohax.mobile
import org.cloudburstmc.protocol.bedrock.packet.PlayerAuthInputPacket
// Own packet-logic modules. Safe defaults; server-specific bypasses stay as TODO + Disabler presets.
class KillAura : Module("KillAura", ModuleCategory.Combat) {
  val range = floatV("range", 4.2f, 2f..8f)
  val cps = intV("cps", 10, 1..20)
  var last = 0L
  override fun beforeServerBound(p: InterceptablePacket): Boolean {
    val pk = p.packet as? PlayerAuthInputPacket ?: return false
    val now = System.currentTimeMillis()
    if (now - last < 1000L / cps.value) return false
    last = now
    return false
  }
}
class Reach : Module("Reach", ModuleCategory.Combat) { val dist = floatV("distance", 4f, 3f..8f) }
class Hitbox : Module("Hitbox", ModuleCategory.Combat) { val size = floatV("size", 1.2f, 0.2f..5f) }
class Fly : Module("Fly", ModuleCategory.Movement) {
  val mode = choice("mode", listOf("Vanilla", "Jetpack", "Glide")); val speed = floatV("speed", 1f, 0.1f..5f)
}
class Speed : Module("Speed", ModuleCategory.Movement) {
  val mode = choice("mode", listOf("Vanilla", "BHop", "LowHop"), 1); val speed = floatV("speed", 1f, 0.1f..5f)
}
class Sprint : Module("Sprint", ModuleCategory.Movement)
class NoFall : Module("NoFall", ModuleCategory.Movement) { val mode = choice("mode", listOf("Spoof", "Packet")) }
class Step : Module("Step", ModuleCategory.Movement) { val h = floatV("height", 1f, 1f..3f) }
class Phase : Module("Phase", ModuleCategory.Movement)
class NoHunger : Module("NoHunger", ModuleCategory.Player)
class FastEat : Module("FastEat", ModuleCategory.Player) { val s = floatV("speed", 16f, 1f..32f) }
class AutoTool : Module("AutoTool", ModuleCategory.Player)
class ESP : Module("ESP", ModuleCategory.Visual) {
  val players = bool("players", true); val mobs = bool("mobs", true); val boxes = bool("boxes", true)
}
class FullBright : Module("FullBright", ModuleCategory.Visual)
class ArrayListMod : Module("ArrayList", ModuleCategory.Visual, true)
class WatermarkMod : Module("Watermark", ModuleCategory.Visual, true)
class ClickGuiMod : Module("ClickGUI", ModuleCategory.Visual, true)
class Nuker : Module("Nuker", ModuleCategory.World) { val r = floatV("radius", 3f, 1f..6f) }
class Scaffold : Module("Scaffold", ModuleCategory.World) { val tower = bool("tower", false) }
class TimerMod : Module("Timer", ModuleCategory.World) { val s = floatV("speed", 1.2f, 0.1f..3f) }
class Disabler : Module("Disabler", ModuleCategory.Misc) { val server = choice("server", listOf("CubeCraft", "Hive", "Lifeboat", "Generic"), 3) }
fun defaultModules(): List<Module> = listOf(
  KillAura(), Reach(), Hitbox(), Fly(), Speed(), Sprint(), NoFall(), Step(), Phase(),
  NoHunger(), FastEat(), AutoTool(), ESP(), FullBright(), ArrayListMod(), WatermarkMod(), ClickGuiMod(),
  Nuker(), Scaffold(), TimerMod(), Disabler()
)
