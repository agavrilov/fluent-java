package org.projectfluent.syntax.processor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref.ObjectRef;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequenceScope;
import kotlin.sequences.SequencesKt;
import kotlin.text.CharsKt;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import org.projectfluent.syntax.ast.InsidePlaceable;
import org.projectfluent.syntax.ast.Pattern;
import org.projectfluent.syntax.ast.PatternElement;
import org.projectfluent.syntax.ast.Placeable;
import org.projectfluent.syntax.ast.SelectExpression;
import org.projectfluent.syntax.ast.StringLiteral;
import org.projectfluent.syntax.ast.TextElement;
import org.projectfluent.syntax.ast.Variant;

public final class Processor {

    private final Regex special;

    public final Pattern unescapeLiteralsToText(Pattern pattern) {
        Intrinsics.checkParameterIsNotNull(pattern, "pattern");
        Pattern result = new Pattern(new PatternElement[0]);
        Iterator var4 = this.textFromLiterals(pattern).iterator();

        while (var4.hasNext()) {
            PatternElement elem = (PatternElement)var4.next();
            result.getElements().add(elem);
        }

        return result;
    }

    public final Pattern escapeTextToLiterals(Pattern pattern) {
        Intrinsics.checkParameterIsNotNull(pattern, "pattern");
        Pattern result = new Pattern(new PatternElement[0]);
        Iterator var4 = this.literalsFromText(pattern).iterator();

        while (var4.hasNext()) {
            PatternElement elem = (PatternElement)var4.next();
            result.getElements().add(elem);
        }

        return result;
    }

