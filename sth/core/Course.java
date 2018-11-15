package sth.core;

import sth.core.exception.BadEntryException;
import sth.core.exception.NumberOfDelegatesExceed;
import java.util.ArrayList;


public class Course {
	private String _name;
	private int _numberOfRepresentatives;
	private ArrayList<Discipline> _disciplineList;
	private ArrayList<Student> _studentList;

	public Course(String name){
		_numberOfRepresentatives = 0;
		_name = name;
		_disciplineList = new ArrayList<>();
		_studentList = new ArrayList<>();
	}

	public String getName(){
		return _name;
	}
	
	ArrayList<Discipline> getDisciplineList(){
		return _disciplineList;
	}

	void addDiscipline(Discipline discipline) throws BadEntryException{
		if(_disciplineList.contains(discipline))
			throw new BadEntryException("Discipline already exists");
		_disciplineList.add(discipline);
	}
	
	void addStudent(Student student) throws BadEntryException {
		if(_studentList.contains(student))
			throw new BadEntryException("Student already exists");
		_studentList.add(student);
	}
	
	void addRepresentative(Student student) throws NumberOfDelegatesExceed, BadEntryException{
		if(_numberOfRepresentatives > 7)
			throw new NumberOfDelegatesExceed(_name);
		if(!_studentList.contains(student))
			throw new BadEntryException("Student out of course");
		student.setRepresentative(true);
	}

	Discipline parseDiscipline(String name) {
    	for ( Discipline d : _disciplineList){
    		if(d.getName().equals(name))
    			return d;
    	}
    	Discipline discipline = new Discipline(name, this);
    	_disciplineList.add(discipline);
    	return discipline;
    }	
}