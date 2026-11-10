#pragma once
#include "Module.h"
class ModuleManager {
public:
    std::vector<std::unique_ptr<Module>> modules;
    void add(std::unique_ptr<Module> m) { modules.push_back(std::move(m)); }
    Module* find(const std::string& name) {
        for (auto& m : modules) if (m->name == name) return m.get();
        return nullptr;
    }
    void tickAll() { for (auto& m : modules) if (m->enabled) m->onTick(); }
    void renderAll() { for (auto& m : modules) if (m->enabled) m->onRender(); }
    static ModuleManager& instance() { static ModuleManager i; return i; }
};
void registerAllModules();
