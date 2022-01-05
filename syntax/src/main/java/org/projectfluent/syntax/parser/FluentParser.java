package org.projectfluent.syntax.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.projectfluent.syntax.ast.Annotation;
import org.projectfluent.syntax.ast.Attribute;
import org.projectfluent.syntax.ast.BaseComment;
import org.projectfluent.syntax.ast.CallArgument;
import org.projectfluent.syntax.ast.CallArguments;
import org.projectfluent.syntax.ast.Comment;
import org.projectfluent.syntax.ast.Entry;
import org.projectfluent.syntax.ast.Expression;
import org.projectfluent.syntax.ast.FunctionReference;
import org.projectfluent.syntax.ast.GroupComment;
import org.projectfluent.syntax.ast.Identifier;
import org.projectfluent.syntax.ast.InsidePlaceable;
import org.projectfluent.syntax.ast.Junk;
import org.projectfluent.syntax.ast.Literal;
import org.projectfluent.syntax.ast.Message;
import org.projectfluent.syntax.ast.MessageReference;
import org.projectfluent.syntax.ast.NamedArgument;
import org.projectfluent.syntax.ast.NumberLiteral;
import org.projectfluent.syntax.ast.Pattern;
import org.projectfluent.syntax.ast.PatternElement;
import org.projectfluent.syntax.ast.Placeable;
import org.projectfluent.syntax.ast.Resource;
import org.projectfluent.syntax.ast.ResourceComment;
import org.projectfluent.syntax.ast.SelectExpression;
import org.projectfluent.syntax.ast.Span;
import org.projectfluent.syntax.ast.StringLiteral;
import org.projectfluent.syntax.ast.SyntaxNode;
import org.projectfluent.syntax.ast.Term;
import org.projectfluent.syntax.ast.TermReference;
import org.projectfluent.syntax.ast.TextElement;
import org.projectfluent.syntax.ast.TopLevel;
import org.projectfluent.syntax.ast.VariableReference;
import org.projectfluent.syntax.ast.Variant;
import org.projectfluent.syntax.ast.VariantKey;
import org.projectfluent.syntax.ast.Whitespace;

public final class FluentParser {

    private boolean withSpans;

    public final Resource parse(String source) {
        Objects.requireNonNull(source, "source");
        FluentStream ps = new FluentStream(source);
        boolean var4 = false;
        List entries = (new ArrayList());
        Object lastComment = null;
        String blankLines = ps.skipBlankBlock();
        CharSequence var6 = blankLines;
        boolean $i$f$toTypedArray = false;
        if (var6.length() > 0) {
            entries.add(new Whitespace(blankLines));
        }

        while (true) {
            while (ps.currentChar() != null) {
                TopLevel entry = this.getEntryOrJunk(ps);
                blankLines = ps.skipBlankBlock();
                boolean var8;
                CharSequence var18;
                if (entry instanceof Comment) {
                    var18 = blankLines;
                    var8 = false;
                    if (var18.length() == 0 && Intrinsics.areEqual(ps.currentChar(), StreamKt.getEOF()) ^ true) {
                        lastComment = entry;
                        continue;
                    }
                }

                if (lastComment != null) {
                    var8 = false;
                    boolean var9 = false;
                    int var11 = false;
                    if (entry instanceof Message) {
                        ((Message)entry).setComment(lastComment);
                    } else if (entry instanceof Term) {
                        ((Term)entry).setComment(lastComment);
                    } else {
                        entries.add(lastComment);
                    }

                    lastComment = null;
                }

                entries.add(entry);
                var18 = blankLines;
                var8 = false;
                if (var18.length() > 0) {
                    entries.add(new Whitespace(blankLines));
                }
            }

            Collection $this$toTypedArray$iv = entries;
            $i$f$toTypedArray = false;
            Object[] var10000 = $this$toTypedArray$iv.toArray(new TopLevel[0]);
            if (var10000 == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
            }

            Object[] var13 = var10000;
            TopLevel[] var14 = Arrays.copyOf((TopLevel[])var13, ((TopLevel[])var13).length);
            return new Resource(var14);
        }
    }

