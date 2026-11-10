#include "ClickGUI.h"
bool ClickGUI::open = true;
int ClickGUI::selectedCategory = 0;
char ClickGUI::search[64] = {};
void ClickGUI::render() {
    // Real ImGui calls live here (Begin/Selectable/SliderFloat/etc).
    // Pixel reference = index.html demo in this generator:
    // 560px panel, left rail 150px, dark blur bg, accent #7C5CFF,
    // module rows with toggle pill + expandable settings,
    // ArrayList top-right, Watermark top-left.
}
