package org.projectfluent.syntax.ast;

public final class Span extends BaseNode {

    private int start;

    private int end;

    public final int getStart() {
        return this.start;
    }

    public final void setStart(int var1) {
        this.start = var1;
    }

    public final int getEnd() {
        return this.end;
    }

    public final void setEnd(int var1) {
        this.end = var1;
    }

    public Span(int start, int end) {
        this.start = start;
        this.end = end;
    }
}
