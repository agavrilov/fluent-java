package org.projectfluent.syntax.ast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import kotlin.collections.CollectionsKt;

public final class Resource extends SyntaxNode {

    private final List body;

    public final List getBody() {
        return this.body;
    }

    public Resource(TopLevel... children) {
        Objects.requireNonNull(children, "children");
        super();
        boolean var2 = false;
        List var5 = (new ArrayList());
        this.body = var5;
        Collection var6 = this.body;
        boolean var3 = false;
        CollectionsKt.addAll(var6, children);
    }
}
