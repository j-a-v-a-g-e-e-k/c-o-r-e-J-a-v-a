package com.design.pattern.creational;

class User {
	private final String firstName; // required
	private final String lastName; // required
	private final int age; // optional
	private final String phone; // optional
	private final String address; // optional

	private User(UserBuilder builder) {
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		this.age = builder.age;
		this.phone = builder.phone;
		this.address = builder.address;
	}

	public String toString(){
		return this.firstName + this.lastName + " " + this.age + " " + this.phone + " " + this.address; }

	public String getFirstName() { return firstName; }
	public String getLastName() {return lastName; }
	public int getAge() {return age; }
	public String getPhone() {    return phone;}
	public String getAddress() {return address; }

	static class UserBuilder {
		private final String firstName;
		private final String lastName;
		private int age;
		private String phone;
		private String address;

		public UserBuilder(String firstName, String lastName) {
			this.firstName = firstName;
			this.lastName = lastName;
		}
		public UserBuilder age(int age) {
			this.age = age;
			return this;
		}
		public UserBuilder phone(String phone) {
			this.phone = phone;
			return this;
		}
		public UserBuilder address(String address) {
			this.address = address;
			return this;
		}
		public User build() throws Exception {
			return new User(this);    
		}
	}
}

public class BuilderDemo{
	public static void main(String args[]) throws Exception{
		User user = new User.UserBuilder("Bimal", "Jain").age(99).phone("5108945820").address("CA").build();
		System.out.println(user);
	}
}

