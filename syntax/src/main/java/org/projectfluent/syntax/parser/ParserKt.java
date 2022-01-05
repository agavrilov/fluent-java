package org.projectfluent.syntax.parser;

import kotlin.text.Regex;

public final class ParserKt {

    private static final Regex trailingWSRe        = new Regex("[ \t\n\r]+$");

    private static final Regex VALID_FUNCTION_NAME = new Regex("^[A-Z][A-Z0-9_-]*$");

    // $FF: synthetic method
    public static final Regex access$getTrailingWSRe$p() {
        return trailingWSRe;
    }

    // $FF: synthetic method
    public static final Regex access$getVALID_FUNCTION_NAME$p() {
        return VALID_FUNCTION_NAME;
    }
}