   private final Sequence textFromLiterals(final Pattern pattern) {
      return SequencesKt.sequence((Function2)(new Function2((Continuation)null) {
         // $FF: renamed from: p$ kotlin.sequences.SequenceScope
         private SequenceScope field_10;
         Object L$0;
         Object L$1;
         Object L$2;
         Object L$3;
         Object L$4;
         Object L$5;
         Object L$6;
         Object L$7;
         Object L$8;
         Object L$9;
         Object L$10;
         int label;


         public final Object invokeSuspend( Object $result) {
            Object var20 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            SequenceScope $this$sequence;
            ObjectRef lastText;
            Iterable $this$forEach$iv;
            boolean $i$f$forEach;
            Iterator var6;
            PatternElement element;
            InsidePlaceable expression;
            List processedVariants;
            SelectExpression processedSelect;
            Placeable placeable;
            TextElement itx;
            Object element$iv;
            switch (this.label) {
               case 0:
                  ResultKt.throwOnFailure($result);
                  $this$sequence = this.field_10;
                  lastText = new ObjectRef();
                  lastText.element = null;
                  $this$forEach$iv = pattern.getElements();
                  $i$f$forEach = false;
                  var6 = $this$forEach$iv.iterator();
                  break;
               case 1:
                  TextElement itxx = (TextElement)this.L$10;
                  placeable = (Placeable)this.L$9;
                  processedSelect = (SelectExpression)this.L$8;
                  processedVariants = (List)this.L$7;
                  expression = (InsidePlaceable)this.L$6;
                  element = (PatternElement)this.L$5;
                  element$iv = this.L$4;
                  var6 = (Iterator)this.L$3;
                  $this$forEach$iv = (Iterable)this.L$2;
                  lastText = (ObjectRef)this.L$1;
                  $this$sequence = (SequenceScope)this.L$0;
                  ResultKt.throwOnFailure($result);
                  lastText.element = null;
                  this.L$0 = $this$sequence;
                  this.L$1 = lastText;
                  this.L$2 = $this$forEach$iv;
                  this.L$3 = var6;
                  this.L$4 = element$iv;
                  this.L$5 = element;
                  this.L$6 = expression;
                  this.L$7 = processedVariants;
                  this.L$8 = processedSelect;
                  this.L$9 = placeable;
                  this.label = 2;
                  if ($this$sequence.yield(placeable, this) == var20) {
                     return var20;
                  }
                  break;
               case 2:
                  placeable = (Placeable)this.L$9;
                  processedSelect = (SelectExpression)this.L$8;
                  processedVariants = (List)this.L$7;
                  expression = (InsidePlaceable)this.L$6;
                  element = (PatternElement)this.L$5;
                  element$iv = this.L$4;
                  var6 = (Iterator)this.L$3;
                  $this$forEach$iv = (Iterable)this.L$2;
                  lastText = (ObjectRef)this.L$1;
                  $this$sequence = (SequenceScope)this.L$0;
                  ResultKt.throwOnFailure($result);
                  break;
               case 3:
                  itx = (TextElement)this.L$7;
                  expression = (InsidePlaceable)this.L$6;
                  element = (PatternElement)this.L$5;
                  element$iv = this.L$4;
                  var6 = (Iterator)this.L$3;
                  $this$forEach$iv = (Iterable)this.L$2;
                  lastText = (ObjectRef)this.L$1;
                  $this$sequence = (SequenceScope)this.L$0;
                  ResultKt.throwOnFailure($result);
                  lastText.element = null;
                  this.L$0 = $this$sequence;
                  this.L$1 = lastText;
                  this.L$2 = $this$forEach$iv;
                  this.L$3 = var6;
                  this.L$4 = element$iv;
                  this.L$5 = element;
                  this.L$6 = expression;
                  this.label = 4;
                  if ($this$sequence.yield(element, this) == var20) {
                     return var20;
                  }
                  break;
               case 4:
                  expression = (InsidePlaceable)this.L$6;
                  element = (PatternElement)this.L$5;
                  element$iv = this.L$4;
                  var6 = (Iterator)this.L$3;
                  $this$forEach$iv = (Iterable)this.L$2;
                  lastText = (ObjectRef)this.L$1;
                  $this$sequence = (SequenceScope)this.L$0;
                  ResultKt.throwOnFailure($result);
                  break;
               case 5:
                  TextElement it = (TextElement)this.L$2;
                  lastText = (ObjectRef)this.L$1;
                  $this$sequence = (SequenceScope)this.L$0;
                  ResultKt.throwOnFailure($result);
                  return Unit.INSTANCE;
               default:
                  throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }

            while(true) {
               TextElement var10000;
               while(var6.hasNext()) {
                  element$iv = var6.next();
                  element = (PatternElement)element$iv;
                  int var9 = false;
                  boolean var28;
                  boolean var35;
                  if (element instanceof TextElement) {
                     if ((TextElement)lastText.element == null) {
                        lastText.element = new TextElement(((TextElement)element).getValue());
                     } else {
                        var10000 = (TextElement)lastText.element;
                        if (var10000 != null) {
                           TextElement var25 = var10000;
                           boolean var29 = false;
                           var28 = false;
                           var35 = false;
                           var25.setValue(var25.getValue() + ((TextElement)element).getValue());
                        }
                     }
                  } else if (element instanceof Placeable) {
                     expression = ((Placeable)element).getExpression();
                     boolean var17;
                     boolean var33;
                     if (expression instanceof StringLiteral) {
                        Object content = ((StringLiteral)expression).getValue();
                        content = Processor.this.special.replace((CharSequence)content, (Function1)(new Processor$textFromLiterals$1$invokeSuspend$$inlined$forEach$lambda$1(this, $this$sequence, lastText)));
                        if ((TextElement)lastText.element == null) {
                           lastText.element = new TextElement("");
                        }

                        var10000 = (TextElement)lastText.element;
                        if (var10000 != null) {
                           TextElement var32 = var10000;
                           var33 = false;
                           var35 = false;
                           var17 = false;
                           var32.setValue(var32.getValue() + content);
                        }
                     } else {
                        boolean var16;
                        if (!(expression instanceof SelectExpression)) {
                           var10000 = (TextElement)lastText.element;
                           if (var10000 != null) {
                              TextElement var26 = var10000;
                              var28 = false;
                              var33 = false;
                              var16 = false;
                              this.L$0 = $this$sequence;
                              this.L$1 = lastText;
                              this.L$2 = $this$forEach$iv;
                              this.L$3 = var6;
                              this.L$4 = element$iv;
                              this.L$5 = element;
                              this.L$6 = expression;
                              this.L$7 = var26;
                              this.label = 3;
                              if ($this$sequence.yield(var26, this) == var20) {
                                 return var20;
                              }

                              lastText.element = null;
                           }

                           this.L$0 = $this$sequence;
                           this.L$1 = lastText;
                           this.L$2 = $this$forEach$iv;
                           this.L$3 = var6;
                           this.L$4 = element$iv;
                           this.L$5 = element;
                           this.L$6 = expression;
                           this.label = 4;
                           if ($this$sequence.yield(element, this) == var20) {
                              return var20;
                           }
                        } else {
                           var28 = false;
                           processedVariants = (new ArrayList());
                           Iterator var31 = ((SelectExpression)expression).getVariants().iterator();

                           while(var31.hasNext()) {
                              Variant variant = (Variant)var31.next();
                              Variant processedVariant = new Variant(variant.getKey(), Processor.this.unescapeLiteralsToText(variant.getValue()), variant.getDefault());
                              processedVariants.add(processedVariant);
                           }

                           processedSelect = new SelectExpression(((SelectExpression)expression).getSelector(), processedVariants);
                           placeable = new Placeable(processedSelect);
                           var10000 = (TextElement)lastText.element;
                           if (var10000 != null) {
                              itx = var10000;
                              var16 = false;
                              var17 = false;
                              int var19 = false;
                              this.L$0 = $this$sequence;
                              this.L$1 = lastText;
                              this.L$2 = $this$forEach$iv;
                              this.L$3 = var6;
                              this.L$4 = element$iv;
                              this.L$5 = element;
                              this.L$6 = expression;
                              this.L$7 = processedVariants;
                              this.L$8 = processedSelect;
                              this.L$9 = placeable;
                              this.L$10 = itx;
                              this.label = 1;
                              if ($this$sequence.yield(itx, this) == var20) {
                                 return var20;
                              }

                              lastText.element = null;
                           }

                           this.L$0 = $this$sequence;
                           this.L$1 = lastText;
                           this.L$2 = $this$forEach$iv;
                           this.L$3 = var6;
                           this.L$4 = element$iv;
                           this.L$5 = element;
                           this.L$6 = expression;
                           this.L$7 = processedVariants;
                           this.L$8 = processedSelect;
                           this.L$9 = placeable;
                           this.label = 2;
                           if ($this$sequence.yield(placeable, this) == var20) {
                              return var20;
                           }
                        }
                     }
                  }
               }

               var10000 = (TextElement)lastText.element;
               if (var10000 == null) {
                  break;
               }

               TextElement var21 = var10000;
               $i$f$forEach = false;
               boolean var23 = false;
               int var24 = false;
               this.L$0 = $this$sequence;
               this.L$1 = lastText;
               this.L$2 = var21;
               this.label = 5;
               if ($this$sequence.yield(var21, this) == var20) {
                  return var20;
               }
               break;
            }

            return Unit.INSTANCE;
         }


         public final Continuation create( Object value,  Continuation completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            Function2 var3 = new <anonymous constructor>(completion);
            var3.field_10 = (SequenceScope)value;
            return var3;
         }

         public final Object invoke(Object var1, Object var2) {
            return ((<undefinedtype>)this.create(var1, (Continuation)var2)).invokeSuspend(Unit.INSTANCE);
         }
      }));
   }

