/*
Method
IntConsumer accept
IntConsumer andThen

Similar operations are available for LongConsumer and DoubleConsumer
 */

package _011_FunctionalInterface;

import java.util.function.IntConsumer;

public class _006_IntConsumer {
	public static void main(String[] args){
		//basic
		IntConsumer fn1 = x-> System.out.println(x+1);
		fn1.accept(3);
		
		//andThen()
		//Returns a composed IntConsumer that performs, in sequence, this operation followed by the after operation. If performing either 
		//operation throws an exception, it is relayed to the caller of the composed operation. If performing this operation throws an exception, 
		//the after operation will not be performed.
		//default IntConsumer andThen(IntConsumer after)
		IntConsumer fn2 = x-> System.out.println(x+1);
		IntConsumer fn3 = x-> System.out.println(x+2);
		fn2.andThen(fn3).accept(4);
	}

}
