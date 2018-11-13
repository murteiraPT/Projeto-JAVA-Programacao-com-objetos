package sth.core;

import sth.core.exception.BadEntryException;
import sth.core.exception.NoSuchPersonIdException;
import sth.core.Discipline;
import sth.core.Student;

import java.io.IOException;
import java.util.List;


public class Course implements java.io.Serializable{
	private String _name;
	private ArrayList<Discpline> _disciplineList = new ArrayList<>();
	private ArrayList<Student> _studentList = new ArrayList<>();

	public Course(String name){
		_name = name;
	}

	public String getName(){
		return _name;
	}

	protected void addDiscpline(Discipline discipline) throws BadEntryException{
		if (_disciplineList.contains(discpline))
			throw BadEntryException;
		_disciplineList.add(discpline);
	}

	protected void 
	
}