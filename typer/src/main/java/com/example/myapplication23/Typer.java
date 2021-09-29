package com.example.myapplication23;

import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
        mv = {1, 4, 3},
        bv = {1, 0, 3},
        k = 1,
        d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J(\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\b0\u000eJ6\u0010\u000f\u001a\u00020\b2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u000e\b\u0002\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\b0\u000eH\u0002J\u0006\u0010\u0014\u001a\u00020\bJ0\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\u00122\b\b\u0002\u0010\u000b\u001a\u00020\f2\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\b0\u000eR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0018"},
        d2 = {"Lcom/al/mond/typer/Typer;", "", "()V", "handler", "Landroid/os/Handler;", "loopRunnable", "Ljava/lang/Runnable;", "erasing", "", "textView", "Landroid/widget/TextView;", "useCursor", "", "endCallback", "Lkotlin/Function0;", "play", "playList", "", "", "callback", "stop", "typing", "target", "source", "Typer.typer"}
)
public final class Typer {
    private static final Handler handler;
    private static Runnable loopRunnable;
    @NotNull
    public static final Typer INSTANCE;

    public final void typing(@NotNull TextView target, @NotNull String source, boolean useCursor, @NotNull Function0 endCallback) {
        Intrinsics.checkNotNullParameter(target, "target");
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(endCallback, "endCallback");
        List playList = StringSlicer.INSTANCE.slice(source, useCursor);
        this.play(playList, target, useCursor, endCallback);
    }

    // $FF: synthetic method
    public static void typing$default(Typer var0, TextView var1, String var2, boolean var3, Function0 var4, int var5, Object var6) {
        if ((var5 & 4) != 0) {
            var3 = true;
        }

        if ((var5 & 8) != 0) {
            var4 = (Function0)null.INSTANCE;
        }

        var0.typing(var1, var2, var3, var4);
    }

    public final void stop() {
        handler.removeCallbacks(loopRunnable);
    }

    public final void erasing(@NotNull TextView textView, boolean useCursor, @NotNull Function0 endCallback) {
        Intrinsics.checkNotNullParameter(textView, "textView");
        Intrinsics.checkNotNullParameter(endCallback, "endCallback");
        List playList = StringSlicer.INSTANCE.slice(textView.getText().toString(), useCursor);
        playList.add(0, "");
        CollectionsKt.reverse(playList);
        this.play(playList, textView, useCursor, endCallback);
    }

    // $FF: synthetic method
    public static void erasing$default(Typer var0, TextView var1, boolean var2, Function0 var3, int var4, Object var5) {
        if ((var4 & 2) != 0) {
            var2 = true;
        }

        if ((var4 & 4) != 0) {
            var3 = (Function0)null.INSTANCE;
        }

        var0.erasing(var1, var2, var3);
    }

    private final void play(List playList, final TextView textView, final boolean useCursor, final Function0 callback) {
        final Iterator iterator = playList.iterator();
        handler.removeCallbacks(loopRunnable);
        loopRunnable = (Runnable)(new Runnable() {
            @Nullable
            private String tic;
            @Nullable
            private String toc;

            @Nullable
            public final String getTic() {
                return this.tic;
            }

            public final void setTic(@Nullable String var1) {
                this.tic = var1;
            }

            @Nullable
            public final String getToc() {
                return this.toc;
            }

            public final void setToc(@Nullable String var1) {
                this.toc = var1;
            }

            public void run() {
                long delayMillis = (long)(new Random()).nextInt(3) * 100L;
                if (iterator.hasNext()) {
                    textView.setText((CharSequence)iterator.next());
                    Typer.access$getHandler$p(Typer.INSTANCE).postDelayed((Runnable)this, delayMillis);
                    if (!iterator.hasNext()) {
                        callback.invoke();
                    }
                } else if (useCursor) {
                    this.cursorLoop();
                    Typer.access$getHandler$p(Typer.INSTANCE).postDelayed((Runnable)this, 500L);
                } else {
                    Typer.access$getHandler$p(Typer.INSTANCE).removeCallbacks(Typer.access$getLoopRunnable$p(Typer.INSTANCE));
                }

            }

            private final void cursorLoop() {
                this.setCursorTicToc();
                if (Intrinsics.areEqual(textView.getText().toString(), this.tic)) {
                    textView.setText((CharSequence)this.toc);
                } else {
                    textView.setText((CharSequence)this.tic);
                }

            }

            private final void setCursorTicToc() {
                if (this.tic == null) {
                    this.tic = textView.getText().toString();
                    String var10001 = this.tic;
                    Intrinsics.checkNotNull(var10001);
                    CharSequence var1 = (CharSequence)var10001;
                    boolean var2 = false;
                    if (var1.length() > 0) {
                        var10001 = this.tic;
                        Intrinsics.checkNotNull(var10001);
                        String var4 = var10001;
                        var10001 = this.tic;
                        Intrinsics.checkNotNull(var10001);
                        int var5 = StringsKt.getLastIndex((CharSequence)var10001);
                        String var10004 = this.tic;
                        Intrinsics.checkNotNull(var10004);
                        IntRange var6 = new IntRange(var5, StringsKt.getLastIndex((CharSequence)var10004));
                        boolean var3 = false;
                        if (var4 == null) {
                            throw new NullPointerException("null cannot be cast to non-null type kotlin.CharSequence");
                        }

                        var10001 = StringsKt.removeRange((CharSequence)var4, var6).toString();
                    } else {
                        var10001 = "|";
                    }

                    this.toc = var10001;
                }

            }
        });
        loopRunnable.run();
    }

    // $FF: synthetic method
    static void play$default(Typer var0, List var1, TextView var2, boolean var3, Function0 var4, int var5, Object var6) {
        if ((var5 & 8) != 0) {
            var4 = (Function0)null.INSTANCE;
        }

        var0.play(var1, var2, var3, var4);
    }

    private Typer() {
    }

    static {
        Typer var0 = new Typer();
        INSTANCE = var0;
        Looper var10002 = Looper.myLooper();
        Intrinsics.checkNotNull(var10002);
        handler = new Handler(var10002);
        loopRunnable = (Runnable)null.INSTANCE;
    }

    // $FF: synthetic method
    public static final Handler access$getHandler$p(Typer $this) {
        return handler;
    }

    // $FF: synthetic method
    public static final Runnable access$getLoopRunnable$p(Typer $this) {
        return loopRunnable;
    }

    // $FF: synthetic method
    public static final void access$setLoopRunnable$p(Typer $this, Runnable var1) {
        loopRunnable = var1;
    }
}
