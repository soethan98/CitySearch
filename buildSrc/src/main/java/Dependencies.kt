import org.gradle.api.artifacts.dsl.RepositoryHandler
import java.net.URI


object  DaggerHilt{
    private const val hilt = "2.38.1"
    const val hiltAndroid = "com.google.dagger:hilt-android:$hilt"
    const val hiltCompiler = "com.google.dagger:hilt-compiler:$hilt"
    const val hiltGradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:$hilt"
}

object Material {
    const val material = "com.google.android.material:material:1.6.0"
}

object OkHttp {

    private const val version = "4.9.0"

    const val client = "com.squareup.okhttp3:okhttp:$version"
    const val logger = "com.squareup.okhttp3:logging-interceptor:$version"
}

object Retrofit {

    private const val version = "2.9.0"

    const val core = "com.squareup.retrofit2:retrofit:$version"
    const val gson_converter = "com.squareup.retrofit2:converter-gson:$version"

}

object Gson {
    private const val version = "2.9.0"

    const val core = "com.google.code.gson:gson:$version"
}


object AndroidArchLifeCycle {
    private const val lifecycle_version = "2.5.0-rc01"
    const val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version"
    const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version"
    const val compiler = "androidx.lifecycle:lifecycle-compiler:$lifecycle_version"
}

object AndroidArchNavigation {
    private const val nav_version = "2.4.2"
    const val fragment = "androidx.navigation:navigation-fragment-ktx:$nav_version"
    const val ui = "androidx.navigation:navigation-ui-ktx:$nav_version"
    const val gradle_plugin = "androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version"
}

object AndroidX {
    const val core = "androidx.core:core-ktx:1.7.0"
    const val appcompat = "androidx.appcompat:appcompat:1.4.1"
    const val constraint_layout = "androidx.constraintlayout:constraintlayout:2.1.3"
    const val recycler_view = "androidx.recyclerview:recyclerview:1.1.0"
}

object Coroutines {
    private const val coroutines = "1.4.0"

    const val coroutine_core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines"
    const val coroutine_android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines"
}

object AndroidRoom{
    //room
    private const val version= "2.3.0"
    const val room_ktx =  "androidx.room:room-ktx:$version"
    const val room_compiler = "androidx.room:room-compiler:$version"
}

object Paging3{
    private const val  version= "3.1.1"
    const val paging_runtime = "androidx.paging:paging-runtime:$version"
}



object Repo {
    @JvmStatic
    fun addRepos(handler: RepositoryHandler) {
        handler.google()
        handler.jcenter()
        handler.maven { url = URI.create("https://jitpack.io") }
    }
}

