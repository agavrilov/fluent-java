package org.projectfluent.syntax.visitor;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.sound.midi.Sequence;

import org.projectfluent.syntax.ast.BaseNode;

import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;

public abstract class Visitor {

    private final Map handlers;

    public final void visit(BaseNode node) {
        Objects.requireNonNull(node, "node");
        String cName = node.getClass().getSimpleName();
        Method handler = (Method)this.handlers.get(cName);
        if (handler != null) {
            handler.invoke(this, node);
        } else {
            this.genericVisit(node);
        }

    }

    public final void genericVisit(BaseNode node) {
        Objects.requireNonNull(node, "node");
        Sequence $this$forEach$iv = SequencesKt.map(VisitorKt.childrenOf(node), (Function1)null.INSTANCE);
        int $i$f$forEach = false;
        Iterator var4 = $this$forEach$iv.iterator();

        while (var4.hasNext()) {
            Object element$iv = var4.next();
            int var7 = false;
            if (element$iv instanceof BaseNode) {
                this.visit((BaseNode)element$iv);
            } else if (element$iv instanceof Collection) {
                Iterable $this$map$iv = (Iterable)element$iv;
                int $i$f$map = false;
                Collection destination$iv$iv = (new ArrayList());
                int $i$f$mapTo = false;
                Iterator var14 = $this$map$iv.iterator();

                Object item$iv$iv;
                while (var14.hasNext()) {
                    item$iv$iv = var14.next();
                    if (item$iv$iv instanceof BaseNode) {
                        destination$iv$iv.add(item$iv$iv);
                    }
                }

                $this$map$iv = ((List)destination$iv$iv);
                $i$f$map = false;
                destination$iv$iv = (new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10)));
                $i$f$mapTo = false;
                var14 = $this$map$iv.iterator();

                while (var14.hasNext()) {
                    item$iv$iv = var14.next();
                    BaseNode it = (BaseNode)item$iv$iv;
                    int var18 = false;
                    this.visit(it);
                    Unit var19 = Unit.INSTANCE;
                    destination$iv$iv.add(var19);
                }

                List var10000 = (List)destination$iv$iv;
            }
        }

    }

    public Visitor() {
        boolean var1 = false;
        Map var16 = (new LinkedHashMap());
        this.handlers = var16;
        Method[] var10000 = this.getClass().getDeclaredMethods();
        Intrinsics.checkExpressionValueIsNotNull(var10000, "this::class.java.declaredMethods");
        Object[] $this$filter$iv = var10000;
        int $i$f$map = false;
        Collection destination$iv$iv = (new ArrayList());
        int $i$f$mapTo = false;
        Method[] var6 = $this$filter$iv;
        int var7 = $this$filter$iv.length;

        String var24;
        for (int var8 = 0; var8 < var7; ++var8) {
            Object element$iv$iv = var6[var8];
            int var11 = false;
            Intrinsics.checkExpressionValueIsNotNull(element$iv$iv, "it");
            var24 = element$iv$iv.getName();
            Intrinsics.checkExpressionValueIsNotNull(var24, "it.name");
            if (StringsKt.startsWith$default(var24, "visit", false, 2, (Object)null)) {
                destination$iv$iv.add(element$iv$iv);
            }
        }

        Iterable $this$map$iv = ((List)destination$iv$iv);
        $i$f$map = false;
        destination$iv$iv = (new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10)));
        $i$f$mapTo = false;
        Iterator var19 = $this$map$iv.iterator();

        while (var19.hasNext()) {
            Object item$iv$iv = var19.next();
            Method it = (Method)item$iv$iv;
            int var22 = false;
            Map var26 = this.handlers;
            Intrinsics.checkExpressionValueIsNotNull(it, "it");
            String var10001 = it.getName();
            Intrinsics.checkExpressionValueIsNotNull(var10001, "it.name");
            String var10 = var10001;
            int var23 = "visit".length();
            Map var12 = var26;
            boolean var13 = false;
            if (var10 == null) {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }

            var24 = var10.substring(var23);
            Intrinsics.checkExpressionValueIsNotNull(var24, "(this as java.lang.String).substring(startIndex)");
            String var14 = var24;
            var12.put(var14, it);
            Unit var25 = Unit.INSTANCE;
            destination$iv$iv.add(var25);
        }

        List var27 = (List)destination$iv$iv;
    }
}
