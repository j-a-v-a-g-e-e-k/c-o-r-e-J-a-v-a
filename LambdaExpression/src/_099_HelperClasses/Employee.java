package _099_HelperClasses;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Employee {

	public enum Gender{
		MALE, FEMALE
	}
	private int id;
	private String name;
	private Gender gender;
	private double income;

	public Employee(int id, String name, Gender gender, double income){
		this.id=id;
		this.name=name;
		this.gender=gender;
		this.income=income;
	}

	public static List<Employee> create(){
		List<Employee> list = new ArrayList<Employee>();

		Employee Jake = new Employee(1,"Jake", Gender.MALE, 2000.0);
		Employee Jack = new Employee(2,"Jack", Gender.MALE, 2000.0);
		Employee Jane = new Employee(3,"Jane", Gender.FEMALE, 3000.0);
		Employee Jode = new Employee(4,"Jode", Gender.MALE, 4000.0);
		Employee Jeny = new Employee(5,"Jeny", Gender.FEMALE, 3000.0);

		list=Arrays.asList(Jake, Jack, Jane, Jode, Jeny);
		return list;
	}

	public boolean isMale() {
		return this.gender == Gender.MALE;
	}	
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Gender getGender() {
		return gender;
	}

	public double getIncome() {
		return income;
	}

	public String toString(){
		return getName() + "-" + getGender() + "-" + getIncome();
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public void setIncome(double income) {
		this.income = income;
	}	
	
}

