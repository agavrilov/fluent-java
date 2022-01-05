package org.projectfluent.syntax.ast;

import kotlin.jvm.internal.DefaultConstructorMarker;

public final class TermReference extends Expression {
    // $FF: renamed from: id org.projectfluent.syntax.ast.Identifier

    private Identifier    field_1;

    private Identifier    attribute;

    private CallArguments arguments;

    public final Identifier getId() {
        return this.field_1;
    }

    public final void setId(Identifier var1) {
        Objects.requireNonNull(var1, "<set-?>");
        this.field_1 = var1;
    }

    public final Identifier getAttribute() {
        return this.attribute;
    }

    public final void setAttribute(Identifier var1) {
        this.attribute = var1;
    }

    public final CallArguments getArguments() {
        return this.arguments;
    }

    public final void setArguments(CallArguments var1) {
        this.arguments = var1;
    }

    public TermReference(Identifier id, Identifier attribute, CallArguments arguments) {
        Objects.requireNonNull(id, "id");
        super();
        this.field_1 = id;
        this.attribute = attribute;
        this.arguments = arguments;
    }

    // $FF: synthetic method
    public TermReference(Identifier var1, Identifier var2, CallArguments var3, int var4, DefaultConstructorMarker var5) {
        if ((var4 & 2) != 0) {
            var2 = null;
        }

        if ((var4 & 4) != 0) {
            var3 = null;
        }

        this(var1, var2, var3);
    }
}
