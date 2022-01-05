package org.projectfluent.syntax.ast;

public final class GroupComment extends BaseComment {

    public GroupComment(String content) {
        Objects.requireNonNull(content, "content");
        super(content);
    }
}
