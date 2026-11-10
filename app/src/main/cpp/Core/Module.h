#pragma once
#include <string>
#include <vector>
#include <memory>

enum class Category { Combat, Movement, Player, Visual, World, Misc };

inline const char* categoryName(Category c) {
    switch (c) {
        case Category::Combat: return "Combat";
        case Category::Movement: return "Movement";
        case Category::Player: return "Player";
        case Category::Visual: return "Visual";
        case Category::World: return "World";
        case Category::Misc: return "Misc";
    }
    return "?";
}

struct Setting {
    std::string name;
    enum class Type { Bool, Slider, Mode } type = Type::Bool;
    bool boolVal = false;
    float min = 0, max = 1, floatVal = 0;
    std::vector<std::string> options;
    int modeIdx = 0;
};

class Module {
public:
    std::string name;
    Category category;
    std::string desc;
    bool enabled = false;
    int keybind = 0;
    std::vector<Setting> settings;
    Module(std::string n, Category c, std::string d) : name(std::move(n)), category(c), desc(std::move(d)) {}
    virtual ~Module() = default;
    virtual void onEnable() {}
    virtual void onDisable() {}
    virtual void onTick() {}
    virtual void onRender() {}
    Setting* addBool(const std::string& n, bool def) {
        Setting s; s.name = n; s.type = Setting::Type::Bool; s.boolVal = def;
        settings.push_back(s); return &settings.back();
    }
    Setting* addSlider(const std::string& n, float mn, float mx, float def) {
        Setting s; s.name = n; s.type = Setting::Type::Slider;
        s.min = mn; s.max = mx; s.floatVal = def;
        settings.push_back(s); return &settings.back();
    }
    Setting* addMode(const std::string& n, std::vector<std::string> opts, int def = 0) {
        Setting s; s.name = n; s.type = Setting::Type::Mode;
        s.options = std::move(opts); s.modeIdx = def;
        settings.push_back(s); return &settings.back();
    }
};
