package sth.core;

public class Person {

	private int _id;
	private String _name;
	private int _phoneNumber;

	public Person(int nextPersonID, int phone, String name){
		_id = nextPersonID;
		_name = name;
		_phoneNumber = phone;
	}

	int getId(){
		return _id;
	}
	
	void setPhone(int number) {
		_phoneNumber = number;
	}
	int getPhone(){
		return _phoneNumber;
	}
	
	String getName() {
		return _name;
	}
	
	/*
	@Override
	public String toString(){
		return getPersonType() + '|' + _id + '|' + getPhone() + '|' + getName();
	}*/

}


	