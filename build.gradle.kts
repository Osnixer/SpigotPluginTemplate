import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("java-library")
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("net.minecrell.plugin-yml.bukkit") version "0.5.1"
    id("xyz.jpenilla.run-paper") version "1.0.6"
}

repositories {
    gradlePluginPortal()
    mavenCentral()
    maven { url = uri("https://hub.spigotmc.org/nexus/content/repositories/snapshots/") }
    maven { url = uri("https://repo.panda-lang.org/releases") }
    maven { url = uri("https://papermc.io/repo/repository/maven-public/") }
}

dependencies {
    // paper lib, spigot api
    compileOnly("org.spigotmc:spigot-api:1.18.2-R0.1-SNAPSHOT")
    api("io.papermc:paperlib:1.0.7")

    // Kyori Adventure
    implementation("net.kyori:adventure-platform-bukkit:4.1.0")
    implementation("net.kyori:adventure-text-minimessage:4.10.1")

    // LiteCommands
    implementation("dev.rollczi.litecommands:bukkit:1.9.0")

    // cdn configs
    implementation("net.dzikoysk:cdn:1.13.22")

    // expressible
    implementation("org.panda-lang:expressible:1.1.19")

    // TriumphGui
    implementation("dev.triumphteam:triumph-gui:3.1.2")
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

bukkit {
    main = "net.osnixer.template.Plugin"
    apiVersion = "1.13"
    author = "Osnixer"
    name = "SpigotTemplate"
    description = "Sample spigot template!"
    version = "${project.version}"
}

tasks {
    runServer {
        minecraftVersion("1.18.2")
    }
}

tasks.withType<ShadowJar> {
    archiveFileName.set("SpigotPluginTemplate v${project.version}.jar")

    exclude("org/intellij/lang/annotations/**")
    exclude("org/jetbrains/annotations/**")
    exclude("org/checkerframework/**")
    exclude("META-INF/**")
    exclude("javax/**")

    mergeServiceFiles()
    minimize()

    relocate("panda", "net.osnixer.template.libs.org.panda")
    relocate("org.panda_lang", "net.osnixer.template.libs.org.panda")
    relocate("net.dzikoysk", "net.osnixer.template.libs.net.dzikoysk")
    relocate("dev.rollczi", "net.osnixer.template.libs.dev.rollczi")

    relocate("io.papermc.lib", "net.osnixer.template.libs.io.papermc.lib")
    relocate("net.kyori", "net.osnixer.template.libs.net.kyori")
    relocate("dev.triumphteam.gui", "net.osnixer.template.libs.dev.triumphteam.gui")
}
