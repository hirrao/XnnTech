// Add any additional repositories for your dependencies here.

repositories {
    maven {
        name = "Jitpack"
        url = uri("https://jitpack.io")
    }
    //NewHorizonsCoreMod依赖
    exclusiveContent {
        forRepository {
            maven {
                name = "glease"
                url = uri("https://maven.glease.net/repos/releases/")
            }
        }
        filter {
            includeGroup("net.glease")
        }
    }
}
