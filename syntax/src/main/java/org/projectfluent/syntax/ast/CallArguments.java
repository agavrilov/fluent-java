package org.projectfluent.syntax.ast;

import java.util.ArrayList;
import java.util.List;

public final class CallArguments extends SyntaxNode {

    private final List positional;

    private final List named;

    public final List getPositional() {
        return this.positional;
    }

    public final List getNamed() {
        return this.named;
    }

    public CallArguments() {
        boolean var1 = false;
        List var3 = (new ArrayList());
        this.positional = var3;
        var1 = false;
        var3 = (new ArrayList());
        this.named = var3;
    }
}
