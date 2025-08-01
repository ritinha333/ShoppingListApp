pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }

    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

rootProject.name = "ShoppingListApp"
include(":app")
 