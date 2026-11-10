plugins { alias(libs.plugins.kotlin.jvm); `maven-publish` }
group = "com.protohax.mobile.relay"; version = "0.1.0"
// repos come from settings.gradle.kts (FAIL_ON_PROJECT_REPOS); do not add repositories {} here
dependencies {
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1")
  implementation("org.cloudburstmc.protocol:bedrock-connection:3.0.0.Beta1-SNAPSHOT") { exclude(group = "com.nukkitx", module = "natives") }
  implementation("com.google.code.gson:gson:2.10.1")
}
kotlin { jvmToolchain(17) }
