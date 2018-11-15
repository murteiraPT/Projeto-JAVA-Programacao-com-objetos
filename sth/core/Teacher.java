package sth.core;

import java.util.*;
import sth.core.exception.*;

public class Teacher extends Person{

	private Set<Discipline> _disciplineSet;

	public Teacher(int id, int phone, String name){
		super(id, phone, name);
		_disciplineListTeacher = new HashSet<>();

	}
	
	void addDiscipline(Discipline d){
		_disciplineSet.add(d);
	}
	
	void createProject(String discipline, String description ) throws NoSuchDisciplineIdException{
		
		if(!_disciplineSet.contains(discipline))
			throw new NoSuchDisciplineIdException("discipline isnt exists");
		else
		{
			for(Discipline d : _disciplineSet)
			{
				if(d.getName().equals(discipline))
					d.createProject(discipline, description);
			}
		}
	}
	
	@Override
	public String toString(){
		
		String text = "DOCENTE" + '|' + getId() + '|' + getPhone() + '|' + getName() + "\n";
		
		for(Discipline d : _disciplineSet)
		{
			text += "* " + d.getCourse().getName() + " - " + d.getName(); 
		}
		
		return text;
	}

	String getStudentsOfDiscipline(String nameDiscpline) throws NoSuchDisciplineIdException
	{
		String text;
		
		List<Student> listS;

		for(Discipline d : _disciplineSet){
			if(d.getName().equals(nameDiscpline))
			{										
				listS = d.getStudentList();
				for(Student s : listS)
				{
					text += s.toString();
				}
			}
		}
		throw new NoSuchDisciplineIdException("discipline isnt exists");
	}
	
}