package com.bj_1;

import java.util.Comparator;

public class IncomeComparator implements Comparator<Employee>{

	@Override
	public int compare(Employee o1, Employee o2) {
		return o1.getIncome().compareTo(o2.getIncome());
	}
	

}
