package com.design.pattern.Structural;

class Volt{
	int volts;
	Volt (int volts){
		this.volts = volts;
	}
	public int getVolts(){
		return volts;
	}
}

// Adaptee
class Socket{
	public Volt getVolt(){
		return new Volt(120);
	}
}

// Adapter
interface SocketAdapter{
	public Volt get120Volt();
	public Volt get60Volt();
	public Volt get12Volt();
}

//Using inheritance for adapter pattern
class SocketClassAdapterImpl extends Socket implements SocketAdapter{
	public Volt get120Volt(){
		return getVolt();
	}
	public Volt get60Volt(){
		Volt volt = getVolt();
		return new Volt(volt.getVolts()/2);
	}
	public Volt get12Volt(){
		Volt volt = getVolt();
		return new Volt(volt.getVolts()/10);
	}
}

//Using Composition for adapter pattern
class SocketObjectAdapterImpl implements SocketAdapter{
	Socket socket = new Socket();
	public Volt get120Volt(){
		return socket.getVolt();
	}
	public Volt get60Volt(){
		Volt volt = socket.getVolt();
		return new Volt(volt.getVolts()/2);
	}
	public Volt get12Volt(){
		Volt volt = socket.getVolt();
		return new Volt(volt.getVolts()/10);
	}
}

//client
public class AdapterDemo {
	public static void main(String [] args){
		SocketAdapter socketAdapter = new SocketClassAdapterImpl();
		System.out.println("Socket with 120v: " + socketAdapter.get120Volt().volts);
		System.out.println("Socket with 120v: " + socketAdapter.get60Volt().volts);
		System.out.println("Socket with 120v: " + socketAdapter.get12Volt().volts);

		socketAdapter = new SocketObjectAdapterImpl();
		System.out.println("Socket with 120v: " + socketAdapter.get120Volt().volts);
		System.out.println("Socket with 120v: " + socketAdapter.get60Volt().volts);
		System.out.println("Socket with 120v: " + socketAdapter.get12Volt().volts);
	}
}
