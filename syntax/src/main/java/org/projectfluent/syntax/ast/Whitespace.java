package org.projectfluent.syntax.ast;

public final class Whitespace extends TopLevel {

    private final String content;

    public final String getContent() {
        return this.content;
    }

    public Whitespace(String content) {
        Objects.requireNonNull(content, "content");
        super();
        this.content = content;
    }
}
