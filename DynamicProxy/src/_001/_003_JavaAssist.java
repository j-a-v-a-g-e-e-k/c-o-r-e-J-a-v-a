/*
Here we have a ProxyFactory that wants to know for which class it should create a subclass. Then we let the ProxyFactory create a whole class that 
can be reused as many times as necessary. The MethodHandler is here analog to the InvocationHandler the one that gets called for each method 
invocation of the instance. Here again we just forward the call to an instance of Example we have created before.

 */
package _001;

import java.lang.reflect.Method;

import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;

public class _003_JavaAssist {
	public static void main(String[] args) throws InstantiationException, IllegalAccessException{
		ProxyFactory factory = new ProxyFactory();
		factory.setSuperclass(User.class);
		factory.setInterfaces(new Class[] { IUser.class });
		Class cls = factory.createClass();
		IUser instance = (IUser)cls.newInstance();		
		((javassist.util.proxy.ProxyObject)instance).setHandler(new LogHandler003(new User()));// bind your newly methodHandler to your proxy
	
		instance.createUser();
		instance.updateUser(2);
	}
	
	
}

//DYNAMIC PROXY CLASS
class LogHandler003 implements MethodHandler {
	Object target;
	LogHandler003(Object target){
		this.target = target;
	}
	//Here you can even subclass an exiting class and decide which methods you want to forward to the superclass’s implementation and which methods 
	//you want to intercept	
	@Override
    public Object invoke(Object self, Method overridden, Method proceed, Object[] args) throws Throwable {
		System.out.println("user logged in");
		Object result = overridden.invoke(target, args);
		System.out.println("user logged out");
		return result;
	}	
}

/*
CONCLUSION:
First of all we can put to record that the proxy implementations are about 10 times slower than the plain invocation of the method itself. But we 
also notice a difference between the three proxy solutions. JDK’s Proxy class is surprisingly nearly as fast as the cglib implementation. Only 
javassist pulls out with about twice the exeuction time of cglib.

Conclusion: Runtime proxies are easy to use and you have different way of doing it. JDK’s Proxy only supports proxies for interfaces whereas javassist 
and cglib allow you to subclass existing classes. The runtime behavior of a proxy is about 10 times slower than a standard method invocation. The 
three solutions also differ in terms of runtime.
*/
