/*
With the introduction of the Dynamic Proxy API in Java 1.3, a huge and often overlooked improvement has been made to the Java platform. The uses for 
dynamic proxies are sometimes hard concepts to grasp. In this article, I hope to introduce you first to the Proxy design pattern and then to the 
java.lang.reflect.Proxy class and the java.lang.reflect.InvocationHandler interface, which make up the heart of Dynamic Proxy's functionality.

----------------------
Definition of a proxy:
----------------------
A proxy forces object method calls to occur indirectly through the proxy object, which acts as a surrogate or delegate for the underlying object 
being proxied. Proxy objects are usually declared so that the client objects have no indication that they have a proxy object instance.

Some common proxies are the access proxy, facades, remote proxies, and virtual proxies. An access proxy is used to enforce a security policy on 
access to a service or data-providing object. A facade is a single interface to multiple underlying objects. The remote proxy is used to mask or 
shield the client object from the fact that the underlying object is remote. A virtual proxy is used to perform lazy or just-in-time instantiation 
of the real object.

The proxy is a fundamental design pattern that is used quite often in programming. However, one of its drawbacks is the specificity or tight coupling 
of the proxy with its underlying object. 

----------------
Dynamic proxies:
----------------
In Java 1.3, Sun introduced the Dynamic Proxy API. For the dynamic proxy to work, you must first have a proxy interface. The proxy interface is the 
interface that is implemented by the proxy class. Second, you need an instance of the proxy class.

Interestingly, you can have a proxy class that implements multiple interfaces. However, there are a few restrictions on the interfaces you implement. 
It is important to keep those restrictions in mind when creating your dynamic proxy:

1.) The proxy interface must be an interface. In other words, it cannot be a class (or an abstract class) or a primitive.
2.) The array of interfaces passed to the proxy constructor must not contain duplicates of the same interface. Sun specifies that, and it makes sense 
that you wouldn't be trying to implement the same interface twice at the same time. For example, an array { IPerson.class, IPerson.class } would be 
illegal, but the code { IPerson.class, IEmployee.class } would not. The code calling the constructor should check for that case and filter out 
duplicates.
3.) All the interfaces must be visible to the ClassLoader specified during the construction call. Again, that makes sense. The ClassLoader must be able 
to load the interfaces for the proxy.
4.) All the nonpublic interfaces must be from the same package. You cannot have a private interface from package com.xyz and the proxy class in package 
com.abc. If you think about it, it is the same way when programming a regular Java class. You couldn't implement a nonpublic interface from another 
package with a regular class either.
5.) The proxy interfaces cannot have a conflict of methods. You can't have two methods that take the same parameters but return different types. For 
example, the methods public void foo() and public String foo() cannot be defined in the same class because they have the same signature, but return 
different types (see The Java Language Specification). Again, that is the same for a regular class.
6.) The resulting proxy class cannot exceed the limits of the VM, such as the limitation on the number of interfaces that can be implemented.
 
 */

package _001;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class _001_JdkProxy {
	public static void main(String[] args){
		//check #3 above for using _001_JdkProxy.class.getClassLoader() here
		IUser userProxy = (IUser)Proxy.newProxyInstance(_001_JdkProxy.class.getClassLoader(), new Class[]{IUser.class}, new LogHandler(new User()));
		
		userProxy.createUser();
		int i = userProxy.updateUser(2);
		System.out.println(i);
	}
}

//DYNAMIC PROXY CLASS
class LogHandler implements InvocationHandler{
	Object target;
	
	LogHandler(Object target){
		this.target=target;
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("user logged in");
		Object result = method.invoke(target, args); // target=User object, args=method argument, like 2 incase of updateUser()
		System.out.println("user logged out");
		return result;
	}	
}

interface IUser{
	public void createUser();
	public int updateUser(int i);
}

class User implements IUser{
	@Override
	public void createUser() {
		System.out.println("creating user");		
	}
	@Override
	public int updateUser(int i) {
		System.out.println("updating user");
		return 100*i;
	}	
}
