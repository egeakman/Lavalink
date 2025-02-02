import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        mavenLocal()
        maven("https://plugins.gradle.org/m2/")
        maven("https://repo.spring.io/plugins-release")
        maven("https://jitpack.io")
        maven("https://m2.dv8tion.net/releases")
    }

    dependencies {
        classpath("gradle.plugin.com.gorylenko.gradle-git-properties:gradle-git-properties:1.5.2")
        classpath("org.springframework.boot:spring-boot-gradle-plugin:2.6.6")
        classpath("org.sonarsource.scanner.gradle:sonarqube-gradle-plugin:2.6.2")
        classpath("com.adarshr:gradle-test-logger-plugin:1.6.0")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.20")
        classpath("org.jetbrains.kotlin:kotlin-allopen:1.7.20")
    }
}

allprojects {
    group = "lavalink"

    repositories {
        mavenCentral() // main maven repo
        mavenLocal()   // useful for developing
        maven("https://m2.dv8tion.net/releases")
        jcenter()
        maven("https://jitpack.io") // build projects directly from GitHub
    }
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "idea")

    if (project.hasProperty("includeAnalysis")) {
        project.logger.lifecycle("applying analysis plugins")
        apply(from = "../analysis.gradle")
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "11"
    }

    tasks.withType<JavaCompile> {
        options.encoding = "UTF-8"
        options.compilerArgs.add("-Xlint:unchecked")
        options.compilerArgs.add("-Xlint:deprecation")
    }
}
