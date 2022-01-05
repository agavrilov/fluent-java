package org.projectfluent.syntax.ast;

public final class NumberLiteral extends Literal implements VariantKey {

    public NumberLiteral(String value) {
        Objects.requireNonNull(value, "value");
        super(value);
    }
}
