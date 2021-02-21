package com.bj_1;

import java.util.ArrayList;
import java.util.List;

public class Employee {
	enum GENDER {MALE, FEMALE}
	private String name;	
	private GENDER sex;
	private Double income;
	
	public static List<Employee> create(){
		List<Employee> result = new ArrayList<>();
		Employee e1 = new Employee("Ginger", GENDER.FEMALE, 1000);
		Employee e2 = new Employee("Donna", GENDER.FEMALE, 2000);
		Employee e3 = new Employee("Toby",GENDER.MALE, 3000);
		Employee e4 = new Employee("Josh", GENDER.MALE, 4000);
		Employee e5 = new Employee("Margaret", GENDER.FEMALE, 2000);
		result.add(e1); result.add(e2); result.add(e3); result.add(e4); result.add(e5);
		return result;
	}

	public static boolean isMale(Employee e) {
		return e.getSex().equals(GENDER.MALE);
	}
	
	public Employee(String name, GENDER sex, double income) {
		super();
		this.name = name;
		this.sex = sex;
		this.income = income;
	}

	public Employee() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public GENDER getSex() {
		return sex;
	}

	public void setSex(GENDER sex) {
		this.sex = sex;
	}

	public Double getIncome() {
		return income;
	}

	public void setIncome(Double income) {
		this.income = income;
	}

	@Override
	public String toString() {
		return "Employee [name=" + name + ", sex=" + sex + ", income=" + income + "]";
	}

}
