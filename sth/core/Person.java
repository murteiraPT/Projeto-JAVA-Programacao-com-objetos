package sth.core;

public class Person {

	private int _id;
	private String _name;
	private int _phoneNumber;

	public Person(int nextPersonID, String name, int phone)
	{
		_id = nextPersonID;
		_name = name;
		_phoneNumber = phone;
	}

	public String getName(){
		return _name;
	}

	public int getId(){
		return _id;
	}

	public void setPhone(int phone){
		_phoneNumber=phone;
	}

	public int getPhone(){
		return _phoneNumber;
	}

}


	