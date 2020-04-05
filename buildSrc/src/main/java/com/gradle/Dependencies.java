package com.gradle;

public class Dependencies {

    /**
     * Version for libraries
     **/

    private static final String LIFECYLE_VERSION = "2.0.0";
    private static final String DAGGER_VERSION = "2.20";
    private static final String BUTTER_KNIFE_VERSION = "10.1.0";
    private static final String RX_JAVA_VERSION = "2.1.2";
    private static final String RX_ANDROID_VERSION = "2.1.1";
    private static final String RECYCLERVIEW_VERSION = "1.1.0";
    private static final String RETROFIT_VERSION = "2.5.0";
    private static final String OKHTTP_VERSION = "3.14.2";
    private static final String RETROFIT_RX_ADAPTER_VERSION = "1.0.0";
    private static final String CARDVIEW_VERSION = "1.0.0";
    private static final String GSON_VERSION = "2.8.2";
    private static final String GLIDE_VERSION = "4.11.0";
    private static final String AUTO_VALUE_VERSION = "1.6.2";
    private static final String ROOM_VERSION = "2.2.5";
    private static final String SEARCHVIEW_VERSION = "1.4.0";
    private static final String COORDINATOR_VERSION = "1.1.0";
    private static final String METERIAL_DESIGN_VERSION = "1.0.0";

    /**
     * Libraries
     */


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
    public static final String RX_JAVA_ANDROID =
            "io.reactivex.rxjava2:rxandroid:" + RX_ANDROID_VERSION;

    public static final String RECYCLERVIEW = "androidx.recyclerview:recyclerview:" + RECYCLERVIEW_VERSION;
    public static final String RETORIFT = "com.squareup.retrofit2:retrofit:" + RETROFIT_VERSION;
    public static final String RETROFIT_CONVERTER =
            "com.squareup.retrofit2:converter-gson:" + RETROFIT_VERSION;

    public static final String OKHTTP = "com.squareup.okhttp3:logging-interceptor:" + OKHTTP_VERSION;
    public static final String RETROFIT_RX_JAVA_ADAPTER =
            "com.jakewharton.retrofit:retrofit2-rxjava2-adapter:" + RETROFIT_RX_ADAPTER_VERSION;
    public static final String CARDVIEW = "androidx.cardview:cardview:" + CARDVIEW_VERSION;

    public static final String GSON = "com.google.code.gson:gson:" + GSON_VERSION;
    public static final String GLIDE = "com.github.bumptech.glide:glide:" + GLIDE_VERSION;
    public static final String GLIDE_COMPILER = "com.github.bumptech.glide:compiler:" + GLIDE_VERSION;

    public static final String AUTO_VALUE =
            "com.google.auto.value:auto-value-annotations:" + AUTO_VALUE_VERSION;
    public static final String AUTO_VALUE_ANNOTATION =
            "com.google.auto.value:auto-value:" + AUTO_VALUE_VERSION;

    public static final String AUTO_VALUE_ANNOTATION_PARSEL =
            "com.ryanharter.auto.value:auto-value-parcel:0.2.5";
    public static final String AUTOVALUE_WITH_COMPILER =
            "com.gabrielittner.auto.value:auto-value-with:1.0.0";

    public static final String ROOM_DATABASE =
            "android.arch.persistence.room:runtime:" + ROOM_VERSION;
    public static final String ROOM_DATABASE_ANOTAION_PROCESSOR =
            "android.arch.persistence.room:compiler:" + ROOM_VERSION;

    public static final String ROOM_RX_JAVA = "androidx.room:room-rxjava2:" + ROOM_VERSION;

    public static final String METERIAL_SEARCHVIEW = "com.miguelcatalan:materialsearchview:" + SEARCHVIEW_VERSION;

    public static final String COORDINATOR_LAYOUT_VERSION = "androidx.coordinatorlayout:coordinatorlayout:" +
            COORDINATOR_VERSION;

    public static final String METERIAL_DESIGN = "com.google.android.material:material:" + METERIAL_DESIGN_VERSION;


}
