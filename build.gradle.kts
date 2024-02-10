// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript{


    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath ("com.android.tools.build:gradle:8.1.3")
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin: 1.9.0")
        classpath ("androidx.navigation:navigation-safe-args-gradle-plugin:2.7.6")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}