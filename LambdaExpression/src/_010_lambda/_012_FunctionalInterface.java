/* Think from FunctionalInterface side and not from lambdaExpression side
 
FunctionalInterface:
Functional Interface is an interface with just one abstract method declared in it.

public interface ActionListener extends EventListener {
    public void actionPerformed(ActionEvent event);
}

ActionListener has only one method, actionPerformed. It is a functional interface. It doesn't matter what the single method is called, the Java compiler 
will match it up to your lambda expression as long as it has a compatible method signature.
A lambda expression represents an instance of a functional interface.
The type of a lambda expression is a functional interface type.
(String str) -> str.length() takes a String parameter and returns its length.
Its type can be any functional interface type with an abstract method that takes a String as a parameter and returns an int.
The following is an example of such a functional interface:

@FunctionalInterface
interface Processor  {
    int  getStringLength(String str);
}

We can assign lambda expression to its functional interface instance.

Processor stringProcessor = (String str) -> str.length();

We cannot use the following types of methods to declare a functional interface:
Default methods
Static methods
Methods inherited from the Object class

A functional interface can redeclare the methods in the Object class. And that method is not counted as abstract method. Therefore we can declare 
another method used by lambda expression.

@FunctionalInterface Annotation:
@FunctionalInterface annotation is defined in the java.lang package. We can optionally use it to mark a functional interface.

If the annotation @FunctionalInterface is annotated on a non-functional interface or other types such as classes, a compile-time error occurs.

An interface with one abstract method is still a functional interface even we don't annotated it with @FunctionalInterface.

Generic Functional Interface:
We can use type parameters with a functional interface to create generic functional interface.

 */

package _010_lambda;

@FunctionalInterface
interface  MyInterface2<T> {
   // An  abstract method  declared in the functional interface 
   T fn1(T  o1, T  o2);

   // Re-declaration of the equals() method in the Object class
   // this interface has two abstract methods: fn1() and equals()
   boolean equals(Object  obj);
   
   //default method
   //Java 8 enables us to add non-abstract method implementations to interfaces by utilizing the default keyword. 
   //This feature is also known as Extension Methods.
   default T fn2(T  o1, T  o2){
	   return o1;
   }
   
   //static method
   static void fn3(Object obj){	   
   }
}
