package org.projectfluent.syntax.ast;

import java.util.Objects;

public final class VariableReference extends Expression {
    // $FF: renamed from: id org.projectfluent.syntax.ast.Identifier

    private Identifier field_4;

    public final Identifier getId() {
        return this.field_4;
    }

    public final void setId(Identifier var1) {
        Objects.requireNonNull(var1, "<set-?>");
        this.field_4 = var1;
    }

    public VariableReference(Identifier id) {
        Objects.requireNonNull(id, "id");
        super();
        this.field_4 = id;
    }
}
