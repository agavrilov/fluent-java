package org.projectfluent.syntax.parser;

import java.util.Objects;

public final class ErrorKt {

    public static final String getErrorMessage(String code, Object... args) {
        Objects.requireNonNull(code, "code");
        Objects.requireNonNull(args, "args");
        String var10000;
        switch (code) {
            case "E0001":
                var10000 = "Generic Error";
                return var10000;
            case "E0002":
                var10000 = "Expected an entry start";
                return var10000;
            case "E0003":
                var10000 = "Expected token: \"" + args[0] + '"';
                return var10000;
            case "E0004":
                var10000 = "Expected a character from range: \"" + args[0] + '"';
                return var10000;
            case "E0005":
                var10000 = "Expected message \"" + args[0] + "\" to have a value or attributes";
                return var10000;
            case "E0006":
                var10000 = "Expected term \"-" + args[0] + "\" to have a value";
                return var10000;
            case "E0007":
                var10000 = "Keyword cannot end with a whitespace";
                return var10000;
            case "E0008":
                var10000 = "The callee has to be an upper-case identifier or a term";
                return var10000;
            case "E0009":
                var10000 = "The argument name has to be a simple identifier";
                return var10000;
            case "E0010":
                var10000 = "Expected one of the variants to be marked as default (*)";
                return var10000;
            case "E0011":
                var10000 = "Expected at least one variant after \"->\"";
                return var10000;
            case "E0012":
                var10000 = "Expected value";
                return var10000;
            case "E0013":
                var10000 = "Expected variant key";
                return var10000;
            case "E0014":
                var10000 = "Expected literal";
                return var10000;
            case "E0015":
                var10000 = "Only one variant can be marked as default (*)";
                return var10000;
            case "E0016":
                var10000 = "Message references cannot be used as selectors";
                return var10000;
            case "E0017":
                var10000 = "Terms cannot be used as selectors";
                return var10000;
            case "E0018":
                var10000 = "Attributes of messages cannot be used as selectors";
                return var10000;
            case "E0019":
                var10000 = "Attributes of terms cannot be used as placeables";
                return var10000;
            case "E0020":
                var10000 = "Unterminated string expression";
                return var10000;
            case "E0021":
                var10000 = "Positional arguments must not follow named arguments";
                return var10000;
            case "E0022":
                var10000 = "Named arguments must be unique";
                return var10000;
            case "E0024":
                var10000 = "Cannot access variants of a message.";
                return var10000;
            case "E0025":
                var10000 = "Unknown escape sequence: \\" + args[0] + '.';
                return var10000;
            case "E0026":
                var10000 = "Invalid Unicode escape sequence: " + args[0] + '.';
                return var10000;
            case "E0027":
                var10000 = "Unbalanced closing brace in TextElement.";
                return var10000;
            case "E0028":
                var10000 = "Expected an inline expression";
                return var10000;
        }

        var10000 = code;
        return var10000;
    }
}
