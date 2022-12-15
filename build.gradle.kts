import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import dev.s7a.gradle.minecraft.server.tasks.LaunchMinecraftServerTask
import dev.s7a.gradle.minecraft.server.tasks.LaunchMinecraftServerTask.JarUrl
import net.minecrell.pluginyml.bukkit.BukkitPluginDescription

plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "7.1.2" apply false
    id("net.minecrell.plugin-yml.bukkit") version "0.5.2" apply false
    id("dev.s7a.gradle.minecraft.server") version "2.0.0" apply false
}

group = "dev.s7a"
version = "1.0-SNAPSHOT"

allprojects {
    repositories {
        mavenCentral()
        maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    }
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.19.3-R0.1-SNAPSHOT")
    compileOnly("org.jetbrains:annotations:23.1.0")
}

project(":test") {
    apply(plugin = "java")
    apply(plugin = "com.github.johnrengelman.shadow")
    apply(plugin = "net.minecrell.plugin-yml.bukkit")
    apply(plugin = "dev.s7a.gradle.minecraft.server")

    dependencies {
        compileOnly("org.spigotmc:spigot-api:1.19.3-R0.1-SNAPSHOT")
        implementation(project(":"))
    }

    configure<BukkitPluginDescription> {
        main = "dev.s7a.base64.Main"
        version = rootProject.version.toString()
        apiVersion = "1.13"
    }

    task<LaunchMinecraftServerTask>("testPlugin") {
        dependsOn("build")

        doFirst {
            copy {
                from(buildDir.resolve("libs/${project.name}.jar"))
                into(buildDir.resolve("MinecraftServer/plugins"))
            }
        }

        jarUrl.set(JarUrl.Paper("1.19.3"))
        agreeEula.set(true)
    }

    tasks.withType<ShadowJar> {
        archiveClassifier.set("")
    }

    tasks.build {
        dependsOn("shadowJar")
    }
}
