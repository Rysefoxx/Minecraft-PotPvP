plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("io.papermc.paperweight.userdev") version "1.3.6"
}

group = "io.github.rysefoxx.potpvp"

subprojects {
    apply(plugin = "java")
    apply(plugin = "com.github.johnrengelman.shadow")
    apply(plugin = "application")
    apply(plugin = "io.papermc.paperweight.userdev")

    tasks {
        compileJava {
            options.encoding = "UTF-8"
        }
        shadowJar {
            mergeServiceFiles()
        }
    }

}

allprojects {
    version = "1.0"
    java.toolchain.languageVersion.set(JavaLanguageVersion.of(17))

    repositories {
        mavenCentral()
    }

    dependencies {
        compileOnly("org.projectlombok:lombok:1.18.24")
        annotationProcessor("org.projectlombok:lombok:1.18.24")
        paperDevBundle("1.19-R0.1-SNAPSHOT")
    }
}