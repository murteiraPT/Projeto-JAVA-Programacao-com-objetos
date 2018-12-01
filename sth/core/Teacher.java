package sth.core;

import java.util.*;
import sth.core.exception.*;

public class Teacher extends Person{

	private HashMap<String, Discipline> _disciplineMap;

	public Teacher(int id, int phone, String name){
		super(id, phone, name);
		_disciplineMap = new HashMap<>();

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
		_disciplineMap.put(d.getName(), d);
	}
	
	void createProject(Discipline discipline, String name, String description ) throws NoSuchDisciplineIdException, DuplicateIdProjectException{
		
		if(!_disciplineMap.containsKey(discipline.getName()))
			throw new NoSuchDisciplineIdException("Discipline dont exists");
		
		discipline.createProject(name, description);
	}
	
	@Override
	public String toString(){
		
		/*Para ordenar a lista de disciplinas por nome*/
		List<Discipline> listOrderDiscipline = new ArrayList<>(_disciplineMap.values());
		Collections.sort(listOrderDiscipline, Comparator.comparing(Discipline::getName));
		
		
		String text = "DOCENTE" + '|' + getId() + '|' + getPhone() + '|' + getName() + "\n";
		
		for(Discipline d : listOrderDiscipline){
			text += "* " + d.getCourse().getName() + " - " + d.getName() + "\n"; 
		}
		
		return text;
	}
	
	Discipline getDiscipline(String nameDisc) throws NoSuchDisciplineIdException{
		for (Map.Entry<String, Discipline> entry : _disciplineMap.entrySet()) {
			Discipline value = entry.getValue();
		    
		    if(value.getName().equals(nameDisc))
		    	return value;
		}
		
		throw new NoSuchDisciplineIdException(nameDisc);
	}
	
	
	HashMap<String, Student> getStudentsOfDiscipline(Discipline discipline) throws NoSuchDisciplineIdException{
		if(!_disciplineMap.containsKey(discipline.getName()))	
			throw new NoSuchDisciplineIdException("Discipline does not exists");
		return discipline.getStudentMap();
	}
	
}