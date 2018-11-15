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

	public String getName(){
		return _name;
	}

	int getId(){
		return _id;
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


	