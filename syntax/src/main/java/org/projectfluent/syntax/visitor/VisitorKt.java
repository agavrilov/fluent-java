package org.projectfluent.syntax.visitor;

import java.util.Iterator;
import java.util.Objects;

import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty1;
import kotlin.reflect.KVisibility;
import kotlin.reflect.full.KClasses;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequenceScope;
import kotlin.sequences.SequencesKt;
import org.projectfluent.syntax.ast.BaseNode;

public final class VisitorKt {

   public static final Sequence childrenOf( final BaseNode node) {
      Objects.requireNonNull(node, "node");
      return SequencesKt.sequence((Function2)(new Function2((Continuation)null) {
         // $FF: renamed from: p$ kotlin.sequences.SequenceScope
         private SequenceScope field_8;
         Object L$0;
         Object L$1;
         Object L$2;
         Object L$3;
         Object L$4;
         int label;


         public final Object invokeSuspend( Object $result) {
            Object var9 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            SequenceScope $this$sequence;
            Iterable $this$forEach$iv;
            Iterator var5;
            Object element$iv;
            KProperty1 prop;
            switch (this.label) {
               case 0:
                  ResultKt.throwOnFailure($result);
                  $this$sequence = this.field_8;
                  $this$forEach$iv = (Iterable)KClasses.getMemberProperties(Reflection.getOrCreateKotlinClass(node.getClass()));
                  int $i$f$forEach = false;
                  var5 = $this$forEach$iv.iterator();
                  break;
               case 1:
                  prop = (KProperty1)this.L$4;
                  element$iv = this.L$3;
                  var5 = (Iterator)this.L$2;
                  $this$forEach$iv = (Iterable)this.L$1;
                  $this$sequence = (SequenceScope)this.L$0;
                  ResultKt.throwOnFailure($result);
                  break;
               default:
                  throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }

            Pair var10001;
            do {
               do {
                  if (!var5.hasNext()) {
                     return Unit.INSTANCE;
                  }

                  element$iv = var5.next();
                  prop = (KProperty1)element$iv;
                  int var8 = false;
               } while(prop.getVisibility() != KVisibility.PUBLIC);

               var10001 = new Pair(prop.getName(), prop.getGetter().call(new Object[]{node}));
               this.L$0 = $this$sequence;
               this.L$1 = $this$forEach$iv;
               this.L$2 = var5;
               this.L$3 = element$iv;
               this.L$4 = prop;
               this.label = 1;
            } while($this$sequence.yield(var10001, this) != var9);

            return var9;
         }


         public final Continuation create( Object value,  Continuation completion) {
            Objects.requireNonNull(completion, "completion");
            Function2 var3 = new <anonymous constructor>(completion);
            var3.field_8 = (SequenceScope)value;
            return var3;
         }

         public final Object invoke(Object var1, Object var2) {
            return ((<undefinedtype>)this.create(var1, (Continuation)var2)).invokeSuspend(Unit.INSTANCE);
         }
      }));
   }
}
