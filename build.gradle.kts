import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import dev.s7a.gradle.minecraft.server.tasks.LaunchMinecraftServerTask
import dev.s7a.gradle.minecraft.server.tasks.LaunchMinecraftServerTask.JarUrl
import net.minecrell.pluginyml.bukkit.BukkitPluginDescription

plugins {
    java
    `maven-publish`
    signing
    id("org.cadixdev.licenser") version "0.6.1"
    id("com.github.johnrengelman.shadow") version "8.1.1" apply false
    id("net.minecrell.plugin-yml.bukkit") version "0.5.3" apply false
    id("dev.s7a.gradle.minecraft.server") version "2.1.0" apply false
}

group = "dev.s7a"
version = "1.0.0"

allprojects {
    repositories {
        mavenCentral()
        maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    }
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.19.3-R0.1-SNAPSHOT")
    compileOnly("org.jetbrains:annotations:24.0.1")
}

val sourceJar by tasks.registering(Jar::class) {
    archiveClassifier.set("sources")
    from(sourceSets["main"].allSource)
}

publishing {
    repositories {
        maven {
            url = uri(
                if (version.toString().endsWith("SNAPSHOT")) {
                    "https://s01.oss.sonatype.org/content/repositories/snapshots/"
                } else {
                    "https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/"
                }
            )
            credentials {
                username = project.properties["credentials.username"].toString()
                password = project.properties["credentials.password"].toString()
            }
        }
    }
    publications {
        register<MavenPublication>("maven") {
            groupId = "dev.s7a"
            artifactId = "base64-itemstack"
            from(components["java"])
            artifact(sourceJar.get())
            pom {
                packaging = "pom"
                name.set("base64-itemstack")
                description.set("ItemStack and Base64 (string) conversion library for Spigot")
                url.set("https://github.com/sya-ri/base64-itemstack")
                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://github.com/sya-ri/base64-itemstack/blob/master/LICENSE")
                    }
                }
                developers {
                    developer {
                        id.set("sya-ri")
                        name.set("sya-ri")
                        email.set("contact@s7a.dev")
                    }
                }
                scm {
                    url.set("https://github.com/sya-ri/base64-itemstack.git")
                }
            }
        }
    }
}

signing {
    sign(publishing.publications["maven"])
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
