plugins {
    java
    `maven-publish`
    signing
    id("org.cadixdev.licenser") version "0.6.1"
}

group = "dev.s7a"
version = "1.0.0"

repositories {
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.19.3-R0.1-SNAPSHOT")
    compileOnly("org.jetbrains:annotations:23.1.0")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.1")
    testImplementation("com.github.seeseemelk:MockBukkit-v1.19:2.141.1")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
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
