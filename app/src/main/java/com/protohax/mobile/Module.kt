package com.protohax.mobile
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.serialization.json.*
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket
class InterceptablePacket(val packet: BedrockPacket) { var cancelled = false }
enum class ModuleCategory(val title: String) { Combat("Combat"), Movement("Movement"), Player("Player"), Visual("Visual"), World("World"), Misc("Misc") }
abstract class Value<T>(val name: String, default: T) {
  var value by mutableStateOf(default)
  open fun toJson(): JsonElement = JsonPrimitive(value.toString())
  open fun fromJson(e: JsonElement) {}
  open fun reset() {}
}
class BoolValue(name: String, def: Boolean) : Value<Boolean>(name, def) {
  override fun fromJson(e: JsonElement) { (e as? JsonPrimitive)?.booleanOrNull?.let { value = it } }
}
class FloatValue(name: String, def: Float, val range: ClosedFloatingPointRange<Float>) : Value<Float>(name, def) {
  override fun fromJson(e: JsonElement) { (e as? JsonPrimitive)?.floatOrNull?.let { value = it.coerceIn(range) } }
}
class IntValue(name: String, def: Int, val range: IntRange) : Value<Int>(name, def) {
  override fun fromJson(e: JsonElement) { (e as? JsonPrimitive)?.intOrNull?.let { value = it.coerceIn(range) } }
}
class ChoiceValue(name: String, val options: List<String>, def: Int = 0) : Value<Int>(name, def) {
  override fun fromJson(e: JsonElement) { (e as? JsonPrimitive)?.intOrNull?.let { value = it.coerceIn(options.indices) } }
}
abstract class Module(val name: String, val category: ModuleCategory, defaultEnabled: Boolean = false) {
  lateinit var session: GameSession
  val isSessionCreated get() = ::session.isInitialized
  var isEnabled by mutableStateOf(defaultEnabled); private set
  var isExpanded by mutableStateOf(false)
  val values = mutableListOf<Value<*>>()
  fun setEnabled(v: Boolean) { isEnabled = v; if (v) onEnabled() else onDisabled() }
  open fun onEnabled() { if (isSessionCreated) session.displayClientMessage("§b[Protohax] §7" + name + " §8» " + (if (isEnabled) "§aon" else "§coff")) }
  open fun onDisabled() { if (isSessionCreated) session.displayClientMessage("§b[Protohax] §7" + name + " §8» §coff") }
  open fun beforeServerBound(p: InterceptablePacket): Boolean = false
  open fun beforeClientBound(p: InterceptablePacket): Boolean = false
  protected fun bool(n: String, d: Boolean) = BoolValue(n, d).also { values += it }
  protected fun floatV(n: String, d: Float, r: ClosedFloatingPointRange<Float>) = FloatValue(n, d, r).also { values += it }
  protected fun intV(n: String, d: Int, r: IntRange) = IntValue(n, d, r).also { values += it }
  protected fun choice(n: String, opts: List<String>, d: Int = 0) = ChoiceValue(n, opts, d).also { values += it }
}
class ModuleManager {
  val all = mutableListOf<Module>()
  fun register(m: Module) { all += m }
  fun byCategory(c: ModuleCategory) = all.filter { it.category == c }
}
