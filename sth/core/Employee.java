package sth.core;

public class Employee extends Person implements java.io.Serializable{

	public Employee(int numberOfPersons, String name, int phone){
		super(numberOfPersons,name,phone);
	}
	
	public String getPersonType() {
		return "FUNCIONÁRIO";
	}
	
}