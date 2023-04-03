package com.nit.emp;

import java.util.Date;

public class Employee {

	protected int employeeId;
	protected String name;
	protected String address;
	protected int gender;
	protected double salary;
	protected String birthDate;
	
	
	public Employee() {
		super();
	}


	public Employee(int employeeId, String name, String address, int gender, double salary, String birthDate) {
		super();
		this.employeeId = employeeId;
		this.name = name;
		this.address = address;
		this.gender = gender;
		this.salary = salary;
		this.birthDate = birthDate;
	}


	


	public Employee(String name, String address, int gender, double salary, String birthDate) {
		super();
		this.name = name;
		this.address = address;
		this.gender = gender;
		this.salary = salary;
		this.birthDate = birthDate;
	}


	public int getEmployeeId() {
		return employeeId;
	}


	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public int getGender() {
		return gender;
	}


	public void setGender(int gender) {
		this.gender = gender;
	}


	public double getSalary() {
		return salary;
	}


	public void setSalary(double salary) {
		this.salary = salary;
	}


	public String getBirthDate() {
		return birthDate;
	}


	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}


	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", name=" + name + ", address=" + address + ", gender=" + gender
				+ ", salary=" + salary + ", birthDate=" + birthDate + "]";
	}

     
	
}
