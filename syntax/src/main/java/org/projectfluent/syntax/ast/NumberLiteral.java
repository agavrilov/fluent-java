package org.projectfluent.syntax.ast;

import java.util.Objects;

public final class NumberLiteral extends Literal implements VariantKey {

    public NumberLiteral(String value) {
        Objects.requireNonNull(value, "value");
        super(value);
    }
}
