package org.projectfluent.syntax.processor;

import java.util.Objects;

import kotlin.jvm.functions.Function1;

import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref.ObjectRef;
import kotlin.sequences.SequenceScope;
import kotlin.text.MatchResult;

final class Processor$textFromLiterals$1$invokeSuspend$$inlined$forEach$lambda$1 extends Lambda implements Function1 {
   // $FF: synthetic field
   final <undefinedtype> this$0;
   // $FF: synthetic field
   final SequenceScope $this_sequence$inlined;
   // $FF: synthetic field
   final ObjectRef $lastText$inlined;

   Processor$textFromLiterals$1$invokeSuspend$$inlined$forEach$lambda$1(Object var1, SequenceScope var2, ObjectRef var3) {
      super(1);
      this.this$0 = var1;
      this.$this_sequence$inlined = var2;
      this.$lastText$inlined = var3;
   }


   public final CharSequence invoke( MatchResult m) {
      Objects.requireNonNull(m, "m");
      return Processor.access$unescape(this.this$0.this$0, m);
   }
}
