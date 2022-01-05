package org.projectfluent.syntax.parser;

import java.util.Objects;

import kotlin.text.StringsKt;

public class ParserStream {

    private int    index;

    private int    peekOffset;

    private String string;

    public final int getIndex() {
        return this.index;
    }

    public final void setIndex(int var1) {
        this.index = var1;
    }

    public final int getPeekOffset() {
        return this.peekOffset;
    }

    public final void setPeekOffset(int var1) {
        this.peekOffset = var1;
    }

    private final Character charAt(int offset) {
        Character var10000 = StringsKt.getOrNull((CharSequence)this.string, offset);
        if (var10000 != null) {
            if (var10000 == '\r') {
                var10000 = StringsKt.getOrNull((CharSequence)this.string, offset + 1);
                if (var10000 != null) {
                    if (var10000 == '\n') {
                        var10000 = '\n';
                        return var10000;
                    }
                }
            }
        }

        var10000 = StringsKt.getOrNull((CharSequence)this.string, offset);
        return var10000;
    }

    public final Character currentChar() {
        return this.charAt(this.index);
    }

    public final Character currentPeek() {
        return this.charAt(this.index + this.peekOffset);
    }

    public final Character next() {
        this.peekOffset = 0;
        if (this.index >= this.string.length()) {
            return null;
        } else {
            int var10001;
            if (this.string.charAt(this.index) == '\r') {
                Character var10000 = StringsKt.getOrNull((CharSequence)this.string, this.index + 1);
                if (var10000 != null) {
                    if (var10000 == '\n') {
                        var10001 = this.index++;
                    }
                }
            }

            var10001 = this.index++;
            return StringsKt.getOrNull((CharSequence)this.string, this.index);
        }
    }

    public final Character peek() {
        Character var10000 = StringsKt.getOrNull((CharSequence)this.string, this.index + this.peekOffset);
        int var10001;
        if (var10000 != null) {
            if (var10000 == '\r') {
                var10000 = StringsKt.getOrNull((CharSequence)this.string, this.index + this.peekOffset + 1);
                if (var10000 != null) {
                    if (var10000 == '\n') {
                        var10001 = this.peekOffset++;
                    }
                }
            }
        }

        var10001 = this.peekOffset++;
        return StringsKt.getOrNull((CharSequence)this.string, this.index + this.peekOffset);
    }

    public final void resetPeek(int offset) {
        this.peekOffset = offset;
    }

    // $FF: synthetic method
    public static void resetPeek$default(ParserStream var0, int var1, int var2, Object var3) {
        if (var3 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: resetPeek");
        } else {
            if ((var2 & 1) != 0) {
                var1 = 0;
            }

            var0.resetPeek(var1);
        }
    }

    public final void skipToPeek() {
        this.index += this.peekOffset;
        this.peekOffset = 0;
    }

    public final String getString() {
        return this.string;
    }

    public final void setString(String var1) {
        Objects.requireNonNull(var1, "<set-?>");
        this.string = var1;
    }

    public ParserStream(String string) {
        Objects.requireNonNull(string, "string");
        super();
        this.string = string;
    }
}
