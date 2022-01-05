package org.projectfluent.syntax.ast;

import java.util.Objects;

public final class Comment extends BaseComment {

    public Comment(String content) {
        super(content);
        Objects.requireNonNull(content, "content");
    }
}
