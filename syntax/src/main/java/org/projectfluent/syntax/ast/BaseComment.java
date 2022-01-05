package org.projectfluent.syntax.ast;

public abstract class BaseComment extends Entry {

    private String content;

    public final String getContent() {
        return this.content;
    }

    public final void setContent(String var1) {
        Objects.requireNonNull(var1, "<set-?>");
        this.content = var1;
    }

    public BaseComment(String content) {
        Objects.requireNonNull(content, "content");
        super();
        this.content = content;
    }
}
