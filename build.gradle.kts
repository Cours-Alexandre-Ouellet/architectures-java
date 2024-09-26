plugins {
    id("java")
    application
}

group = "edu.cegepvicto"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

application {
    mainClass = "edu.cegepvicto.application.Application"
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

/*tasks {
    run {
        standardInput = System.`in`
    }
}*/

tasks.test {
    useJUnitPlatform()
}