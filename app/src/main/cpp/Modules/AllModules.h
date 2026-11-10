#pragma once
#include "../Core/ModuleManager.h"
struct KillAura : Module {
    KillAura() : Module("KillAura", Category::Combat, "Attacks entities around you") {
        addSlider("Range", 2.0f, 7.0f, 4.2f);
        addSlider("CPS", 1.0f, 20.0f, 10.0f);
        addBool("Mobs", true);
        addBool("Players", true);
    }
    void onTick() override;
};
struct Reach : Module {
    Reach() : Module("Reach", Category::Combat, "Longer attack distance") {
        addSlider("Distance", 3.0f, 8.0f, 4.0f);
    }
    void onTick() override;
};
struct Hitbox : Module {
    Hitbox() : Module("Hitbox", Category::Combat, "Bigger entity hitboxes") {
        addSlider("Size", 0.2f, 5.0f, 1.2f);
    }
    void onTick() override;
};
struct Fly : Module {
    Fly() : Module("Fly", Category::Movement, "Creative-like flight") {
        addMode("Mode", {"Vanilla", "Jetpack", "Glide"}, 0);
        addSlider("Speed", 0.1f, 5.0f, 1.0f);
    }
    void onTick() override;
};
struct Speed : Module {
    Speed() : Module("Speed", Category::Movement, "Move faster") {
        addMode("Mode", {"Vanilla", "BHop", "LowHop"}, 1);
        addSlider("Speed", 0.1f, 5.0f, 1.0f);
    }
    void onTick() override;
};
struct Sprint : Module {
    Sprint() : Module("Sprint", Category::Movement, "Always sprint") {}
    void onTick() override;
};
struct NoFall : Module {
    NoFall() : Module("NoFall", Category::Movement, "No fall damage") {
        addMode("Mode", {"Spoof", "Packet"}, 0);
    }
    void onTick() override;
};
struct Step : Module {
    Step() : Module("Step", Category::Movement, "Step up blocks") {
        addSlider("Height", 1.0f, 3.0f, 1.0f);
    }
    void onTick() override;
};
struct Phase : Module {
    Phase() : Module("Phase", Category::Movement, "Clip through blocks") {}
    void onTick() override;
};
struct NoHunger : Module {
    NoHunger() : Module("NoHunger", Category::Player, "No hunger drain") {}
    void onTick() override;
};
struct FastEat : Module {
    FastEat() : Module("FastEat", Category::Player, "Eat instantly") {
        addSlider("Speed", 1.0f, 32.0f, 16.0f);
    }
    void onTick() override;
};
struct AutoTool : Module {
    AutoTool() : Module("AutoTool", Category::Player, "Auto best tool") {}
    void onTick() override;
};
struct ESP : Module {
    ESP() : Module("ESP", Category::Visual, "Boxes over entities") {
        addBool("Players", true);
        addBool("Mobs", true);
        addBool("Boxes", true);
        addBool("Tracers", false);
    }
    void onRender() override;
};
struct FullBright : Module {
    FullBright() : Module("FullBright", Category::Visual, "Max brightness") {}
    void onEnable() override; void onDisable() override;
};
struct ArrayList : Module {
    ArrayList() : Module("ArrayList", Category::Visual, "Enabled module list") {
        addBool("Rainbow", false);
    }
    void onRender() override;
};
struct Watermark : Module {
    Watermark() : Module("Watermark", Category::Visual, "Top-left client watermark") {}
    void onRender() override;
};
struct ClickGuiMod : Module {
    ClickGuiMod() : Module("ClickGUI", Category::Visual, "PC-style click menu") {}
};
struct Nuker : Module {
    Nuker() : Module("Nuker", Category::World, "Break around you") {
        addSlider("Radius", 1.0f, 6.0f, 3.0f);
        addBool("Flat", false);
    }
    void onTick() override;
};
struct Scaffold : Module {
    Scaffold() : Module("Scaffold", Category::World, "Bridge under you") {
        addBool("Tower", false);
    }
    void onTick() override;
};
struct Timer : Module {
    Timer() : Module("Timer", Category::World, "Client tick speed") {
        addSlider("Speed", 0.1f, 3.0f, 1.2f);
    }
    void onTick() override;
};
struct Disabler : Module {
    Disabler() : Module("Disabler", Category::Misc, "Anti-cheat heuristics") {
        addMode("Server", {"CubeCraft", "Hive", "Lifeboat", "Generic"}, 3);
    }
    void onTick() override;
};
inline void registerCombat(ModuleManager& mm) {
    mm.add(std::make_unique<KillAura>());
    mm.add(std::make_unique<Reach>());
    mm.add(std::make_unique<Hitbox>());
}
inline void registerMovement(ModuleManager& mm) {
    mm.add(std::make_unique<Fly>());
    mm.add(std::make_unique<Speed>());
    mm.add(std::make_unique<Sprint>());
    mm.add(std::make_unique<NoFall>());
    mm.add(std::make_unique<Step>());
    mm.add(std::make_unique<Phase>());
}
inline void registerPlayer(ModuleManager& mm) {
    mm.add(std::make_unique<NoHunger>());
    mm.add(std::make_unique<FastEat>());
    mm.add(std::make_unique<AutoTool>());
}
inline void registerVisual(ModuleManager& mm) {
    mm.add(std::make_unique<ESP>());
    mm.add(std::make_unique<FullBright>());
    auto al = std::make_unique<ArrayList>(); al->enabled = true;
    mm.add(std::move(al));
    auto wm = std::make_unique<Watermark>(); wm->enabled = true;
    mm.add(std::move(wm));
    mm.add(std::make_unique<ClickGuiMod>());
}
inline void registerWorld(ModuleManager& mm) {
    mm.add(std::make_unique<Nuker>());
    mm.add(std::make_unique<Scaffold>());
    mm.add(std::make_unique<Timer>());
}
inline void registerMisc(ModuleManager& mm) {
    mm.add(std::make_unique<Disabler>());
}
