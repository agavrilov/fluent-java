package org.projectfluent.syntax.ast;

import java.util.Objects;

import kotlin.jvm.internal.DefaultConstructorMarker;

public final class MessageReference extends Expression {
    // $FF: renamed from: id org.projectfluent.syntax.ast.Identifier

    private Identifier field_3;

    private Identifier attribute;

    public final Identifier getId() {
        return this.field_3;
    }

    public final void setId(Identifier var1) {
        Objects.requireNonNull(var1, "<set-?>");
        this.field_3 = var1;
    }

    public final Identifier getAttribute() {
        return this.attribute;
    }

    public final void setAttribute(Identifier var1) {
        this.attribute = var1;
    }

    public MessageReference(Identifier id, Identifier attribute) {
        Objects.requireNonNull(id, "id");
        super();
        this.field_3 = id;
        this.attribute = attribute;
    }

    // $FF: synthetic method
    public MessageReference(Identifier var1, Identifier var2, int var3, DefaultConstructorMarker var4) {
        if ((var3 & 2) != 0) {
            var2 = null;
        }

        this(var1, var2);
    }
}
