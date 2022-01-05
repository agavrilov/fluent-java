package org.projectfluent.syntax.parser;

public final class StreamKt {

    public static final char       EOL                      = '\n';

    private static final Character EOF;

    public static final String     SPECIAL_LINE_START_CHARS = "}.[*";

    public static final Character getEOF() {
        return EOF;
    }
}
