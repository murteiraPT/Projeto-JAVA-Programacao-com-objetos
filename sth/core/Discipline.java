package sth.core;

import sth.core.exception.BadEntryException;
import sth.core.exception.ImportFileException;
import sth.core.exception.NoSuchPersonIdException;

import java.io.IOException;
import java.util.*;



public class Discipline {
	private String _name;
	private int _capacity;
	private Course _course;
	private HashMap<String, Project>  _projectMap;
	private HashMap<String, Student> _studentMap;
	private HashMap<String, Teacher> _teacherMap;
	
	public Discipline(String name, int c, Course course) {
		_name = name;
		_capacity = c;
		_course = course;
		_projectMap = new HashMap<>();
		_studentMap = new HashMap<>();
		_teacherMap = new HashMap<>();
	}
	
	public Discipline(String name, Course course) {
		_name = name;
		_capacity = 300;
		_course = course;
		_projectMap = new HashMap<>();
		_studentMap = new HashMap<>();
		_teacherMap = new HashMap<>();
	}
	
	public String getName() {
		return _name;
	}
	
	Project getProject(String name) {
		return _projectMap.get(name);
	}
	
	public Course getCourse() {
		return _course;
	}
	
	HashMap<String, Student> getStudentMap () {
		return _studentMap;
	} 
	
	void addTeacher(Teacher t) {
		_teacherMap.put(t.getName(), t);
		t.addDiscipline(this);
	}
	
	void enrollStudent(Student s) {
		_studentMap.put(s.getName(), s);
	}
	
	void createProject(String name, String description) {
		final Project p = new Project(name, description);
		_projectMap.put(name, p);
	}		
}