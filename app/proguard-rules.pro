-ignorewarnings
-renamesourcefileattribute SourceFile
-keepattributes SourceFile,LineNumberTable,*Annotation*,Signature

-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.app.Fragment
-keep public class * extends android.support.v4.app.Fragment
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends com.google.inject.AbstractModule
-keep public class com.android.vending.licensing.ILicensingService
-keep public class rejasupotaro.rebuild.tools.MainThreadExecutor
-keep public class rejasupotaro.rebuild.tools.OnContextExecutor

-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-dontpreverify
-verbose

-keep class com.google.inject.** { *; }
-keep class javax.inject.** { *; }
-keep class javax.annotation.** { *; }
-keep class roboguice.** { *; }


-keep class android.support.v4.** { *; }
-dontwarn android.support.v4.**
-keep class android.support.v7.** { *; }
-dontwarn android.support.v7.**

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
-keepclassmembers class fqcn.of.javascript.interface.for.webview {
   public *;
}

-keep class * implements rejasupotaro.rebuild.Injectable

-dontoptimize

-keepclasseswithmembers class * { native <methods>; }

-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}


-keepclasseswithmembers class * {
    public <init> (android.content.Context, android.util.AttributeSet);
}
-keepclasseswithmembers class * {
    public <init> (android.content.Context, android.util.AttributeSet, int);
}

-keepclassmembers class **.R$* { public static <fields>; }
-keepclassmembers class * {
	@com.google.inject.Inject <init>(...);
	@com.google.inject.Inject <fields>;
	@javax.annotation.Nullable <fields>;
}
-keepclassmembers class * {
	void *(net.eworldui.videouploader.events.*);
}
-keepclassmembers class * {
	void *(roboguice.activity.event.*);
}
-keepclassmembers class * extends android.webkit.WebChromeClient {
    *;
}
-dontwarn com.google.ads.**

-keepclassmembers class ** {
    @com.squareup.otto.Subscribe public *;
    @com.squareup.otto.Produce public *;
}

# activeandroid
-keep class com.activeandroid.** { *; }
-keep class com.activeandroid.**.** { *; }
-keep class * extends com.activeandroid.Model
-keep class * extends com.activeandroid.serializer.TypeSerializer

-keepattributes Column
-keepattributes Table
-keepclasseswithmembers class * { @com.activeandroid.annotation.Column <fields>; }

# gson (library for Json by Google)
-keep class com.google.gson.** { *; }
-keep class com.google.gson.stream.** { *; }
-keep class sun.misc.Unsafe { *; }
-keepattributes Expose
-keepattributes SerializedName
-keepattributes Since
-keepattributes Until
-keepclasseswithmembers class * { @com.google.gson.annotations.Expose <fields>; }

# 艹,由于gson解析需要,必须keeping所有序列化和反序列化的model
-keep class cn.timeface.api.models.** { *; }


# Parcelable
-keep class * implements android.os.Parcelable {
    public static android.os.Parcelable$Creator *;
}

# twitter4j
-keepclasseswithmembers  class twitter4j.** {
    *;
}
-dontwarn twitter4j.management.**
-dontwarn twitter4j.TwitterAPIMonitor
-dontwarn twitter4j.internal.**
-dontwarn twitter4j.Annotation

# Butterknife
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }

-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}

-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

# OkHttp
-keep class com.squareup.okhttp.** { *; }
-keep interface com.squareup.okhttp.** { *; }
-dontwarn com.squareup.okhttp.**

# Okio oddities
-keepnames class okio.** { *; }
-keepnames interface okio.** { *; }
-dontwarn java.nio.file.*

# Retrofit
-keep class com.squareup.okhttp.** { *; }
-keep interface com.squareup.okhttp.** { *; }
-dontwarn com.squareup.okhttp.**

-dontwarn retrofit.**
-keep class retrofit.** { *; }
-keepclasseswithmembers class * {
    @retrofit.http.* <methods>;
}

#gradle-retrolambda
-dontwarn java.lang.invoke.*

# RxJava
-dontwarn rx.**
-keep class rx.schedulers.Schedulers {
    public static <methods>;
}
-keep class rx.schedulers.ImmediateScheduler {
    public <methods>;
}
-keep class rx.schedulers.TestScheduler {
    public <methods>;
}
-keep class rx.schedulers.Schedulers {
    public static ** test();
}
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
     long producerIndex;
     long consumerIndex;
 }
 -keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
     long producerNode;
     long consumerNode;
 }

# LoganSquare
-keep class com.bluelinelabs.logansquare.** { *; }
-keep @com.bluelinelabs.logansquare.annotation.JsonObject class *
-keep class **$$JsonObjectMapper { *; }

## ----------------------------------
##      sharesdk
## ----------------------------------
-keep class cn.sharesdk.**{*;}
-keep class com.sina.**{*;}
-keep class com.mob.**{*;}
-keep class **.R$* {*;}
-keep class **.R{*;}
-dontwarn cn.sharesdk.**
-dontwarn com.mob.**
-dontwarn **.R$*


# # -------------------------------------------
# #  ############### volley混淆  ###############
# # -------------------------------------------
-keep class com.android.volley.** {*;}
-keep class com.android.volley.toolbox.** {*;}
-keep class com.android.volley.Response$* { *; }
-keep class com.android.volley.Request$* { *; }
-keep class com.android.volley.RequestQueue$* { *; }
-keep class com.android.volley.toolbox.HurlStack$* { *; }
-keep class com.android.volley.toolbox.ImageLoader$* { *; }


## ----------------------------------
##      pinyin4j
## ----------------------------------
-keep class net.sourceforge.pinyin4j.** { *;}
-keep class demo.** { *;}

## ----------------------------------
##      slidingmenu
## ----------------------------------
-dontwarn com.jeremyfeinstein.slidingmenu.lib.**
-keep class com.jeremyfeinstein.slidingmenu.lib.**{*;}


## ----------------------------------
##      EventBus
## ----------------------------------
-keepclassmembers class ** {
    public void onEvent*(**);
}

# AndroidAsync
-keepnames class com.koushikdutta.async.** { *; }
-keepnames interface com.koushikdutta.async.** { *; }

# Ion
-keepnames class com.koushikdutta.ion.** { *; }
-keepnames interface com.koushikdutta.ion.** { *; }

# easing func
-dontwarn com.daimajia.easing.**
-keep class com.daimajia.easing.**{*;}

#Glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
    **[] $VALUES;
    public *;
}
-dontwarn com.bumptech.glide.**
-keepnames class com.bumptech.glide.Glide
-keep class cn.timeface.utils.glide.TFGlideModule
# LeakCanary
-keep class org.eclipse.mat.** { *; }
-keep class com.squareup.leakcanary.** { *; }

# MiPush
-keep class cn.timeface.managers.receivers.MiPushMessageReceiver {*;}

#mp4 merge
-keep class com.coremedia.iso.** { *; }
-keep class com.googlecode.mp4parser.** { *; }

-keep class com.jpardogo.android.googleprogressbar.** { *; }