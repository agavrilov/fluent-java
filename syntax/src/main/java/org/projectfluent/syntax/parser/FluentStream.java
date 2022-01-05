package org.projectfluent.syntax.parser;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;

public final class FluentStream extends ParserStream {

    public final String peekBlankInline() {
        int start = this.getIndex() + this.getPeekOffset();

        while (true) {
            Character var10000 = this.currentPeek();
            if (var10000 == null) {
                break;
            }

            if (var10000 != ' ') {
                break;
            }

            this.peek();
        }

        return StringsKt.slice(this.getString(), RangesKt.until(start, this.getIndex() + this.getPeekOffset()));
    }

    public final String skipBlankInline() {
        String blank = this.peekBlankInline();
        this.skipToPeek();
        return blank;
    }

    public final String peekBlankBlock() {
        String blank = "";

        int lineStart;
        while (true) {
            lineStart = this.getPeekOffset();
            this.peekBlankInline();
            Character var10000 = this.currentPeek();
            byte var3 = 10;
            if (var10000 == null) {
                break;
            }

            if (var10000 != var3) {
                break;
            }

            blank = blank + '\n';
            this.peek();
        }

        if (Objects.equals(this.currentPeek(), StreamKt.getEOF())) {
            return blank;
        } else {
            this.resetPeek(lineStart);
            return blank;
        }
    }

    public final String skipBlankBlock() {
        String blank = this.peekBlankBlock();
        this.skipToPeek();
        return blank;
    }

    public final void peekBlank() {
        while (true) {
            label21: {
                Character var10000 = this.currentPeek();
                if (var10000 != null) {
                    if (var10000 == ' ') {
                        break label21;
                    }
                }

                var10000 = this.currentPeek();
                byte var1 = 10;
                if (var10000 != null) {
                    if (var10000 == var1) {
                        break label21;
                    }
                }

                return;
            }

            this.peek();
        }
    }

    public final void skipBlank() {
        this.peekBlank();
        this.skipToPeek();
    }

    public final void expectChar(char ch) {
        Character var10000 = this.currentChar();
        if (var10000 != null) {
            if (var10000 == ch) {
                this.next();
                return;
            }
        }

        throw (new ParseError("E0003", new Object[] {ch}));
    }

    public final void expectLineEnd() {
        if (!Objects.equals(this.currentChar(), StreamKt.getEOF())) {
            Character var10000 = this.currentChar();
            byte var1 = 10;
            if (var10000 != null) {
                if (var10000 == var1) {
                    this.next();
                    return;
                }
            }

            throw (new ParseError("E0003", new Object[] {"␤"}));
        }
    }

    public final Character takeChar(Function1 f) {
        Intrinsics.checkParameterIsNotNull(f, "f");
        Character ch = this.currentChar();
        if (ch != null) {
            boolean var4 = false;
            boolean var5 = false;
            char it = ch;
            int var7 = false;
            if ((Boolean)f.invoke(ch)) {
                this.next();
                return ch;
            }
        }

        return null;
    }

    private final boolean isCharIdStart(Character ch) {
        if (ch == null) {
            return false;
        } else {
            boolean var10000;
            label35: {
                boolean var3 = false;
                boolean var4 = false;
                char it = ch;
                int var6 = false;
                int cc = ch;
                if ('a' <= cc) {
                    if ('z' >= cc) {
                        break label35;
                    }
                }

                if ('A' <= cc) {
                    if ('Z' >= cc) {
                        break label35;
                    }
                }

                var10000 = false;
                return var10000;
            }

            var10000 = true;
            return var10000;
        }
    }

    public final boolean isIdentifierStart() {
        return this.isCharIdStart(this.currentPeek());
    }

    public final boolean isNumberStart() {
        Character var10000;
        label27: {
            var10000 = this.currentChar();
            if (var10000 != null) {
                if (var10000 == '-') {
                    var10000 = this.peek();
                    break label27;
                }
            }

            var10000 = this.currentChar();
        }

        Character ch = var10000;
        if (ch == null) {
            ParserStream.resetPeek$default(this, 0, 1, (Object)null);
            return false;
        } else {
            boolean var10;
            label21: {
                boolean var3 = false;
                boolean var4 = false;
                char it = ch;
                int var6 = false;
                int cc = ch;
                if ('0' <= cc) {
                    if ('9' >= cc) {
                        var10 = true;
                        break label21;
                    }
                }

                var10 = false;
            }

            boolean isDigit = var10;
            ParserStream.resetPeek$default(this, 0, 1, (Object)null);
            return isDigit;
        }
    }

    private final boolean isCharPatternContinuation(Character ch) {
        if (ch != null) {
            boolean var3 = false;
            boolean var4 = false;
            char it = ch;
            int var6 = false;
            return StringsKt.indexOf$default((CharSequence)"}.[*", ch, 0, false, 6, (Object)null) < 0;
        } else {
            return false;
        }
    }

