package org.projectfluent.syntax.serializer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;
import kotlin.text.StringsKt;
import org.projectfluent.syntax.ast.Attribute;
import org.projectfluent.syntax.ast.BaseComment;
import org.projectfluent.syntax.ast.CallArguments;
import org.projectfluent.syntax.ast.Comment;
import org.projectfluent.syntax.ast.Entry;
import org.projectfluent.syntax.ast.Expression;
import org.projectfluent.syntax.ast.FunctionReference;
import org.projectfluent.syntax.ast.GroupComment;
import org.projectfluent.syntax.ast.Identifier;
import org.projectfluent.syntax.ast.InsidePlaceable;
import org.projectfluent.syntax.ast.Junk;
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
import org.projectfluent.syntax.ast.StringLiteral;
import org.projectfluent.syntax.ast.Term;
import org.projectfluent.syntax.ast.TermReference;
import org.projectfluent.syntax.ast.TextElement;
import org.projectfluent.syntax.ast.TopLevel;
import org.projectfluent.syntax.ast.VariableReference;
import org.projectfluent.syntax.ast.Variant;
import org.projectfluent.syntax.ast.VariantKey;
import org.projectfluent.syntax.ast.Whitespace;

public final class FluentSerializer {

    private final boolean withJunk;

    public final CharSequence serialize(Resource resource) {
        Objects.requireNonNull(resource, "resource");
        Iterable $this$mapNotNull$iv = resource.getBody();
        int $i$f$mapNotNull = false;
        Collection destination$iv$iv = (new ArrayList());
        int $i$f$mapNotNullTo = false;
        int $i$f$forEach = false;
        Iterator var9 = $this$mapNotNull$iv.iterator();

        while (var9.hasNext()) {
            Object element$iv$iv$iv = var9.next();
            int var12 = false;
            TopLevel it = (TopLevel)element$iv$iv$iv;
            int var14 = false;
            CharSequence var10000;
            if (it instanceof Entry) {
                var10000 = this.serializeEntry((Entry)it);
            } else if (it instanceof Whitespace) {
                var10000 = ((Whitespace)it).getContent();
            } else {
                if (!(it instanceof Junk)) {
                    throw (new SerializeError("Unknown top-level entry type"));
                }

                String var16 = ((Junk)it).getContent();
                boolean var17 = false;
                boolean var18 = false;
                int var20 = false;
                var10000 = this.withJunk ? var16 : null;
            }

            if (var10000 != null) {
                CharSequence var21 = var10000;
                boolean var22 = false;
                boolean var23 = false;
                int var25 = false;
                destination$iv$iv.add(var21);
            }
        }

        return (CharSequence)CollectionsKt.joinToString$default((Iterable)((List)destination$iv$iv),
                (CharSequence)"",
                (CharSequence)null,
                (CharSequence)null,
                0,
                (CharSequence)null,
                (Function1)null,
                62,
                (Object)null);
    }

    public final CharSequence serialize(TopLevel entry) {
        Objects.requireNonNull(entry, "entry");
        CharSequence var10000;
        if (entry instanceof Entry) {
            var10000 = this.serializeEntry((Entry)entry);
        } else if (entry instanceof Whitespace) {
            var10000 = ((Whitespace)entry).getContent();
        } else {
            if (!(entry instanceof Junk)) {
                throw (new SerializeError("Unknown top-level type: " + entry));
            }

            var10000 = ((Junk)entry).getContent();
        }

        return var10000;
    }

    public final CharSequence serialize(Expression expr) {
        Objects.requireNonNull(expr, "expr");
        return this.serializeExpression(expr);
    }

    public final CharSequence serialize(VariantKey key) {
        Objects.requireNonNull(key, "key");
        return this.serializeVariantKey(key);
    }

