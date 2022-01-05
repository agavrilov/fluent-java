package org.projectfluent.syntax.ast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Annotation extends SyntaxNode {

    private final List<?> arguments;

    private String        code;

    private String        message;

    public final List<?> getArguments() {
        return this.arguments;
    }

    public final String getCode() {
        return this.code;
    }

    public final void setCode(String var1) {
        Objects.requireNonNull(var1, "<set-?>");
        this.code = var1;
    }

    public final String getMessage() {
        return this.message;
    }

    public final void setMessage(String var1) {
        Objects.requireNonNull(var1, "<set-?>");
        this.message = var1;
    }

    public Annotation(String code, String message) {
        super();
        Objects.requireNonNull(code, "code");
        Objects.requireNonNull(message, "message");
        this.code = code;
        this.message = message;
        boolean var3 = false;
        List<?> var5 = new ArrayList<>();
        this.arguments = var5;
    }
}
