rootProject.name = "PotPvP"

pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://repo.papermc.io/repository/maven-public/")
    }
}

include("core")
include("auctionhouse")
