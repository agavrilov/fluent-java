package org.projectfluent.syntax.ast;

import java.util.Objects;

public final class Attribute extends SyntaxNode {
    // $FF: renamed from: id org.projectfluent.syntax.ast.Identifier

    private Identifier field_0;

    private Pattern    value;

    public final Identifier getId() {
        return this.field_0;
    }

    public final void setId(Identifier var1) {
        Objects.requireNonNull(var1, "<set-?>");
        this.field_0 = var1;
    }

    public final Pattern getValue() {
        return this.value;
    }

    public final void setValue(Pattern var1) {
        Objects.requireNonNull(var1, "<set-?>");
        this.value = var1;
    }

    public Attribute(Identifier id, Pattern value) {
        super();
        Objects.requireNonNull(id, "id");
        Objects.requireNonNull(value, "value");
        this.field_0 = id;
        this.value = value;
    }
}
