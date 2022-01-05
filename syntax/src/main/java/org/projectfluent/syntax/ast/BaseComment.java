package org.projectfluent.syntax.ast;

import java.util.Objects;

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
        super();
        Objects.requireNonNull(content, "content");
        this.content = content;
    }
}
