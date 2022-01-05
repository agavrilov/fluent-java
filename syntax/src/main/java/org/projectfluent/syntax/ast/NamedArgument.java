package org.projectfluent.syntax.ast;

import java.util.Objects;

public final class NamedArgument extends SyntaxNode implements CallArgument {

    private Identifier name;

    private Literal    value;

    public final Identifier getName() {
        return this.name;
    }

    public final void setName(Identifier var1) {
        Objects.requireNonNull(var1, "<set-?>");
        this.name = var1;
    }

    public final Literal getValue() {
        return this.value;
    }

    public final void setValue(Literal var1) {
        Objects.requireNonNull(var1, "<set-?>");
        this.value = var1;
    }

    public NamedArgument(Identifier name, Literal value) {
        Objects.requireNonNull(name, "name");
        Objects.requireNonNull(value, "value");
        super();
        this.name = name;
        this.value = value;
    }
}