   private final TopLevel getEntryOrJunk(FluentStream ps) {
      int entryStartPos = ps.getIndex();

      try {
         Entry entry = this.getEntry(ps);
         ps.expectLineEnd();
         return entry;
      } catch (ParseError var9) {
         int errorIndex = ps.getIndex();
         ps.skipToNextEntryStart(entryStartPos);
         int nextEntryStart = ps.getIndex();
         if (nextEntryStart < errorIndex) {
            errorIndex = nextEntryStart;
         }

         String var7 = ps.getString();
         boolean var8 = false;
         if (var7 == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
         } else {
            String var10000 = var7.substring(entryStartPos, nextEntryStart);
            Intrinsics.checkExpressionValueIsNotNull(var10000, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            String slice = var10000;
            Junk junk = new Junk(slice);
            Annotation var10 = new Annotation;
            String var10002 = var9.getCode();
            String var10003 = var9.getMessage();
            if (var10003 == null) {
               var10003 = "";
            }

            var10.<init>(var10002, var10003);
            Annotation annot = var10;
            annot.getArguments().addAll(var9.getArgs());
            annot.addSpan(errorIndex, errorIndex);
            junk.addAnnotation(annot);
            return junk;
         }
      }
   }

    private final Entry getEntry(FluentStream ps) {
        Character var10000 = ps.currentChar();
        if (var10000 != null) {
            if (var10000 == '#') {
                return (Entry)this.getComment(ps);
            }
        }

        var10000 = ps.currentChar();
        if (var10000 != null) {
            if (var10000 == '-') {
                return (Entry)this.getTerm(ps);
            }
        }

        if (ps.isIdentifierStart()) {
            return (Entry)this.getMessage(ps);
        } else {
            throw (new ParseError("E0002", new Object[0]));
        }
    }

    private final BaseComment getComment(FluentStream ps) {
        int level = -1;
        String content = "";

        while (true) {
            int i = -1;
            int thisLevel = level == -1 ? 2 : level;

            Character var10000;
            while (true) {
                var10000 = ps.currentChar();
                if (var10000 == null) {
                    break;
                }

                if (var10000 != '#' || i >= thisLevel) {
                    break;
                }

                ps.next();
                ++i;
            }

            if (level == -1) {
                level = i;
            }

            label62: {
                var10000 = ps.currentChar();
                byte var6 = 10;
                if (var10000 != null) {
                    if (var10000 == var6) {
                        break label62;
                    }
                }

                ps.expectChar(' ');

                for (Character ch = ps.takeChar((Function1)null.INSTANCE); Intrinsics.areEqual(ch, StreamKt.getEOF()) ^ true; ch =
                        ps.takeChar((Function1)null.INSTANCE)) {
                    content = content + ch;
                }
            }

            if (!ps.isNextLineComment(level)) {
                BaseComment var7;
                switch (level) {
                    case 0:
                        var7 = (new Comment(content));
                        break;
                    case 1:
                        var7 = (new GroupComment(content));
                        break;
                    default:
                        var7 = (new ResourceComment(content));
                }

                return var7;
            }

            content = content + ps.currentChar();
            ps.next();
        }
    }

    private final Message getMessage(FluentStream ps) {
        Identifier id = this.getIdentifier(ps);
        ps.skipBlankInline();
        ps.expectChar('=');
        Pattern value = this.maybeGetPattern(ps);
        Collection attrs = this.getAttributes(ps);
        if (value == null && attrs.isEmpty()) {
            throw (new ParseError("E0005", new Object[] {id.getName()}));
        } else {
            Message msg = new Message(id, value);
            msg.getAttributes().addAll(attrs);
            return msg;
        }
    }

    private final Term getTerm(FluentStream ps) {
        ps.expectChar('-');
        Identifier id = this.getIdentifier(ps);
        ps.skipBlankInline();
        ps.expectChar('=');
        Pattern value = this.maybeGetPattern(ps);
        if (value == null) {
            throw (new ParseError("E0006", new Object[] {id.getName()}));
        } else {
            Collection attrs = this.getAttributes(ps);
            Term term = new Term(id, value);
            term.getAttributes().addAll(attrs);
            return term;
        }
    }

    private final Attribute getAttribute(FluentStream ps) {
        ps.expectChar('.');
        Identifier key = this.getIdentifier(ps);
        ps.skipBlankInline();
        ps.expectChar('=');
        Pattern var10000 = this.maybeGetPattern(ps);
        if (var10000 != null) {
            Pattern value = var10000;
            return new Attribute(key, value);
        } else {
            throw (new ParseError("E0012", new Object[0]));
        }
    }

    private final Collection getAttributes(FluentStream ps) {
        boolean var3 = false;
        List attrs = (new ArrayList());
        ps.peekBlank();

        while (ps.isAttributeStart()) {
            ps.skipToPeek();
            Attribute attr = this.getAttribute(ps);
            attrs.add(attr);
            ps.peekBlank();
        }

        return attrs;
    }

    private final Identifier getIdentifier(FluentStream ps) {
        String name = "" + ps.takeIDStart();

        for (Character ch = ps.takeIDChar(); ch != null; ch = ps.takeIDChar()) {
            name = name + ch;
        }

        return new Identifier(name);
    }

    private final VariantKey getVariantKey(FluentStream ps) {
        Character var10000 = ps.currentChar();
        if (var10000 == null) {
            throw (new ParseError("E0013", new Object[0]));
        } else {
            char var2 = var10000;
            if ('0' <= var2) {
                if ('9' >= var2) {
                    return (VariantKey)this.getNumber(ps);
                }
            }

            if (var2 != '-') {
                return (VariantKey)this.getIdentifier(ps);
            } else {
                return (VariantKey)this.getNumber(ps);
            }
        }
    }

    private final Variant getVariant(FluentStream ps, boolean hasDefault) {
        boolean defaultIndex = false;
        Character var10000 = ps.currentChar();
        if (var10000 != null) {
            if (var10000 == '*') {
                if (hasDefault) {
                    throw (new ParseError("E0015", new Object[0]));
                }

                ps.next();
                defaultIndex = true;
            }
        }

        ps.expectChar('[');
        ps.skipBlank();
        VariantKey key = this.getVariantKey(ps);
        ps.skipBlank();
        ps.expectChar(']');
        Pattern var6 = this.maybeGetPattern(ps);
        if (var6 != null) {
            Pattern value = var6;
            return new Variant(key, value, defaultIndex);
        } else {
            throw (new ParseError("E0012", new Object[0]));
        }
    }

    // $FF: synthetic method
    static Variant getVariant$default(FluentParser var0, FluentStream var1, boolean var2, int var3, Object var4) {
        if ((var3 & 2) != 0) {
            var2 = false;
        }

        return var0.getVariant(var1, var2);
    }

    private final List getVariants(FluentStream ps) {
        boolean hasDefault = false;
        List variants = (new ArrayList());
        hasDefault = false;
        ps.skipBlank();

        while (ps.isVariantStart()) {
            Variant variant = this.getVariant(ps, hasDefault);
            if (variant.getDefault()) {
                hasDefault = true;
            }

            variants.add(variant);
            ps.expectLineEnd();
            ps.skipBlank();
        }

        if (variants.size() == 0) {
            throw (new ParseError("E0011", new Object[0]));
        } else if (!hasDefault) {
            throw (new ParseError("E0010", new Object[0]));
        } else {
            return variants;
        }
    }

    private final String getDigits(FluentStream ps) {
        String num = "";

        for (Character ch = ps.takeDigit(); ch != null; ch = ps.takeDigit()) {
            num = num + ch;
        }

        CharSequence var4 = num;
        boolean var5 = false;
        if (var4.length() == 0) {
            throw (new ParseError("E0004", new Object[] {"0-9"}));
        } else {
            return num;
        }
    }

    private final NumberLiteral getNumber(FluentStream ps) {
        StringBuilder var10000;
        String value;
        String var4;
        label20: {
            value = "";
            var10000 = (new StringBuilder()).append(value);
            Character var10001 = ps.currentChar();
            if (var10001 != null) {
                if (var10001 == '-') {
                    ps.next();
                    var4 = '-' + this.getDigits(ps);
                    break label20;
                }
            }

            var4 = this.getDigits(ps);
        }

        value = var10000.append(var4).toString();
        Character var3 = ps.currentChar();
        if (var3 != null) {
            if (var3 == '.') {
                ps.next();
                value = value + '.' + this.getDigits(ps);
            }
        }

        return new NumberLiteral(value);
    }

    private final Pattern maybeGetPattern(FluentStream ps) {
        ps.peekBlankInline();
        if (ps.isValueStart()) {
            ps.skipToPeek();
            return this.getPattern(ps, false);
        } else {
            ps.peekBlankBlock();
            if (ps.isValueContinuation()) {
                ps.skipToPeek();
                return this.getPattern(ps, true);
            } else {
                return null;
            }
        }
    }

    private final Pattern getPattern(FluentStream ps, boolean isBlock) {
        boolean var4 = false;
        List elements = (new ArrayList());
        int var10000;
        if (isBlock) {
            int blankStart = ps.getIndex();
            String firstIndent = ps.skipBlankInline();
            elements.add(this.getIndent(ps, firstIndent, blankStart));
            var10000 = firstIndent.length();
        } else {
            var10000 = Integer.MAX_VALUE;
        }

        int commonIndentLength = var10000;
        Character ch = null;

        while (true) {
            ch = ps.currentChar();
            if (ch == null) {
                break;
            }

            int blankStart = 10;
            if (ch == blankStart) {
                blankStart = ps.getIndex();
                String blankLines = ps.peekBlankBlock();
                if (!ps.isValueContinuation()) {
                    ParserStream.resetPeek$default(ps, 0, 1, (Object)null);
                    break;
                }

                ps.skipToPeek();
                String indent = ps.skipBlankInline();
                int var10 = indent.length();
                boolean var11 = false;
                commonIndentLength = Math.min(commonIndentLength, var10);
                elements.add(this.getIndent(ps, blankLines + indent, blankStart));
            } else if (ch == '{') {
                elements.add(this.getPlaceable(ps));
            } else {
                if (ch == '}') {
                    throw (new ParseError("E0027", new Object[0]));
                }

                elements.add(this.getTextElement(ps));
            }
        }

        List dedented = this.dedent(elements, commonIndentLength);
        Collection $this$toTypedArray$iv = dedented;
        int $i$f$toTypedArray = false;
        Object[] var21 = $this$toTypedArray$iv.toArray(new PatternElement[0]);
        if (var21 == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
        } else {
            Object[] var14 = var21;
            PatternElement[] var15 = Arrays.copyOf((PatternElement[])var14, ((PatternElement[])var14).length);
            return new Pattern(var15);
        }
    }

    private final Indent getIndent(FluentStream ps, String value, int start) {
        return new Indent(value, start, ps.getIndex());
    }

   private final List dedent(Collection elements, int commonIndent) {
      boolean var4 = false;
      List trimmed = (new ArrayList());
      Iterator var5 = elements.iterator();

      while(true) {
         PatternElement element;
         TextElement var23;
         while(var5.hasNext()) {
            element = (PatternElement)var5.next();
            if (element instanceof Placeable) {
               trimmed.add(element);
            } else {
               boolean var7;
               if (element instanceof Indent) {
                  ((Indent)element).setValue(StringsKt.slice(((Indent)element).getValue(), RangesKt.until(0, ((Indent)element).getValue().length() - commonIndent)));
                  CharSequence var6 = ((Indent)element).getValue();
                  var7 = false;
                  if (var6.length() == 0) {
                     continue;
                  }
               }

               Collection var14 = trimmed;
               var7 = false;
               Integer start;
               Span var10000;
               if (!var14.isEmpty()) {
                  PatternElement prev = (PatternElement)CollectionsKt.last(trimmed);
                  if (prev instanceof TextElement) {
                     var23 = new TextElement;
                     StringBuilder var10002 = (new StringBuilder()).append(((TextElement)prev).getValue());
                     String var10003;
                     if (element instanceof TextElement) {
                        var10003 = ((TextElement)element).getValue();
                     } else {
                        if (!(element instanceof Indent)) {
                           throw (new IllegalStateException("Unexpected PatternElement type"));
                        }

                        var10003 = ((Indent)element).getValue();
                     }

                     var23.<init>(var10002.append(var10003).toString());
                     TextElement sum = var23;
                     if (this.withSpans) {
                        var10000 = prev.getSpan();
                        start = var10000 != null ? var10000.getStart() : null;
                        var10000 = element.getSpan();
                        Integer end = var10000 != null ? var10000.getEnd() : null;
                        if (start != null && end != null) {
                           sum.addSpan(start, end);
                        }
                     }

                     trimmed.set(trimmed.size() - 1, sum);
                     continue;
                  }
               }

               if (element instanceof Indent) {
                  TextElement textElement = new TextElement(((Indent)element).getValue());
                  if (this.withSpans) {
                     var10000 = element.getSpan();
                     Integer start = var10000 != null ? var10000.getStart() : null;
                     var10000 = element.getSpan();
                     start = var10000 != null ? var10000.getEnd() : null;
                     if (start != null && start != null) {
                        textElement.addSpan(start, start);
                     }
                  }

                  trimmed.add(textElement);
               } else {
                  trimmed.add(element);
               }
            }
         }

         element = (PatternElement)CollectionsKt.last(trimmed);
         if (element instanceof TextElement) {
            var23 = (TextElement)element;
            CharSequence var13 = ((TextElement)element).getValue();
            Regex var17 = ParserKt.access$getTrailingWSRe$p();
            String var21 = "";
            TextElement var10 = var23;
            boolean var22 = false;
            String var11 = var17.replace(var13, var21);
            var10.setValue(var11);
            var13 = ((TextElement)element).getValue();
            boolean var18 = false;
            if (var13.length() == 0) {
               trimmed.remove(trimmed.size() - 1);
            }
         }

         return trimmed;
      }
   }

    private final TextElement getTextElement(FluentStream ps) {
        String buffer = "";
        Character ch = null;

        while (true) {
            ch = ps.currentChar();
            if (ch == null) {
                return new TextElement(buffer);
            }

            if (ch == '{' || ch == '}') {
                break;
            }

            byte var4 = 10;
            if (ch == var4) {
                break;
            }

            buffer = buffer + ch;
            ps.next();
        }

        return new TextElement(buffer);
    }

   private final String getEscapeSequence(FluentStream ps) {
      Character next;
      String var3;
      label56: {
         label46: {
            next = ps.currentChar();
            if (next != null) {
               if (next == '\\') {
                  break label46;
               }
            }

            if (next == null) {
               break label56;
            }

            if (next != '"') {
               break label56;
            }
         }

         ps.next();
         var3 = "" + '\\' + next;
         return var3;
      }

      if (next != null) {
         if (next == 'u') {
            var3 = this.getUnicodeEscapeSequence(ps, next, 4);
            return var3;
         }
      }

      if (next != null) {
         if (next == 'U') {
            var3 = this.getUnicodeEscapeSequence(ps, next, 6);
            return var3;
         }
      }

      ParseError var10000 = new ParseError;
      Object[] var10003 = new Object[1];
      Object var10006 = next;
      if (next == null) {
         var10006 = "EOF";
      }

      var10003[0] = var10006;
      var10000.<init>("E0025", var10003);
      throw var10000;
   }

    private final String getUnicodeEscapeSequence(FluentStream ps, char u, int digits) {
        ps.expectChar(u);
        String sequence = "";
        int var5 = 0;

        for (int var6 = digits; var5 < var6; ++var5) {
            Character var10000 = ps.takeHexDigit();
            if (var10000 == null) {
                throw (new ParseError("E0026", new Object[] {"" + '\\' + u + sequence + ps.currentChar()}));
            }

            char ch = var10000;
            sequence = sequence + ch;
        }

        return "" + '\\' + u + sequence;
    }

   private final PatternElement getPlaceable(FluentStream ps) {
      SyntaxNode var10000;
      label17: {
         ps.expectChar('{');
         ps.skipBlank();
         Character var3 = ps.currentChar();
         if (var3 != null) {
            if (var3 == '{') {
               PatternElement child = this.getPlaceable(ps);
               ps.skipBlank();
               var10000 = child;
               break label17;
            }
         }

         var10000 = (SyntaxNode)this.getExpression(ps);
      }

      SyntaxNode expression = var10000;
      ps.expectChar('}');
      Placeable var5 = new Placeable;
      if (expression == null) {
         throw new TypeCastException("null cannot be cast to non-null type org.projectfluent.syntax.ast.InsidePlaceable");
      } else {
         var5.<init>((InsidePlaceable)expression);
         return var5;
      }
   }

    private final Expression getExpression(FluentStream ps) {
        Expression selector = this.getInlineExpression(ps);
        ps.skipBlank();
        Character var10000 = ps.currentChar();
        if (var10000 != null) {
            if (var10000 == '-') {
                var10000 = ps.peek();
                if (var10000 != null) {
                    if (var10000 == '>') {
                        if (selector instanceof MessageReference) {
                            if (((MessageReference)selector).getAttribute() == null) {
                                throw (new ParseError("E0016", new Object[0]));
                            }

                            throw (new ParseError("E0018", new Object[0]));
                        }

                        if (selector instanceof TermReference && ((TermReference)selector).getAttribute() == null) {
                            throw (new ParseError("E0017", new Object[0]));
                        }

                        ps.next();
                        ps.next();
                        ps.skipBlankInline();
                        ps.expectLineEnd();
                        List variants = this.getVariants(ps);
                        return (new SelectExpression(selector, variants));
                    }
                }

                ParserStream.resetPeek$default(ps, 0, 1, (Object)null);
                return selector;
            }
        }

        if (selector instanceof TermReference && ((TermReference)selector).getAttribute() != null) {
            throw (new ParseError("E0019", new Object[0]));
        } else {
            return selector;
        }
    }

    private final Expression getInlineExpression(FluentStream ps) {
        if (ps.isNumberStart()) {
            return (Expression)this.getNumber(ps);
        } else {
            Character var10000 = ps.currentChar();
            if (var10000 != null) {
                if (var10000 == '"') {
                    return (Expression)this.getString(ps);
                }
            }

            var10000 = ps.currentChar();
            Identifier id;
            if (var10000 != null) {
                if (var10000 == '$') {
                    ps.next();
                    id = this.getIdentifier(ps);
                    return (new VariableReference(id));
                }
            }

            var10000 = ps.currentChar();
            Identifier attr;
            if (var10000 != null) {
                if (var10000 == '-') {
                    ps.next();
                    id = this.getIdentifier(ps);
                    attr = null;
                    var10000 = ps.currentChar();
                    if (var10000 != null) {
                        if (var10000 == '.') {
                            ps.next();
                            attr = this.getIdentifier(ps);
                        }
                    }

                    CallArguments args = null;
                    ps.peekBlank();
                    var10000 = ps.currentPeek();
                    if (var10000 != null) {
                        if (var10000 == '(') {
                            ps.skipToPeek();
                            args = this.getCallArguments(ps);
                        }
                    }

                    return (new TermReference(id, attr, args));
                }
            }

            if (ps.isIdentifierStart()) {
                id = this.getIdentifier(ps);
                ps.peekBlank();
                var10000 = ps.currentPeek();
                if (var10000 != null) {
                    if (var10000 == '(') {
                        if (ParserKt.access$getVALID_FUNCTION_NAME$p().matchEntire((CharSequence)id.getName()) == null) {
                            throw (new ParseError("E0008", new Object[0]));
                        }

                        ps.skipToPeek();
                        CallArguments args = this.getCallArguments(ps);
                        return (new FunctionReference(id, args));
                    }
                }

                attr = null;
                var10000 = ps.currentChar();
                if (var10000 != null) {
                    if (var10000 == '.') {
                        ps.next();
                        attr = this.getIdentifier(ps);
                    }
                }

                return (new MessageReference(id, attr));
            } else {
                throw (new ParseError("E0028", new Object[0]));
            }
        }
    }

    private final CallArgument getCallArgument(FluentStream ps) {
        Expression exp = this.getInlineExpression(ps);
        ps.skipBlank();
        Character var10000 = ps.currentChar();
        if (var10000 != null) {
            if (var10000 == ':') {
                if (exp instanceof MessageReference && ((MessageReference)exp).getAttribute() == null) {
                    ps.next();
                    ps.skipBlank();
                    Literal value = this.getLiteral(ps);
                    return (new NamedArgument(((MessageReference)exp).getId(), value));
                }

                throw (new ParseError("E0009", new Object[0]));
            }
        }

        return exp;
    }

    private final CallArguments getCallArguments(FluentStream ps) {
        boolean var3 = false;
        List positional = (new ArrayList());
        boolean var4 = false;
        List named = (new ArrayList());
        boolean var5 = false;
        Set argumentNames = (new LinkedHashSet());
        ps.expectChar('(');
        ps.skipBlank();

        while (true) {
            Character var10000 = ps.currentChar();
            if (var10000 != null) {
                if (var10000 == ')') {
                    break;
                }
            }

            CallArgument arg = this.getCallArgument(ps);
            if (arg instanceof NamedArgument) {
                if (argumentNames.contains(((NamedArgument)arg).getName().getName())) {
                    throw (new ParseError("E0022", new Object[0]));
                }

                named.add(arg);
                argumentNames.add(((NamedArgument)arg).getName().getName());
            } else if (arg instanceof Expression) {
                if (argumentNames.size() > 0) {
                    throw (new ParseError("E0021", new Object[0]));
                }

                positional.add(arg);
            }

            ps.skipBlank();
            var10000 = ps.currentChar();
            if (var10000 == null) {
                break;
            }

            if (var10000 != ',') {
                break;
            }

            ps.next();
            ps.skipBlank();
        }

        ps.expectChar(')');
        CallArguments args = new CallArguments();
        args.getPositional().addAll(positional);
        args.getNamed().addAll(named);
        return args;
    }

    private final StringLiteral getString(FluentStream ps) {
        ps.expectChar('"');
        String value = "";
        Function1 filter = (Function1)null.INSTANCE;

        for (Character ch = ps.takeChar(filter); ch != null; ch = ps.takeChar(filter)) {
            if (ch == '\\') {
                value = value + this.getEscapeSequence(ps);
            } else {
                value = value + ch;
            }
        }

        Character var10000 = ps.currentChar();
        byte var5 = 10;
        if (var10000 != null) {
            if (var10000 == var5) {
                throw (new ParseError("E0020", new Object[0]));
            }
        }

        ps.expectChar('"');
        return new StringLiteral(value);
    }

    private final Literal getLiteral(FluentStream ps) {
        if (ps.isNumberStart()) {
            return (Literal)this.getNumber(ps);
        } else {
            Character var10000 = ps.currentChar();
            if (var10000 != null) {
                if (var10000 == '"') {
                    return (Literal)this.getString(ps);
                }
            }

            throw (new ParseError("E0014", new Object[0]));
        }
    }

    public final boolean getWithSpans() {
        return this.withSpans;
    }

    public final void setWithSpans(boolean var1) {
        this.withSpans = var1;
    }

    public FluentParser(boolean withSpans) {
        this.withSpans = withSpans;
    }

    // $FF: synthetic method
    public FluentParser(boolean var1, int var2, DefaultConstructorMarker var3) {
        if ((var2 & 1) != 0) {
            var1 = false;
        }

        this(var1);
    }

    public FluentParser() {
        this(false, 1, (DefaultConstructorMarker)null);
    }
}
