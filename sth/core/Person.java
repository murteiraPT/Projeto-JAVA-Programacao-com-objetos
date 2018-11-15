package sth.core;

public class Person {

	private int _id;
	private String _name;
	private int _phoneNumber;

	public Person(int numberOfPersons, String name, int phone)
	{
		_id=100000 + numberOfPersons;
		_name=name;
		_phoneNumber=phone;
	}

	public String getName(){
		return _name;
	}

	public int getId(){
		return _id;
	}


	/*@Override
	public String toString(){
		return _id + '|' + _phoneNumber + '|' + _name;
	} Nao e necessario..*/

	public void setPhone(int phone){
		_phoneNumber=phone;
	}

	public int getPhone(){
		return _phoneNumber;
	}

}


	