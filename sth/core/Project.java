package sth.core;

import java.util.*;

public class Project{
	private String _name;
	private String _description;
	private Boolean _isOpen;
	
	public Project(String name, String description) {
		_name = name;
		_description = description;
		_isOpen = true;
	}
	
	public String getName() {
		return _name;
	}
	
	void close() {
		_isOpen = false;
	}
	
	Boolean getStatus() {
		return _isOpen;
	}
	
	void setDescription(String description) {
		_description = description;
	}
}

