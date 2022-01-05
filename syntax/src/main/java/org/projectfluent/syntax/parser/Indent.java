package org.projectfluent.syntax.parser;

import org.projectfluent.syntax.ast.PatternElement;

final class Indent extends PatternElement {

    private String value;

    public final String getValue() {
        return this.value;
    }

    public final void setValue(String var1) {
        Objects.requireNonNull(var1, "<set-?>");
        this.value = var1;
    }

    public Indent(String value) {
        Objects.requireNonNull(value, "value");
        super();
        this.value = value;
    }

    public Indent(String value, int start, int end) {
        Objects.requireNonNull(value, "value");
        this(value);
        this.addSpan(start, end);
    }

    public final String component1() {
        return this.value;
    }

    public final Indent copy(String value) {
        Objects.requireNonNull(value, "value");
        return new Indent(value);
    }

    // $FF: synthetic method
    public static Indent copy$default(Indent var0, String var1, int var2, Object var3) {
        if ((var2 & 1) != 0) {
            var1 = var0.value;
        }

        return var0.copy(var1);
    }

    @Override
    public String toString() {
        return "Indent(value=" + this.value + ")";
    }

    @Override
    public int hashCode() {
        String var10000 = this.value;
        return var10000 != null ? var10000.hashCode() : 0;
    }

    @Override
    public boolean equals(Object var1) {
        if (this != var1) {
            if (var1 instanceof Indent) {
                Indent var2 = (Indent)var1;
                if (Intrinsics.areEqual(this.value, var2.value)) {
                    return true;
                }
            }

            return false;
        } else {
            return true;
        }
    }
}
