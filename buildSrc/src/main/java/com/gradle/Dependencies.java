package com.gradle;

public class Dependencies {


    private static final String LIFECYLE_VERSION = "2.0.0";
    private static final String DAGGER_VERSION = "2.20";
    private static final String BUTTER_KNIFE_VERSION = "10.1.0";
    private static final String RX_JAVA_VERSION = "2.1.2";


    public static final String ANDROID_X_LIFECYCLE =
            "androidx.lifecycle:lifecycle-extensions:" + LIFECYLE_VERSION;

    public static final String DAGGER = "com.google.dagger:dagger-android:" + DAGGER_VERSION;
    public static final String DAGGER_SUPPORT =
            "com.google.dagger:dagger-android-support:" + DAGGER_VERSION;
    public static final String DAGGER_ANNOTATION_PROCESSOR =
            "com.google.dagger:dagger-android-processor:" + DAGGER_VERSION;
    public static final String DAGGER_COMPILER =
            "com.google.dagger:dagger-compiler:" + DAGGER_VERSION;
    public static final String BUTTER_KNIFE = "com.jakewharton:butterknife:" + BUTTER_KNIFE_VERSION;
    public static final String BUTTER_KNIFE_ANNOTATION =
            "com.jakewharton:butterknife-compiler:" + BUTTER_KNIFE_VERSION;

    public static final String RX_JAVA = "io.reactivex.rxjava2:rxjava:" + RX_JAVA_VERSION;
    private static final String RX_ANDROID_VERSION = "2.1.1";
    public static final String RX_JAVA_ANDROID =
            "io.reactivex.rxjava2:rxandroid:" + RX_ANDROID_VERSION;


}
