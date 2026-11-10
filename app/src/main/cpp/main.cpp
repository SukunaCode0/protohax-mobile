#include <jni.h>
#include "Core/ModuleManager.h"
#include "SDK/Offsets.h"
extern "C" JNIEXPORT void JNICALL
Java_com_protohax_client_NativeBridge_init(JNIEnv* env, jclass, jobject service) {
    (void)env; (void)service;
    registerAllModules();
}
extern "C" JNIEXPORT void JNICALL
Java_com_protohax_client_NativeBridge_shutdown(JNIEnv*, jclass) {}
extern "C" JNIEXPORT jstring JNICALL
Java_com_protohax_client_NativeBridge_getSupportedVersions(JNIEnv* env, jclass) {
    return env->NewStringUTF("[\"1.26.0+\", \"offsets/*.json\"]");
}
bool OffsetDB::loadFromJson(const std::string&) { loaded = true; return true; }
void KillAura::onTick() {} void Reach::onTick() {} void Hitbox::onTick() {}
void Fly::onTick() {} void Speed::onTick() {} void Sprint::onTick() {}
void NoFall::onTick() {} void Step::onTick() {} void Phase::onTick() {}
void NoHunger::onTick() {} void FastEat::onTick() {} void AutoTool::onTick() {}
void ESP::onRender() {} void FullBright::onEnable() {} void FullBright::onDisable() {}
void ArrayList::onRender() {} void Watermark::onRender() {}
void Nuker::onTick() {} void Scaffold::onTick() {} void Timer::onTick() {}
void Disabler::onTick() {}
