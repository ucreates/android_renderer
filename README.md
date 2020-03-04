# Renderer(Android)
The project is renderer for [Android](https://www.android.com).

## Description
The project is develoed by Android Studio(3.4).

OpenGLES(1.x).

Android renderer supported Android6.0 Later.

## Usage
***Notes on use for Android***

1.Add to your build.gradle(app).

- Java

```
・
・
・
repositories {
    maven {
        url 'https://github.com/ucreates/android_renderer/raw/master/repository/java/renderer/gles1'
    }
}
・
・
・
dependencies {
    compile 'com.ucreates:renderer:1.0.0' 
}    
```

2.Sync gradle in your Android project.

3.Build and Running Android Renderer on your Android Client App.
