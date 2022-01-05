package org.projectfluent.syntax.ast;

public final class StringLiteral extends Literal {

    public StringLiteral(String value) {
        Objects.requireNonNull(value, "value");
        super(value);
    }
}
