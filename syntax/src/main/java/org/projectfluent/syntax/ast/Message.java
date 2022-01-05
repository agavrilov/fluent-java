package org.projectfluent.syntax.ast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Message extends Entry {

    private List       attributes;

    private Comment    comment;
    // $FF: renamed from: id org.projectfluent.syntax.ast.Identifier

    private Identifier field_5;

    private Pattern    value;

    public final List getAttributes() {
        return this.attributes;
    }

    public final void setAttributes(List var1) {
        Objects.requireNonNull(var1, "<set-?>");
        this.attributes = var1;
    }

    public final Comment getComment() {
        return this.comment;
    }

    public final void setComment(Comment var1) {
        this.comment = var1;
    }

    public final Identifier getId() {
        return this.field_5;
    }

    public final void setId(Identifier var1) {
        Objects.requireNonNull(var1, "<set-?>");
        this.field_5 = var1;
    }

    public final Pattern getValue() {
        return this.value;
    }

    public final void setValue(Pattern var1) {
        this.value = var1;
    }

    public Message(Identifier id, Pattern value) {
        Objects.requireNonNull(id, "id");
        super();
        this.field_5 = id;
        this.value = value;
        boolean var3 = false;
        List var5 = (new ArrayList());
        this.attributes = var5;
    }
}
