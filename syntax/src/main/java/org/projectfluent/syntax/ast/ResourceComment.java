package org.projectfluent.syntax.ast;

import java.util.Objects;

public final class ResourceComment extends BaseComment {

    public ResourceComment(String content) {
        Objects.requireNonNull(content, "content");
        super(content);
    }
}
