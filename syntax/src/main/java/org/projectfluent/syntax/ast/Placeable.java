package org.projectfluent.syntax.ast;

import java.util.Objects;

public final class Placeable extends PatternElement implements InsidePlaceable {

    private InsidePlaceable expression;

    public final InsidePlaceable getExpression() {
        return this.expression;
    }

    public final void setExpression(InsidePlaceable var1) {
        Objects.requireNonNull(var1, "<set-?>");
        this.expression = var1;
    }

    public Placeable(InsidePlaceable expression) {
        Objects.requireNonNull(expression, "expression");
        super();
        this.expression = expression;
    }
}
