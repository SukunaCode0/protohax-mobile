package com.protohax.mobile
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
// PC-vibe ClickGUI: left rail categories, search, module cards with toggle + expandable values, ArrayList.
@Composable
fun ClickGUIScreen(mods: List<Module>) {
  var sel by remember { mutableStateOf(ModuleCategory.Combat) }
  var q by remember { mutableStateOf("") }
  val accent = Color(0xFF7C5CFF)
  Row(Modifier.fillMaxSize().background(Color(0xFF0E0E16))) {
    Column(Modifier.width(168.dp).fillMaxHeight().background(Color(0xFF0A0A12)).padding(10.dp)) {
      Text("CLICKGUI", color = Color.Gray)
      ModuleCategory.values().forEach { c ->
        val active = c == sel
        Text(c.title, modifier = Modifier.fillMaxWidth().background(if (active) accent else Color.Transparent, RoundedCornerShape(10.dp)).clickable { sel = c }.padding(10.dp), color = Color.White)
      }
    }
    Column(Modifier.weight(1f).padding(14.dp)) {
      OutlinedTextField(q, { q = it }, label = { Text("Search modules") }, modifier = Modifier.fillMaxWidth())
      LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.padding(top = 10.dp)) {
        val list = mods.filter { it.category == sel && (q.isBlank() || it.name.contains(q, true)) }
        items(list) { m ->
          Card(colors = CardDefaults.cardColors(containerColor = Color(0xFF151522))) {
            Column(Modifier.padding(12.dp)) {
              Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                Text(m.name, color = Color.White)
                Switch(m.isEnabled, { m.setEnabled(it) })
              }
              m.values.forEach { v ->
                when (v) {
                  is BoolValue -> Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                    Text(v.name, color = Color.LightGray); Checkbox(v.value, { v.value = it })
                  }
                  is FloatValue -> Column { Text(v.name + ": " + v.value, color = Color.LightGray); Slider(v.value, { v.value = it }, valueRange = v.range) }
                  is IntValue -> Column { Text(v.name + ": " + v.value, color = Color.LightGray); Slider(v.value.toFloat(), { v.value = it.toInt() }, valueRange = v.range.first.toFloat()..v.range.last.toFloat()) }
                  is ChoiceValue -> Row { v.options.forEachIndexed { i, o -> FilterChip(i == v.value, { v.value = i }, { Text(o) }) } }
                }
              }
            }
          }
        }
      }
    }
  }
}
@Composable
fun ArrayListOverlay(mods: List<Module>) {
  Column(Modifier.padding(10.dp)) {
    mods.filter { it.isEnabled }.forEach { Text(it.name, color = Color.White, modifier = Modifier.background(Color(0xAA101018)).padding(4.dp)) }
  }
}
