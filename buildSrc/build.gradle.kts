repositories {
    google()
    mavenCentral()
}
plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(gradleApi())
    implementation("org.ow2.asm:asm:9.3")
    implementation("org.ow2.asm:asm-commons:9.3")
    implementation("com.android.tools.build:gradle:7.2.1")
    implementation("commons-io:commons-io:2.11.0")
    implementation("commons-codec:commons-codec:1.15")
}
gradlePlugin {
    plugins {
        create("LottieFix") {
            id = "com.ly.lottieFix"
            implementationClass = "com.ly.lottiefix.LottieFixPlugin"
        }
    }
}