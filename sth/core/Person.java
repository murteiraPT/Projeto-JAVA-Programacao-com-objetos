package sth.core;

import sth.core.exception.BadEntryException;
import java.util.*;

public class Person implements java.io.Serializable {

	private int _id;
	private String _name;
	private int _phoneNumber;
	private boolean _hasNotifications;
	private ArrayList<Notification> _notificationsList;

	public Person(int nextPersonID, int phone, String name){
		_id = nextPersonID;
		_name = name;
		_phoneNumber = phone;
		_hasNotifications = false;
		_notificationsList = new ArrayList<>();
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

	Boolean hasNotifications(){
		return _hasNotifications;
	}

	ArrayList<Notification> getNotificationList(){
		return _notificationsList;
	}

	void receiveNotification(Notification n){
		_notificationsList.add(n);
		_hasNotifications = true;
	}

	void clearNotifications(){
		_notificationsList.clear();
		_hasNotifications = false;
	}

}


	