plugins { kotlin("jvm"); `maven-publish` }
group = "com.protohax.mobile.relay"; version = "0.1.0"
repositories { mavenCentral(); maven("https://repo.opencollab.dev/maven-releases/"); maven("https://repo.opencollab.dev/maven-snapshots/") }
dependencies {
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1")
  implementation("org.cloudburstmc.protocol:bedrock-connection:3.0.0.Beta1-SNAPSHOT") { exclude(group = "com.nukkitx", module = "natives") }
  implementation("com.google.code.gson:gson:2.10.1")
}
kotlin { jvmToolchain(17) }
