#include "ModuleManager.h"
#include "../Modules/AllModules.h"
void registerAllModules() {
    auto& mm = ModuleManager::instance();
    if (!mm.modules.empty()) return;
    registerCombat(mm);
    registerMovement(mm);
    registerPlayer(mm);
    registerVisual(mm);
    registerWorld(mm);
    registerMisc(mm);
}
