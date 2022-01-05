package org.projectfluent.syntax.ast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import kotlin.collections.CollectionsKt;

public final class Pattern extends SyntaxNode {

    private final List elements;

    public final List getElements() {
        return this.elements;
    }

    public Pattern(PatternElement... elements) {
        Objects.requireNonNull(elements, "elements");
        super();
        boolean var2 = false;
        List var5 = (new ArrayList());
        this.elements = var5;
        Collection var6 = this.elements;
        boolean var3 = false;
        CollectionsKt.addAll(var6, elements);
    }
}
