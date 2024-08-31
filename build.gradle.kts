plugins {
    id("java")
    id("maven-publish")
}

group = "com.github.sniconmc"
version = "0.1-dev"
description = "Movement-oriented dependency for the SniconMC server network"

repositories {
    mavenCentral()
    maven{
        url = uri("https://jitpack.io")
    }
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("net.minestom:minestom-snapshots:65f75bb059") // Minestom
    implementation("ch.qos.logback:logback-classic:1.5.7") // Logback
    implementation("net.kyori:adventure-text-minimessage:4.17.0") // MiniMessage
    implementation("com.github.SniconMC:Minestom-Utils:0.1.3")
}

tasks.withType<JavaCompile> {
    sourceCompatibility = "21"
    targetCompatibility = "21"
}

java {
    withSourcesJar()
    withJavadocJar()
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])

            pom{
                name = "Minestom-Momentum"
                description = project.description
                url = "https://github.com/SniconMC/Minestom-Momentum"
                licenses {
                    license {
                        name = "The GNU Affero General Public License Version 3"
                        url = "https://www.gnu.org/licenses/agpl-3.0.txt"
                    }
                }
            }
        }
    }
}