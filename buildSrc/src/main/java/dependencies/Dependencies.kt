package dependencies

import versions.Versions

object Dependencies {

    /** Android Jetpack **/
    const val ANDROIDX_CORE = "androidx.core:core-ktx:${Versions.CORE}"
    const val ANDROIDX_APPCOMPAT = "androidx.appcompat:appcompat:${Versions.APP_COMPAT}"
    const val NAVIGATION_UI = "androidx.navigation:navigation-ui-ktx:${Versions.NAVIGATION}"
    const val NAVIGATION_FRAGMENT = "androidx.navigation:navigation-fragment-ktx:${Versions.NAVIGATION}"
    const val ANDROIDX_CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:${Versions.CONSTRAINT_LAYOUT}"

    /** Networking Retfotit & OkHttp **/
    const val RETROFIT = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT}"
    const val RETROFIT_RX_JAVA = "com.squareup.retrofit2:adapter-rxjava3:${Versions.RETROFIT}"
    const val CONVERTER_GSON = "com.squareup.retrofit2:converter-gson:${Versions.GSON_CONVERTER}"
    const val OKHTTP = "com.squareup.okhttp3:okhttp:${Versions.OKHTTP}"
    const val OKHTTP_INTERCEPTOR = "com.squareup.okhttp3:logging-interceptor:${Versions.OKHTTP_INTERCEPTOR}"

    /** Dagger Hilt **/
    const val HILT = "com.google.dagger:hilt-android:${Versions.DAGGER}"
    const val HILT_COMPILER = "com.google.dagger:hilt-android-compiler:${Versions.DAGGER}"
    const val HILT_ANDROIDX_COMPILER = "androidx.hilt:hilt-compiler:${Versions.HILT}"

    /** Room Database **/
    const val ROOM = "androidx.room:room-ktx:${Versions.ROOM}"
    const val ROOM_RUNTIME = "androidx.room:room-runtime:${Versions.ROOM}"
    const val ROOM_COMPILER = "androidx.room:room-compiler:${Versions.ROOM}"
    const val ROOM_RX_JAVA = "androidx.room:room-rxjava3:${Versions.ROOM_RX}"
    const val ROOM_PAGING = "androidx.room:room-paging:${Versions.ROOM_PAGING}"

    /** UI Library **/
    const val MATERIAL = "com.google.android.material:material:${Versions.ANDROID_MATERIAL}"
    const val GLIDE = "com.github.bumptech.glide:glide:${Versions.GLIDE}"
    const val GLIDE_COMPILER = "com.github.bumptech.glide:compiler:${Versions.GLIDE}"
    const val SHIMMER = "com.facebook.shimmer:shimmer:${Versions.SHIMMER}"

    /** Paging **/
    const val PAGING_RUNTIME = "androidx.paging:paging-runtime-ktx:${Versions.PAGING}"
    const val PAGING_RX_JAVA = "androidx.paging:paging-rxjava3:${Versions.PAGING}"

    /** RxJava **/
    const val RX_ANDROID = "io.reactivex.rxjava3:rxandroid:${Versions.RX_ANDROID}"
    const val RX_JAVA = "io.reactivex.rxjava3:rxjava:${Versions.RX_JAVA}"
    const val RX_BINDING = "com.jakewharton.rxbinding3:rxbinding:${Versions.RX_BINDING}"

    /** Testing **/
    const val ANDROIDX_JUNIT = "androidx.test.ext:junit:${Versions.ANDROIDX_JUNIT}"
    const val ESPRESSO = "androidx.test.espresso:espresso-core:${Versions.ANDROID_TEST_ESPRESSO}"
    const val JUNIT = "junit:junit:${Versions.TEST_JUNIT}"
}