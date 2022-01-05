package org.projectfluent.syntax.ast;

public final class Comment extends BaseComment {

    public Comment(String content) {
        Objects.requireNonNull(content, "content");
        super(content);
    }
}
