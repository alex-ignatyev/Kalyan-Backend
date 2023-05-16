plugins {
    kotlin("jvm") version "1.8.20"
    id("io.ktor.plugin") version "2.2.4"
    id("com.github.johnrengelman.shadow") version "5.2.0"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.20"
}

group = "com.kalyan"
version = "0.0.1"

application {
    mainClass.set("io.ktor.server.netty.EngineMain")
    project.setProperty("mainClassName", mainClass.get())
}

val sshAntTask = configurations.create("sshAntTask")

dependencies {
    implementation(libs.koin.core)
    implementation(libs.bundles.ktor)
    implementation(libs.bundles.serialization)
    implementation(libs.logback)
    implementation(libs.codec)

    implementation("org.jetbrains.exposed:exposed-core:0.40.1")
    implementation("org.jetbrains.exposed:exposed-dao:0.40.1")
    implementation("org.jetbrains.exposed:exposed-jdbc:0.40.1")
    implementation ("org.postgresql:postgresql:42.5.3")

    sshAntTask("org.apache.ant:ant-jsch:1.10.12")
}

repositories {
    mavenCentral()
    gradlePluginPortal()
    maven { url = uri("https://jitpack.io") }
    maven { url = uri("https://maven.pkg.jetbrains.space/public/p/ktor/eap") }
}

tasks.withType<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar> {
    manifest {
        attributes(
            "Main-Class" to application.mainClass.get()
        )
    }
}

ktor {
    fatJar {
        archiveFileName.set("kalyan_server.jar")
    }
}

ant.withGroovyBuilder {
    "taskdef"(
        "name" to "scp",
        "classname" to "org.apache.tools.ant.taskdefs.optional.ssh.Scp",
        "classpath" to configurations.get("sshAntTask").asPath
    )
    "taskdef"(
        "name" to "ssh",
        "classname" to "org.apache.tools.ant.taskdefs.optional.ssh.SSHExec",
        "classpath" to configurations.get("sshAntTask").asPath
    )
}

task("deploy") {
    dependsOn("clean", "shadowJar")
    ant.withGroovyBuilder {
        doLast {
            val knownHosts = File.createTempFile("knownhosts", "txt")
            val user = "root"
            val host = "185.233.82.216"
            val key = file("keys/kalyan")
            val jarFileName = "kalyan_server.jar"
            try {
                "scp"(
                    "file" to file("build/libs/$jarFileName"),
                    "todir" to "$user@$host:/root/kalyan",
                    "keyfile" to key,
                    "trust" to true,
                    "knownhosts" to knownHosts
                )
                "ssh"(
                    "host" to host,
                    "username" to user,
                    "keyfile" to key,
                    "trust" to true,
                    "knownhosts" to knownHosts,
                    "command" to "mv /root/kalyan/$jarFileName /root/kalyan/kalyan.jar"
                )
                "ssh"(
                    "host" to host,
                    "username" to user,
                    "keyfile" to key,
                    "trust" to true,
                    "knownhosts" to knownHosts,
                    "command" to "systemctl stop kalyan"
                )
                "ssh"(
                    "host" to host,
                    "username" to user,
                    "keyfile" to key,
                    "trust" to true,
                    "knownhosts" to knownHosts,
                    "command" to "systemctl start kalyan"
                )
            } finally {
                knownHosts.delete()
            }
        }
    }
}
