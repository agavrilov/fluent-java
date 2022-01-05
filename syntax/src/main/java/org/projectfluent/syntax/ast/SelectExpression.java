package org.projectfluent.syntax.ast;

import java.util.List;

public final class SelectExpression extends Expression {

    private Expression selector;

    private List       variants;

    public final Expression getSelector() {
        return this.selector;
    }

    public final void setSelector(Expression var1) {
        Objects.requireNonNull(var1, "<set-?>");
        this.selector = var1;
    }

    public final List getVariants() {
        return this.variants;
    }

    public final void setVariants(List var1) {
        Objects.requireNonNull(var1, "<set-?>");
        this.variants = var1;
    }

    public SelectExpression(Expression selector, List variants) {
        Objects.requireNonNull(selector, "selector");
        Objects.requireNonNull(variants, "variants");
        super();
        this.selector = selector;
        this.variants = variants;
    }
}
