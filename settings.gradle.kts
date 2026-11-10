pluginManagement {
  repositories {
    google(); mavenCentral(); gradlePluginPortal()
    maven("https://repo.opencollab.dev/maven-releases/")
    maven("https://repo.opencollab.dev/maven-snapshots/")
  }
}
dependencyResolutionManagement {
  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
  repositories {
    google(); mavenCentral()
    maven("https://repo.opencollab.dev/maven-releases/")
    maven("https://repo.opencollab.dev/maven-snapshots/")
    maven("https://mccheatz.github.io/maven_repo/")
  }
}
rootProject.name = "ProtohaxMobile"
include(":app", ":relay")