    private final CharSequence serializeEntry(Entry entry) {
        CharSequence var10000;
        if (entry instanceof Message) {
            var10000 = this.serializeMessage((Message)entry);
        } else if (entry instanceof Term) {
            var10000 = this.serializeTerm((Term)entry);
        } else if (entry instanceof Comment) {
            var10000 = this.serializeComment((BaseComment)entry, (CharSequence)"#");
        } else if (entry instanceof GroupComment) {
            var10000 = this.serializeComment((BaseComment)entry, (CharSequence)"##");
        } else {
            if (!(entry instanceof ResourceComment)) {
                throw (new SerializeError("Unknown entry type: " + entry));
            }

            var10000 = this.serializeComment((BaseComment)entry, (CharSequence)"###");
        }

        return var10000;
    }

    private final String serializeComment(BaseComment comment, final CharSequence prefix) {
        return CollectionsKt.joinToString$default(
                (Iterable)StringsKt.split$default((CharSequence)comment.getContent(), new String[] {"\n"}, false, 0, 6, (Object)null),
                (CharSequence)"",
                (CharSequence)null,
                (CharSequence)null,
                0,
                (CharSequence)null,
                (Function1)(new Function1() {

                    public final CharSequence invoke(String it) {
                        Objects.requireNonNull(it, "it");
                        CharSequence var2 = it;
                        boolean var3 = false;
                        return var2.length() > 0 ? (CharSequence)(new StringBuilder()).append(prefix).append(' ').append(it).append('\n').toString()
                                : (CharSequence)(new StringBuilder()).append(prefix).append('\n').toString();
                    }
                }),
                30,
                (Object)null);
    }

    // $FF: synthetic method
    static String serializeComment$default(FluentSerializer var0, BaseComment var1, CharSequence var2, int var3, Object var4) {
        if ((var3 & 2) != 0) {
            var2 = "#";
        }

        return var0.serializeComment(var1, var2);
    }

    private final CharSequence serializeMessage(Message message) {
        StringBuilder builder = new StringBuilder();
        Comment var10000 = message.getComment();
        boolean var4;
        boolean var5;
        boolean var7;
        if (var10000 != null) {
            Comment var3 = var10000;
            var4 = false;
            var5 = false;
            var7 = false;
            builder.append(serializeComment$default(this, var3, (CharSequence)null, 2, (Object)null));
        }

        StringsKt.append(builder, new String[] {message.getId().getName(), " ="});
        Pattern var11 = message.getValue();
        if (var11 != null) {
            Pattern var8 = var11;
            var4 = false;
            var5 = false;
            var7 = false;
            builder.append(this.serializePattern(var8));
        }

        Iterator var10 = message.getAttributes().iterator();

        while (var10.hasNext()) {
            Attribute attribute = (Attribute)var10.next();
            builder.append(this.serializeAttribute(attribute));
        }

        builder.append("\n");
        return builder;
    }

    private final CharSequence serializeTerm(Term term) {
        StringBuilder builder = new StringBuilder();
        Comment var10000 = term.getComment();
        if (var10000 != null) {
            Comment var3 = var10000;
            boolean var4 = false;
            boolean var5 = false;
            int var7 = false;
            builder.append(serializeComment$default(this, var3, (CharSequence)null, 2, (Object)null));
        }

        builder.append("-" + term.getId().getName() + " =");
        builder.append(this.serializePattern(term.getValue()));
        Iterator var9 = term.getAttributes().iterator();

        while (var9.hasNext()) {
            Attribute attribute = (Attribute)var9.next();
            builder.append(this.serializeAttribute(attribute));
        }

        builder.append("\n");
        return builder;
    }

    private final CharSequence serializeAttribute(Attribute attribute) {
        String value = SerializerKt.access$indentExceptFirstLine(this.serializePattern(attribute.getValue()));
        return "\n    ." + attribute.getId().getName() + " =" + value;
    }

