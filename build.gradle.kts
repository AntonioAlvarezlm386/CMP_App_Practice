plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    //plugins de gradle que le dan a gradle las funcionalidades para manejar la compilacion de nuestro proyecto
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.composeMultiplatform) apply false
    alias(libs.plugins.composeCompiler) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.kotlinsSerialization) apply false
}