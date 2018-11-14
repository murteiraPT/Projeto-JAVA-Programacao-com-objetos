package sth.core;

import sth.core.exception.BadEntryException;
import sth.core.exception.NoSuchPersonIdException;
import sth.core.Student;

import java.io.IOException;
import java.util.ArrayList;


public class Course implements java.io.Serializable{
	private String _name;
	private ArrayList<Discipline> _disciplineList = new ArrayList<>();
	private ArrayList<Student> _studentList = new ArrayList<>();

	public Course(String name){
		_name = name;
	}

	public String getName(){
		return _name;
	}

	void addDiscpline(Discipline discipline) throws BadEntryException{
		
	}

	
}