    public final boolean isValueStart() {
        boolean var10000;
        label16: {
            Character ch = this.currentPeek();
            byte var2 = 10;
            if (ch != null) {
                if (ch == var2) {
                    break label16;
                }
            }

            if (Objects.equals(ch, StreamKt.getEOF()) ^ true) {
                var10000 = true;
                return var10000;
            }
        }

        var10000 = false;
        return var10000;
    }

    public final boolean isValueContinuation() {
        int column1 = this.getPeekOffset();
        this.peekBlankInline();
        Character var10000 = this.currentPeek();
        if (var10000 != null) {
            if (var10000 == '{') {
                this.resetPeek(column1);
                return true;
            }
        }

        if (this.getPeekOffset() - column1 == 0) {
            return false;
        } else if (this.isCharPatternContinuation(this.currentPeek())) {
            this.resetPeek(column1);
            return true;
        } else {
            return false;
        }
    }

    public final boolean isNextLineComment(int level) {
        Character var10000 = this.currentChar();
        int i = 10;
        if (var10000 != null) {
            if (var10000 == i) {
                i = 0;

                while (i <= level || level == -1 && i < 3) {
                    var10000 = this.peek();
                    if (var10000 != null) {
                        if (var10000 == '#') {
                            ++i;
                            continue;
                        }
                    }

                    if (i <= level && level != -1) {
                        ParserStream.resetPeek$default(this, 0, 1, (Object)null);
                        return false;
                    }
                    break;
                }

                label56: {
                    Character ch = this.peek();
                    if (ch != null) {
                        if (ch == ' ') {
                            break label56;
                        }
                    }

                    byte var4 = 10;
                    if (ch != null) {
                        if (ch == var4) {
                            break label56;
                        }
                    }

                    ParserStream.resetPeek$default(this, 0, 1, (Object)null);
                    return false;
                }

                ParserStream.resetPeek$default(this, 0, 1, (Object)null);
                return true;
            }
        }

        return false;
    }

    // $FF: synthetic method
    public static boolean isNextLineComment$default(FluentStream var0, int var1, int var2, Object var3) {
        if ((var2 & 1) != 0) {
            var1 = -1;
        }

        return var0.isNextLineComment(var1);
    }

    public final boolean isVariantStart() {
        int currentPeekOffset = this.getPeekOffset();
        Character var10000 = this.currentPeek();
        if (var10000 != null) {
            if (var10000 == '*') {
                this.peek();
            }
        }

        var10000 = this.currentPeek();
        if (var10000 != null) {
            if (var10000 == '[') {
                this.resetPeek(currentPeekOffset);
                return true;
            }
        }

        this.resetPeek(currentPeekOffset);
        return false;
    }

    public final boolean isAttributeStart() {
        Character var10000 = this.currentPeek();
        boolean var1;
        if (var10000 != null) {
            if (var10000 == '.') {
                var1 = true;
                return var1;
            }
        }

        var1 = false;
        return var1;
    }

    public final void skipToNextEntryStart(int junkStart) {
        int lastNewline = StringsKt.lastIndexOf$default((CharSequence)this.getString(), '\n', this.getIndex(), false, 4, (Object)null);
        if (junkStart < lastNewline) {
            this.setIndex(lastNewline);
        }

        while (this.currentChar() != null) {
            Character var10000 = this.currentChar();
            byte var3 = 10;
            if (var10000 != null) {
                if (var10000 == var3) {
                    Character first = this.next();
                    if (this.isCharIdStart(first)) {
                        break;
                    }

                    if (first != null) {
                        if (first == '-') {
                            break;
                        }
                    }

                    if (first == null) {
                        continue;
                    }

                    if (first == '#') {
                        break;
                    }
                    continue;
                }
            }

            this.next();
        }

    }

    public final char takeIDStart() {
        if (this.isCharIdStart(this.currentChar())) {
            Character ret = this.currentChar();
            if (ret != null) {
                boolean var3 = false;
                boolean var4 = false;
                char it = ret;
                int var6 = false;
                this.next();
                return ret;
            }
        }

        throw (new ParseError("E0004", new Object[] {"a-zA-Z"}));
    }

    public final Character takeIDChar() {
        Function1 closure = (Function1)null.INSTANCE;
        return this.takeChar(closure);
    }

    public final Character takeDigit() {
        Function1 closure = (Function1)null.INSTANCE;
        return this.takeChar(closure);
    }

    public final Character takeHexDigit() {
        Function1 closure = (Function1)null.INSTANCE;
        return this.takeChar(closure);
    }

    public FluentStream(String string) {
        Intrinsics.checkParameterIsNotNull(string, "string");
        super(string);
    }
}
