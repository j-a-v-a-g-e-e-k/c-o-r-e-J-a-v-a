/*
JDK Proxy

JDK Proxy allows us to create proxy classes at runtime with specified interfaces. To use Proxy class, we also need an InvocationHandler. When calls 
to a proxy class are made, calls are delegated to InvocationHandler.

CGLib Proxy
CGLib allows us to create proxy classes at runtime by creating sub class of specified class using Byte code generation. CGLib proxies are used in the 
case where Proxy is to be created for those class which does not have any interfaces or have methods which are not declared in the implementing 
interface. To create CGLib based proxy, we need InvocationHandler same as JDK proxy InvocationHandler but from CGLib package.

In the cglib world we have an Enhancer class that we can use to implement a given interface with a MethodInterceptor instance. The implementation of 
the callback method looks very similar to the one in the javassist example. We just forward the method call via reflection API to the already 
existing instance of Example.

 */

package _001;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InvocationHandler;

public class _002_Cglib {
	public static void main(String[] args){
		// object of implementing class
		User user = new User();

		// create proxy using CGLIB
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(User.class);
		enhancer.setCallback(new LogHandler002(user));
		User userProxy = (User)enhancer.create();
		userProxy.createUser();
		int i = userProxy.updateUser(2);
		System.out.println(i);
	}
}

//DYNAMIC PROXY CLASS
class LogHandler002 implements InvocationHandler {
	Object target;
	
	LogHandler002(Object target){
		this.target=target;
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)throws Throwable {
		System.out.println("user logged in");
		Object result = method.invoke(target, args);
		System.out.println("user logged out");
		return result;
	}	
}
