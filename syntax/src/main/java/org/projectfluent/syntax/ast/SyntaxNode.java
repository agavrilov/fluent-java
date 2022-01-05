package org.projectfluent.syntax.ast;

public abstract class SyntaxNode extends BaseNode {

    private Span span;

    public final Span getSpan() {
        return this.span;
    }

    public final void setSpan(Span var1) {
        this.span = var1;
    }

    public final void addSpan(int start, int end) {
        this.span = new Span(start, end);
    }
}
