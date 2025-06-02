pluginManagement {
    includeBuild("build-logic/convention")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Movie Junkie"
include(":app")
include(":core:common")
include(":feature:movie")
include(":core:data")
include(":core:domain")
include(":core:ui")
include(":build-logic:convention")
include(":core:network")
include(":feature:moviedetail")
include(":build-logic:conventions")
