package sth.core;



public class Employee extends Person implements java.io.Serializable{

	public Employee(int id, int phone, String name){
		super(id, phone, name);
	}
	
	@Override
	public String toString(){
		
		String text = "FUNCIONARIO" + '|' + getId() + '|' + getPhone() + '|' + getName() + "\n";

		return text;
	}
	
}