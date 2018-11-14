package sth.core;

import sth.core.exception.BadEntryException;
import sth.core.exception.ImportFileException;
import sth.core.exception.NoSuchPersonIdException;

import java.io.IOException;
import java.util.*;



public class Discipline {
	private String _name;
	private int _capacity;
	private Set<Project>  _projectSet;
	private ArrayList<Student> _studentList;
	private ArrayList<Teacher> _teacherList;
	
	public Discipline(String name, int c) {
		_name = name;
		_capacity = c;
		_projectSet = new HashSet<>();
		_studentList = new ArrayList<>();
		_teacherList = new ArrayList<>();
	}
	
	public String getName() {
		return _name;
	}
	
	void addTeacher(Teacher t) {
		_teacherList.add(t);
	}
	
	void enrollStudent(Student s) {
		_studentList.add(s);
	}
	
	void createProject(String name, String description) {
		final Project p = new Project(name, description);
		_projectSet.add(p);
	}
	
	
}