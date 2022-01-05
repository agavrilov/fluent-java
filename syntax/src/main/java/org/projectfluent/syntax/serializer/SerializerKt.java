package org.projectfluent.syntax.serializer;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.projectfluent.syntax.ast.Pattern;
import org.projectfluent.syntax.ast.PatternElement;
import org.projectfluent.syntax.ast.Placeable;
import org.projectfluent.syntax.ast.SelectExpression;
import org.projectfluent.syntax.ast.TextElement;

import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.text.StringsKt;

public final class SerializerKt {

    private static final String indentExceptFirstLine(CharSequence content) {
        return CollectionsKt.joinToString$default((Iterable)StringsKt.split$default(content, new String[] {"\n"}, false, 0, 6, (Object)null),
                (CharSequence)"\n    ",
                (CharSequence)null,
                (CharSequence)null,
                0,
                (CharSequence)null,
                (Function1)null,
                62,
                (Object)null);
    }

    private static final boolean includesLine(PatternElement $this$includesLine) {
        return $this$includesLine instanceof TextElement &&
               StringsKt.contains$default((CharSequence)((TextElement)$this$includesLine).getValue(), (CharSequence)"\n", false, 2, (Object)null);
    }

    private static final boolean isSelectExpr(PatternElement $this$isSelectExpr) {
        return $this$isSelectExpr instanceof Placeable && ((Placeable)$this$isSelectExpr).getExpression() instanceof SelectExpression;
    }

    private static final boolean shouldStartOnNewLine(Pattern $this$shouldStartOnNewLine) {
        Iterable $this$any$iv = $this$shouldStartOnNewLine.getElements();
        int $i$f$any = false;
        boolean var10000;
        if ($this$any$iv instanceof Collection && ((Collection)$this$any$iv).isEmpty()) {
            var10000 = false;
        } else {
            Iterator var4 = $this$any$iv.iterator();

            while (true) {
                if (!var4.hasNext()) {
                    var10000 = false;
                    break;
                }

                Object element$iv = var4.next();
                PatternElement it = (PatternElement)element$iv;
                int var7 = false;
                if (isSelectExpr(it) || includesLine(it)) {
                    var10000 = true;
                    break;
                }
            }
        }

        boolean isMultiline = var10000;
        if (!isMultiline) {
            return false;
        } else {
            List var9 = $this$shouldStartOnNewLine.getElements();
            byte var11 = 0;
            boolean var13 = false;
            PatternElement firstElement = (PatternElement)CollectionsKt.getOrNull(var9, var11);
            if (firstElement instanceof TextElement) {
                CharSequence var12 = ((TextElement)firstElement).getValue();
                byte var14 = 0;
                boolean var15 = false;
                Character firstChar = StringsKt.getOrNull(var12, var14);
                if (firstChar != null) {
                    if (firstChar == '[') {
                        return false;
                    }
                }

                if (firstChar != null) {
                    if (firstChar == '.') {
                        return false;
                    }
                }

                if (firstChar != null) {
                    if (firstChar == '*') {
                        return false;
                    }
                }
            }

            return true;
        }
    }

    // $FF: synthetic method
    public static final String access$indentExceptFirstLine(CharSequence content) {
        return indentExceptFirstLine(content);
    }

    // $FF: synthetic method
    public static final boolean access$shouldStartOnNewLine(Pattern $this$access_u24shouldStartOnNewLine) {
        return shouldStartOnNewLine($this$access_u24shouldStartOnNewLine);
    }
}