   private final Sequence literalsFromText(final Pattern pattern) {
      return SequencesKt.sequence((Function2)(new Function2((Continuation)null) {
         // $FF: renamed from: p$ kotlin.sequences.SequenceScope
         private SequenceScope field_9;
         Object L$0;
         Object L$1;
         Object L$2;
         Object L$3;
         Object L$4;
         Object L$5;
         Object L$6;
         Object L$7;
         Object L$8;
         int I$0;
         int I$1;
         int I$2;
         char C$0;
         int label;


         public final Object invokeSuspend( Object param1) {
            // $FF: Couldn't be decompiled
         }


         public final Continuation create( Object value,  Continuation completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            Function2 var3 = new <anonymous constructor>(completion);
            var3.field_9 = (SequenceScope)value;
            return var3;
         }

         public final Object invoke(Object var1, Object var2) {
            return ((<undefinedtype>)this.create(var1, (Continuation)var2)).invokeSuspend(Unit.INSTANCE);
         }
      }));
   }

    private final CharSequence unescape(MatchResult matchResult) {
        ListIterator matches = CollectionsKt.drop((Iterable)matchResult.getGroupValues(), 2).listIterator();
        String simple = (String)matches.next();
        if (Intrinsics.areEqual(simple, "") ^ true) {
            return simple;
        } else {
            String uni4 = (String)matches.next();
            char var7;
            int codepoint;
            String var10000;
            if (Intrinsics.areEqual(uni4, "") ^ true) {
                var7 = 1;
                boolean var8 = false;
                if (uni4 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                }

                var10000 = uni4.substring(var7);
                Intrinsics.checkExpressionValueIsNotNull(var10000, "(this as java.lang.String).substring(startIndex)");
                String var6 = var10000;
                var7 = 16;
                var8 = false;
                int codepoint = Integer.parseInt(var6, CharsKt.checkRadix(var7));
                if (Character.isBmpCodePoint(codepoint)) {
                    codepoint = (char)codepoint;
                    if (!Character.isSurrogate((char)codepoint)) {
                        return String.valueOf((char)codepoint);
                    }
                }
            }

            String uni6 = (String)matches.next();
            if (Intrinsics.areEqual(uni6, "") ^ true) {
                byte var13 = 1;
                boolean var9 = false;
                if (uni6 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                }

                var10000 = uni6.substring(var13);
                Intrinsics.checkExpressionValueIsNotNull(var10000, "(this as java.lang.String).substring(startIndex)");
                String var12 = var10000;
                var13 = 16;
                var9 = false;
                codepoint = Integer.parseInt(var12, CharsKt.checkRadix(var13));
                if (Character.isValidCodePoint(codepoint)) {
                    var7 = (char)codepoint;
                    if (!Character.isSurrogate(var7)) {
                        StringBuilder builder = new StringBuilder();
                        builder.append(Character.highSurrogate(codepoint));
                        builder.append(Character.lowSurrogate(codepoint));
                        return builder;
                    }
                }
            }

            return "�";
        }
    }

    public Processor() {
        String var1 = "\\\\(([\\\\\"])|(u[0-9a-fA-F]{4})|(U[0-90a-fA-F]{6}))";
        boolean var2 = false;
        Regex var4 = new Regex(var1);
        this.special = var4;
    }

    // $FF: synthetic method
    public static final CharSequence access$unescape(Processor $this, MatchResult matchResult) {
        return $this.unescape(matchResult);
    }
}