    private final CharSequence serializePattern(Pattern pattern) {
        Iterable $this$map$iv = pattern.getElements();
        FluentSerializer var4 = this;
        int $i$f$map = false;
        Collection destination$iv$iv = (new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10)));
        int $i$f$mapTo = false;
        Iterator var9 = $this$map$iv.iterator();

        while (var9.hasNext()) {
            Object item$iv$iv = var9.next();
            PatternElement p1 = (PatternElement)item$iv$iv;
            int var12 = false;
            CharSequence var14 = var4.serializeElement(p1);
            destination$iv$iv.add(var14);
        }

        List elements = (List)destination$iv$iv;
        String content = SerializerKt.access$indentExceptFirstLine((CharSequence)CollectionsKt.joinToString$default((Iterable)elements,
                (CharSequence)"",
                (CharSequence)null,
                (CharSequence)null,
                0,
                (CharSequence)null,
                (Function1)null,
                62,
                (Object)null));
        return SerializerKt.access$shouldStartOnNewLine(pattern) ? (CharSequence)("\n    " + content) : (CharSequence)(' ' + content);
    }

    private final CharSequence serializeElement(PatternElement element) {
        CharSequence var10000;
        if (element instanceof TextElement) {
            var10000 = ((TextElement)element).getValue();
        } else {
            if (!(element instanceof Placeable)) {
                throw (new SerializeError("Unknown element type: " + element));
            }

            var10000 = this.serializePlaceable((Placeable)element);
        }

        return var10000;
    }

    private final CharSequence serializePlaceable(Placeable placeable) {
        InsidePlaceable expr = placeable.getExpression();
        CharSequence var10000;
        if (expr instanceof Placeable) {
            var10000 = (new StringBuilder()).append('{').append(this.serializePlaceable((Placeable)expr)).append('}').toString();
        } else if (expr instanceof SelectExpression) {
            var10000 = (new StringBuilder()).append("{ ").append(this.serializeExpression((Expression)expr)).append('}').toString();
        } else {
            if (!(expr instanceof Expression)) {
                throw (new SerializeError("Unknown placeable type"));
            }

            var10000 = (new StringBuilder()).append("{ ").append(this.serializeExpression((Expression)expr)).append(" }").toString();
        }

        return var10000;
    }

    private final CharSequence serializeExpression(Expression expr) {
        CharSequence var10000;
        if (expr instanceof StringLiteral) {
            var10000 = '"' + ((StringLiteral)expr).getValue() + '"';
        } else if (expr instanceof NumberLiteral) {
            var10000 = ((NumberLiteral)expr).getValue();
        } else if (expr instanceof VariableReference) {
            var10000 = '$' + ((VariableReference)expr).getId().getName();
        } else {
            StringBuilder builder;
            Identifier var4;
            boolean $i$f$forEach;
            boolean var6;
            boolean var8;
            Identifier var14;
            if (expr instanceof TermReference) {
                builder = new StringBuilder();
                StringsKt.append(builder, new String[] {"-", ((TermReference)expr).getId().getName()});
                var14 = ((TermReference)expr).getAttribute();
                if (var14 != null) {
                    var4 = var14;
                    $i$f$forEach = false;
                    var6 = false;
                    var8 = false;
                    StringsKt.append(builder, new String[] {".", var4.getName()});
                }

                CallArguments var15 = ((TermReference)expr).getArguments();
                if (var15 != null) {
                    CallArguments var10 = var15;
                    $i$f$forEach = false;
                    var6 = false;
                    var8 = false;
                    builder.append(this.serializeCallArguments(var10));
                }

                var10000 = builder;
            } else if (expr instanceof MessageReference) {
                builder = new StringBuilder();
                builder.append(((MessageReference)expr).getId().getName());
                var14 = ((MessageReference)expr).getAttribute();
                if (var14 != null) {
                    var4 = var14;
                    $i$f$forEach = false;
                    var6 = false;
                    var8 = false;
                    StringsKt.append(builder, new String[] {".", var4.getName()});
                }

                var10000 = builder;
            } else if (expr instanceof FunctionReference) {
                var10000 = (new StringBuilder()).append(((FunctionReference)expr).getId().getName())
                        .append(this.serializeCallArguments(((FunctionReference)expr).getArguments()))
                        .toString();
            } else {
                if (!(expr instanceof SelectExpression)) {
                    throw (new SerializeError("Unknown expression type: " + expr));
                }

                builder = new StringBuilder();
                StringsKt.append(builder, new Object[] {this.serializeExpression(((SelectExpression)expr).getSelector()), " ->"});
                Iterable $this$forEach$iv = ((SelectExpression)expr).getVariants();
                $i$f$forEach = false;
                Iterator var12 = $this$forEach$iv.iterator();

                while (var12.hasNext()) {
                    Object element$iv = var12.next();
                    Variant it = (Variant)element$iv;
                    int var9 = false;
                    builder.append(this.serializeVariant(it));
                }

                StringBuilder var16 = builder.append("\n");
                Intrinsics.checkExpressionValueIsNotNull(var16, "builder.append(\"\\n\")");
                var10000 = var16;
            }
        }

        return var10000;
    }

    private final CharSequence serializeVariant(Variant variant) {
        String key = this.serializeVariantKey(variant.getKey());
        String value = SerializerKt.access$indentExceptFirstLine(this.serializePattern(variant.getValue()));
        return variant.getDefault() ? (CharSequence)("\n   *[" + key + ']' + value) : (CharSequence)("\n    [" + key + ']' + value);
    }

    private final CharSequence serializeCallArguments(CallArguments expr) {
        String positional = CollectionsKt.joinToString$default((Iterable)expr.getPositional(),
                (CharSequence)", ",
                (CharSequence)null,
                (CharSequence)null,
                0,
                (CharSequence)null,
                (Function1)(new Function1((FluentSerializer)this) {

                    public final CharSequence invoke(Expression p1) {
                        Objects.requireNonNull(p1, "p1");
                        return ((FluentSerializer)this.receiver).serializeExpression(p1);
                    }

                    public final KDeclarationContainer getOwner() {
                        return Reflection.getOrCreateKotlinClass(FluentSerializer.class);
                    }

                    public final String getName() {
                        return "serializeExpression";
                    }

                    public final String getSignature() {
                        return "serializeExpression(Lorg/projectfluent/syntax/ast/Expression;)Ljava/lang/CharSequence;";
                    }
                }),
                30,
                (Object)null);
        String named = CollectionsKt.joinToString$default((Iterable)expr.getNamed(),
                (CharSequence)", ",
                (CharSequence)null,
                (CharSequence)null,
                0,
                (CharSequence)null,
                (Function1)(new Function1((FluentSerializer)this) {

                    public final String invoke(NamedArgument p1) {
                        Objects.requireNonNull(p1, "p1");
                        return ((FluentSerializer)this.receiver).serializeNamedArgument(p1);
                    }

                    public final KDeclarationContainer getOwner() {
                        return Reflection.getOrCreateKotlinClass(FluentSerializer.class);
                    }

                    public final String getName() {
                        return "serializeNamedArgument";
                    }

                    public final String getSignature() {
                        return "serializeNamedArgument(Lorg/projectfluent/syntax/ast/NamedArgument;)Ljava/lang/String;";
                    }
                }),
                30,
                (Object)null);
        boolean hasPositional = expr.getPositional().size() > 0;
        boolean hasNamed = expr.getNamed().size() > 0;
        return hasPositional && hasNamed ? (CharSequence)('(' + positional + ", " + named + ')')
                : (CharSequence)(hasPositional ? '(' + positional + ')' : (hasNamed ? '(' + named + ')' : "()"));
    }

    private final String serializeNamedArgument(NamedArgument arg) {
        return (new StringBuilder()).append(arg.getName().getName()).append(": ").append(this.serializeExpression(arg.getValue())).toString();
    }

    private final String serializeVariantKey(VariantKey key) {
        String var10000;
        if (key instanceof Identifier) {
            var10000 = ((Identifier)key).getName();
        } else {
            if (!(key instanceof NumberLiteral)) {
                throw (new SerializeError("Unknown variant key type: " + key));
            }

            var10000 = ((NumberLiteral)key).getValue();
        }

        return var10000;
    }

    public FluentSerializer(boolean withJunk) {
        this.withJunk = withJunk;
    }

    // $FF: synthetic method
    public FluentSerializer(boolean var1, int var2, DefaultConstructorMarker var3) {
        if ((var2 & 1) != 0) {
            var1 = false;
        }

        this(var1);
    }

    public FluentSerializer() {
        this(false, 1, (DefaultConstructorMarker)null);
    }
}
