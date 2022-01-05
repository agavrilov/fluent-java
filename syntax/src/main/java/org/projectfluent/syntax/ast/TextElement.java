package org.projectfluent.syntax.ast;

import java.util.Objects;

public final class TextElement extends PatternElement {

    private String value;

    public final String getValue() {
        return this.value;
    }

    public final void setValue(String var1) {
        Objects.requireNonNull(var1, "<set-?>");
        this.value = var1;
    }

    public TextElement(String value) {
        super();
        Objects.requireNonNull(value, "value");
        this.value = value;
    }
}
