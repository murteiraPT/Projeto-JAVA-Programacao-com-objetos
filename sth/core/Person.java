package sth.core;

import sth.core.exception.BadEntryException;

public class Person implements java.io.Serializable {

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
	
	void parseContext(String context, School school) throws BadEntryException {
	    throw new BadEntryException("Should not have extra context: " + context);
	  }
	/*
	@Override
	public String toString(){
		return getPersonType() + '|' + _id + '|' + getPhone() + '|' + getName();
	}*/

}


	