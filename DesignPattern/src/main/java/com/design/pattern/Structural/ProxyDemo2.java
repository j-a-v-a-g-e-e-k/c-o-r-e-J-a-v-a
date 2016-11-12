package com.design.pattern.Structural;

// subject base
interface Image{
	public void showImage();
}

// real subject
class RealImage implements Image{
	String fileName;
	RealImage(String fileName){
		this.fileName = fileName;
	}
	public void showImage(){
		System.out.println("Show image: " + fileName);
	}
}

// proxy
class ProxyImage implements Image{
	RealImage img;
	String fileName;
	ProxyImage(String fileName){
		this.fileName = fileName;
	}
	public void showImage(){
		if (img == null){
			img = new RealImage(fileName);
		}
		img.showImage();
	}
}

// client
public class ProxyDemo2 {
	public static void main(String [] args){
		Image img1 = new ProxyImage("myimage**1");
		Image img2 = new ProxyImage("myimage**2");
		img1.showImage();
		img2.showImage();
	}
}
