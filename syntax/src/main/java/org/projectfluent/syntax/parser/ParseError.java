package org.projectfluent.syntax.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import kotlin.collections.CollectionsKt;

public final class ParseError extends Exception {

    private final List args;

    private String     code;

    public final List getArgs() {
        return this.args;
    }

    public final String getCode() {
        return this.code;
    }

    public final void setCode(String var1) {
        Objects.requireNonNull(var1, "<set-?>");
        this.code = var1;
    }

    public ParseError(String code, Object... args) {
        Objects.requireNonNull(code, "code");
        Objects.requireNonNull(args, "args");
        super(ErrorKt.getErrorMessage(code, Arrays.copyOf(args, args.length)));
        this.code = code;
        boolean var3 = false;
        List var5 = (new ArrayList());
        this.args = var5;
        CollectionsKt.addAll((Collection)this.args, args);
    }
}
