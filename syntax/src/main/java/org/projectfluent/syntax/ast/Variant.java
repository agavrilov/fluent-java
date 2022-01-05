package org.projectfluent.syntax.ast;

import java.util.Objects;

public final class Variant extends SyntaxNode {

    private VariantKey key;

    private Pattern    value;

    // $FF: renamed from: default boolean
    private boolean    field_7;

    public final VariantKey getKey() {
        return this.key;
    }

    public final void setKey(VariantKey var1) {
        Objects.requireNonNull(var1, "<set-?>");
        this.key = var1;
    }

    public final Pattern getValue() {
        return this.value;
    }

    public final void setValue(Pattern var1) {
        Objects.requireNonNull(var1, "<set-?>");
        this.value = var1;
    }

    public final boolean getDefault() {
        return this.field_7;
    }

    public final void setDefault(boolean var1) {
        this.field_7 = var1;
    }

    public Variant(VariantKey key, Pattern value, boolean var3) {
        super();
        Objects.requireNonNull(key, "key");
        Objects.requireNonNull(value, "value");
        this.key = key;
        this.value = value;
        this.field_7 = var3;
    }
}
