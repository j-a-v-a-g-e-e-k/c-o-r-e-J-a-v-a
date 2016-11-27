/*
Supertype Instance Method References
The keyword super, which is only used in an instance context, references the overridden method.
We can use the following syntax to create a method reference that refers to the instance method in the parent type.

ClassName.super::instanceMethod

We use this::append to reference the current class while using Util.super::append to reference the method from parent class.
 */

package _010_Lambda;

import java.util.function.BiFunction;

public class _023_SupertypeInstanceMethodReference {

	public static void main(String[] argv){
		new ChildUtil();
	}
}
class ChildUtil extends ParentUtil{

	public ChildUtil(){
		BiFunction<String,String,String> strFunc = this::append; 
		String s=  strFunc.apply("bimal", "jain"); 
		System.out.println(s);

		strFunc = ChildUtil.super::append; 
		s=  strFunc.apply("meghna","jain"); 
		System.out.println(s);
		
		BiFunction<ChildUtil, String, String> ii=ChildUtil::append2;
	}

	@Override
	public String append(String s1,String s2){
		System.out.println("child append");
		return s1+s2;
	}  
	
	public String append2(String s1){
		System.out.println("child append");
		return s1;
	} 
}

class ParentUtil{
	public String append(String s1,String s2){
		System.out.println("parent append");
		return s1+s2;
	}  
}