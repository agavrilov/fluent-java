package org.projectfluent.syntax.ast;

import java.util.Objects;

public final class Identifier extends SyntaxNode implements VariantKey {

    private String name;

    public final String getName() {
        return this.name;
    }

    public final void setName(String var1) {
        Objects.requireNonNull(var1, "<set-?>");
        this.name = var1;
    }

    public Identifier(String name) {
        Objects.requireNonNull(name, "name");
        super();
        this.name = name;
    }
}
