package org.projectfluent.syntax.ast;

import java.util.Objects;

public final class GroupComment extends BaseComment {

    public GroupComment(String content) {
        Objects.requireNonNull(content, "content");
        super(content);
    }
}
