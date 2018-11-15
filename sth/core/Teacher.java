package sth.core;

import java.util.*;
import sth.core.exception.*;

public class Teacher extends Person{

	private Set<Discipline> _disciplineListTeacher;

	public Teacher(int numberOfPersons, String name, int phone){
		super(numberOfPersons, name, phone);
		_disciplineListTeacher = new HashSet<>();
	}
	
	void addDiscipline(Discipline d){
		_disciplineListTeacher.add(d);
	}
	
	void createProject(String nameDiscipline, String nameProject) throws NoSuchDisciplineIdException{

		for(Discipline d : _disciplineListTeacher) {
			if(d.getName().equals(nameDiscipline)){
				Project p = new Project(nameProject,??Descrição??);
				//....
			}
		}
		
		throw new NoSuchDisciplineIdException("Não existe a disciplina leccionada");
	}


	@Override
	public String toString(){
		
		String text = "DOCENTE" + '|' + getId() + '|' + getPhone() + '|' + getName() + "\n";
		
		for(Discipline d : _disciplineListTeacher)
		{
			text += "* " + d.getCourse().getName() + " - " + d.getName(); 
		}
		
		return text;
	}

	String getStudentsOfDiscipline(String nameDiscpline) throws NoSuchDisciplineIdException
	{
		String text;
		
		List<Student> listS;

		for(Discipline d : _disciplineListTeacher){
			if(d.getName().equals(nameDiscpline))
			{										
				listS = d.getStudentList();
				for(Student s : listS)
				{
					text += s.toString();
				}
			}
		}
		throw new NoSuchDisciplineIdException("Não existe a disciplina leccionada");
	}
	
}