package org.projectfluent.syntax.ast;

public abstract class Literal extends Expression {

    private final String value;

    public final String getValue() {
        return this.value;
    }

    public Literal(String value) {
        Objects.requireNonNull(value, "value");
        super();
        this.value = value;
    }
}
