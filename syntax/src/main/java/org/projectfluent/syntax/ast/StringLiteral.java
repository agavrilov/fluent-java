package org.projectfluent.syntax.ast;

import java.util.Objects;

public final class StringLiteral extends Literal {

    public StringLiteral(String value) {
        Objects.requireNonNull(value, "value");
        super(value);
    }
}
