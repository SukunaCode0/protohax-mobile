#pragma once
#include <string>
#include <unordered_map>
struct OffsetDB {
    std::string mcVersion = "unknown";
    std::unordered_map<std::string, uintptr_t> offsets;
    std::unordered_map<std::string, std::string> signatures;
    bool loaded = false;
    bool loadFromJson(const std::string& json);
    bool has(const std::string& key) const { return offsets.count(key) != 0; }
    static OffsetDB& instance() { static OffsetDB i; return i; }
};
