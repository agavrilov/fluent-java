package org.projectfluent.syntax.ast;

public final class FunctionReference extends Expression {
    // $FF: renamed from: id org.projectfluent.syntax.ast.Identifier

    private Identifier    field_2;

    private CallArguments arguments;

    public final Identifier getId() {
        return this.field_2;
    }

    public final void setId(Identifier var1) {
        Objects.requireNonNull(var1, "<set-?>");
        this.field_2 = var1;
    }

    public final CallArguments getArguments() {
        return this.arguments;
    }

    public final void setArguments(CallArguments var1) {
        Objects.requireNonNull(var1, "<set-?>");
        this.arguments = var1;
    }

    public FunctionReference(Identifier id, CallArguments arguments) {
        Objects.requireNonNull(id, "id");
        Objects.requireNonNull(arguments, "arguments");
        super();
        this.field_2 = id;
        this.arguments = arguments;
    }
}
