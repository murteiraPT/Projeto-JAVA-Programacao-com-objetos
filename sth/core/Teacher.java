package sth.core;

import java.util.*;
import sth.core.exception.*;

public class Teacher extends Person{

	private List<Discipline> _disciplineList;

	public Teacher(int id, int phone, String name){
		super(id, phone, name);
		_disciplineList = new ArrayList<>();

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
		_disciplineList.add(d);
	}
	
	void createProject(Discipline discipline, String name, String description ) throws NoSuchDisciplineIdException, DuplicateIdProjectException{
		
		if(!_disciplineList.contains(discipline))
			throw new NoSuchDisciplineIdException("Discipline dont exists");
		
		discipline.createProject(name, description);
	}
	
	@Override
	public String toString(){
		
		
		List<Course> listCourse = new ArrayList<>();
		
		for(Discipline d : _disciplineList) {
			listCourse.add(d.getCourse());
		}
		Collections.sort(listCourse, Comparator.comparing(Course::getName));
		
		List<Discipline> listDiscipline = new ArrayList<>();
		listDiscipline.addAll(_disciplineList);
		List<Discipline> sortedDisciplineList;
		
		String text = "DOCENTE" + '|' + getId() + '|' + getPhone() + '|' + getName() + "\n";
		
		
		for(Course c : listCourse) {
			sortedDisciplineList = c.getSortedDisciplineList();
			for(Discipline d : sortedDisciplineList) {
				if(listDiscipline.contains(d))
				{
					text += "* " + c.getName() + " - " + d.getName() + "\n";
					listDiscipline.remove(d);
				}
			}
		}
		
		return text;
	}
	
	Discipline getDiscipline(String nameDisc) throws NoSuchDisciplineIdException{
		for (Discipline entry : _disciplineList) {
			Discipline value = entry;
		    
		    if(value.getName().equals(nameDisc))
		    	return value;
		}
		
		throw new NoSuchDisciplineIdException(nameDisc);
	}
	
	
	HashMap<Integer, Student> getStudentsOfDiscipline(Discipline discipline) throws NoSuchDisciplineIdException{
		if(!_disciplineList.contains(discipline))	
			throw new NoSuchDisciplineIdException("Discipline does not exists");
		return discipline.getStudentMap();
	}

	HashMap<Integer, Submission> getProjectSubmissions(Discipline discipline, String nameProject) throws NoSuchDisciplineIdException, NoSuchProjectIdException{
		if(!_disciplineList.contains(discipline))
			throw new NoSuchDisciplineIdException("Discipline does not exists");
		
		Project p;
		if((p = discipline.getProject(nameProject))==null)
			throw new NoSuchProjectIdException(discipline.getName(),nameProject);
		
		return p.getSubmissions();
	}
	
	
}