package com.example.myapplication23;

import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;

@Metadata(
    mv = {1, 4, 3},
    bv = {1, 0, 3},
    k = 1,
    d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J$\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00072\u0006\u0010\b\u001a\u00020\tH\u0002J\u0010\u0010\n\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tH\u0002J\u001c\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tJ\u0016\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00050\u00072\u0006\u0010\u000e\u001a\u00020\u0005H\u0002¨\u0006\u000f"},
    d2 = {"Lcom/al/mond/typer/StringSlicer;", "", "()V", "assembleStepByStepFME", "", "", "splitedFME", "", "useCursor", "", "getCursor", "slice", "source", "splitFME", "input", "Typer.typer"}
)
public final class StringSlicer {
    @NotNull
    public static final StringSlicer INSTANCE;

    @NotNull
    public final List slice(@NotNull String source, boolean useCursor) {
        Intrinsics.checkNotNullParameter(source, "source");
        return this.assembleStepByStepFME(this.splitFME(source), useCursor);
    }

    private final List splitFME(String input) {
        boolean var3 = false;
        List result = (List)(new ArrayList());
        List inputs = StringsKt.split$default((CharSequence)input, new String[]{""}, false, 0, 6, (Object)null);
        Iterator var5 = inputs.iterator();

        while(true) {
            String var4;
            do {
                if (!var5.hasNext()) {
                    return result;
                }

                var4 = (String)var5.next();
            } while(Intrinsics.areEqual(var4, ""));

            String nfd = Normalizer.normalize((CharSequence)var4, Form.NFD);
            int i = 0;
            Intrinsics.checkNotNullExpressionValue(nfd, "nfd");

            for(int var8 = ((CharSequence)nfd).length(); i < var8; ++i) {
                boolean var10 = false;
                result.add(String.valueOf((char)nfd.codePointAt(i)));
            }
        }
    }

    private final List assembleStepByStepFME(List splitedFME, boolean useCursor) {
        boolean var4 = false;
        ArrayList result = new ArrayList();
        Iterable $this$forEach$iv = (Iterable)CollectionsKt.getIndices((Collection)splitedFME);
        int $i$f$forEach = false;

        String step;
        for(Iterator var6 = $this$forEach$iv.iterator(); var6.hasNext(); result.add(step + INSTANCE.getCursor(useCursor))) {
            int element$iv = ((IntIterator)var6).nextInt();
            int var9 = false;
            step = "";
            int j = 0;
            int var12 = element$iv;
            if (j <= element$iv) {
            while(true) {
                step = step + (String)splitedFME.get(j);
                if (j == var12) {
                    break;
                }

                ++j;
            }
        }
        }

        var4 = false;
        System.out.println(result);
        return (List)result;
    }

    private final String getCursor(boolean useCursor) {
        return useCursor ? "|" : "";
    }

    private StringSlicer() {
    }

    static {
        StringSlicer var0 = new StringSlicer();
        INSTANCE = var0;
    }
}
