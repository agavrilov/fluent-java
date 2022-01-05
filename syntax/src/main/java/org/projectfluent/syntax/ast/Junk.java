package org.projectfluent.syntax.ast;

import java.util.ArrayList;
import java.util.List;

public final class Junk extends TopLevel {

    private final List   annotations;

    private final String content;

    public final List getAnnotations() {
        return this.annotations;
    }

    public final void addAnnotation(Annotation annotation) {
        Objects.requireNonNull(annotation, "annotation");
        this.annotations.add(annotation);
    }

    public final String getContent() {
        return this.content;
    }

    public Junk(String content) {
        Objects.requireNonNull(content, "content");
        super();
        this.content = content;
        boolean var2 = false;
        List var4 = (new ArrayList());
        this.annotations = var4;
    }
}
