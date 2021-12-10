#noinspection ShrinkerUnresolvedReference

# 这里的规则将会被包含进 AAR 包中
# 1. 可以存放该模块的混淆规则，防止使用方无法找到对应的方法
# 2. 可以存放影响该模块功能的第三方库的混淆规则，例如反射、序列化等

-keeppackagenames com.xzy.load.manager
-keepclasseswithmembers class com.xzy.load.manager.core.LoadManagerKt {
    com.xzy.load.manager.core.LoadService observe(android.view.View, androidx.lifecycle.LifecycleOwner, kotlin.jvm.functions.Function1);
}
-keep class com.xzy.load.manager.core.LoadManager { *; }
-keepclasseswithmembers class com.xzy.load.manager.core.LoadService {
    <init>(androidx.lifecycle.LifecycleOwner,com.xzy.load.manager.core.TargetContext,kotlin.jvm.functions.Function1);
    void notify(java.lang.Object);
    void showSuccess();
}
-keepclasseswithmembers class com.xzy.load.manager.core.LoadCallback {
    int onCreateView();
    boolean onReloadEvent();
}
-keepclasseswithmembers class * extends com.xzy.load.manager.core.LoadCallback {
    int onCreateView();
    boolean onReloadEvent();
}
-keep class com.xzy.load.manager.state.Empty { *; }
-keep class com.xzy.load.manager.state.Error { *; }
-keep class com.xzy.load.manager.state.Loading { *; }
-keep class com.xzy.load.manager.state.Success { *; }
-keep class com.xzy.load.manager.state.Timeout { *; }
-keep interface com.xzy.load.manager.inter.INetTimeout { *; }
-keep interface com.xzy.load.manager.inter.INetError { *; }
