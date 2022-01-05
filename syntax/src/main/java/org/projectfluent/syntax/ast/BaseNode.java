package org.projectfluent.syntax.ast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.reflect.KProperty1;
import kotlin.reflect.KVisibility;

public abstract class BaseNode {

    /** @deprecated */
    @Deprecated
    public static final BaseNode.Companion Companion = new BaseNode.Companion((DefaultConstructorMarker)null);

    @Override
    public boolean equals(Object other) {
        return other instanceof BaseNode ? this.equals((BaseNode)other, Collections.emptySet()) : false;
    }

    public final boolean equals(BaseNode other, Set<String> ignoredFields) {
        Objects.requireNonNull(other, "other");
        Objects.requireNonNull(ignoredFields, "ignoredFields");
        boolean var10000;
        if (this.getClass().equals(other.getClass())) {
            Iterable $this$all$iv = publicMemberProperties(this.getClass(), ignoredFields);
            int $i$f$all = false;
            if ($this$all$iv instanceof Collection && ((Collection)$this$all$iv).isEmpty()) {
                var10000 = true;
            } else {
                Iterator var5 = $this$all$iv.iterator();

                while (true) {
                    if (!var5.hasNext()) {
                        var10000 = true;
                        break;
                    }

                    Object element$iv = var5.next();
                    KProperty1 it = (KProperty1)element$iv;
                    int var8 = false;
                    Object thisValue = it.getGetter().call(new Object[] {this});
                    Object otherValue = it.getGetter().call(new Object[] {other});
                    if (thisValue instanceof Collection && otherValue instanceof Collection) {
                        if (((Collection)thisValue).size() == ((Collection)otherValue).size()) {
                            Iterable $this$all$iv = (Iterable)CollectionsKt.zip((Iterable)thisValue, (Iterable)otherValue);
                            int $i$f$all = false;
                            if ($this$all$iv instanceof Collection && ((Collection)$this$all$iv).isEmpty()) {
                                var10000 = true;
                            } else {
                                Iterator var13 = $this$all$iv.iterator();

                                while (true) {
                                    if (!var13.hasNext()) {
                                        var10000 = true;
                                        break;
                                    }

                                    Object element$iv = var13.next();
                                    Pair $dstr$a$b = (Pair)element$iv;
                                    int var16 = false;
                                    Object a = $dstr$a$b.component1();
                                    Object b = $dstr$a$b.component2();
                                    if (!Companion.scalarsEqual(a, b, ignoredFields)) {
                                        var10000 = false;
                                        break;
                                    }
                                }
                            }
                        } else {
                            var10000 = false;
                        }
                    } else {
                        var10000 = Companion.scalarsEqual(thisValue, otherValue, ignoredFields);
                    }

                    if (!var10000) {
                        var10000 = false;
                        break;
                    }
                }
            }
        } else {
            var10000 = false;
        }

        return var10000;
    }

    private static List publicMemberProperties(Class<?> clazz, Set<String> ignoredFields) {
        Iterable $this$filterNot$iv = clazz.getMemberProperties(clazz);
        boolean $i$f$filterNot = false;
        Collection destination$iv$iv = (new ArrayList());
        boolean $i$f$filterNotTo = false;
        Iterator var8 = $this$filterNot$iv.iterator();

        Object element$iv$iv;
        KProperty1 it;
        boolean var11;
        while (var8.hasNext()) {
            element$iv$iv = var8.next();
            it = (KProperty1)element$iv$iv;
            var11 = false;
            if (it.getVisibility() == KVisibility.PUBLIC) {
                destination$iv$iv.add(element$iv$iv);
            }
        }

        $this$filterNot$iv = (destination$iv$iv);
        $i$f$filterNot = false;
        destination$iv$iv = (new ArrayList());
        $i$f$filterNotTo = false;
        var8 = $this$filterNot$iv.iterator();

        while (var8.hasNext()) {
            element$iv$iv = var8.next();
            it = (KProperty1)element$iv$iv;
            var11 = false;
            if (!ignoredFields.contains(it.getName())) {
                destination$iv$iv.add(element$iv$iv);
            }
        }

        return (List)destination$iv$iv;
    }

    private static boolean scalarsEqual(Object left, Object right, Set ignoredFields) {
        return left instanceof BaseNode && right instanceof BaseNode ? ((BaseNode)left).equals((BaseNode)right, ignoredFields)
                : Objects.equals(left, right);
    }
}
