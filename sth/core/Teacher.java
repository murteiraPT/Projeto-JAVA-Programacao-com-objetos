package sth.core;

import java.util.*;
import sth.core.exception.*;

public class Teacher extends Person{

	private Set<Discipline> _disciplineSet;

	public Teacher(int id, int phone, String name){
		super(id, phone, name);
		_disciplineSet = new HashSet<>();

	}
	@Override
	void parseContext(String lineContext, School school) throws BadEntryException {
		String components[] =  lineContext.split("\\|");

	    if (components.length != 2)
	      throw new BadEntryException("Invalid line context " + lineContext);

	    Course course = school.parseCourse(components[0]);
	    Discipline discipline = course.parseDiscipline(components[1]);

	    discipline.addTeacher(this);
	 }
	
	void addDiscipline(Discipline d){
		_disciplineSet.add(d);
	}
	
	void createProject(Discipline discipline, String name, String description ) throws NoSuchDisciplineIdException{
		
		if(!_disciplineSet.contains(discipline))
			throw new NoSuchDisciplineIdException("discipline isnt exists");
		discipline.createProject(name, description);
	}
	
	@Override
	public String toString(){
		
		String text = "DOCENTE" + '|' + getId() + '|' + getPhone() + '|' + getName() + "\n";
		
		for(Discipline d : _disciplineSet){
			text += "* " + d.getCourse().getName() + " - " + d.getName() + "\n"; 
		}
		
		return text;
	}

	ArrayList<Student> getStudentsOfDiscipline(Discipline discipline) throws NoSuchDisciplineIdException{
		if(!_disciplineSet.contains(discipline))	
			throw new NoSuchDisciplineIdException("Discipline does not exists");
		return discipline.getStudentList();
	}
	
}