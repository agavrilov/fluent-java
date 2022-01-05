package org.projectfluent.syntax.ast;

public final class ResourceComment extends BaseComment {

    public ResourceComment(String content) {
        Objects.requireNonNull(content, "content");
        super(content);
    }
}
