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
	private Set<Project>  _projectSet;
	private ArrayList<Student> _studentList;
	private ArrayList<Teacher> _teacherList;
	
	public Discipline(String name, int c, Course course) {
		_name = name;
		_capacity = c;
		_course = course;
		_projectSet = new HashSet<>();
		_studentList = new ArrayList<>();
		_teacherList = new ArrayList<>();
	}
	
	public Discipline(String name, Course course) {
		_name = name;
		_capacity = 300;
		_course = course;
		_projectSet = new HashSet<>();
		_studentList = new ArrayList<>();
		_teacherList = new ArrayList<>();
	}
	
	public String getName() {
		return _name;
	}
	
	public Course getCourse() {
		return _course;
	}
	
	ArrayList<Student> getStudentList () {
		return _studentList;
	} 
	
	void addTeacher(Teacher t) {
		_teacherList.add(t);
		t.addDiscipline(this);
	}
	
	void enrollStudent(Student s) {
		_studentList.add(s);
	}
	
	void createProject(String name, String description) {
		final Project p = new Project(name, description);
		_projectSet.add(p);
	}
		
}