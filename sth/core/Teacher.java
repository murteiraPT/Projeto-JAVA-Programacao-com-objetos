package sth.core;

import java.util.*;
import sth.core.exception.*;

public class Teacher extends Person{

	private Set<Discipline> _disciplineListTeacher; //List ou ArrayList ?

	public Teacher(int numberOfPersons, String name, int phone){
		super(numberOfPersons, name, phone);
		_disciplineListTeacher = new HashSet<>();
	}
	
	protected void addDiscipline(Discipline d){
		_disciplineListTeacher.add(d);
	}
	
	protected void createProject(String nameDiscipline, String nameProject) throws NoSuchDisciplineIdException{

		for(Discipline d : _disciplineListTeacher) {
			if(d.getName().equals(nameDiscipline)){
				Project p = new Project(nameProject,??Descrição??);
				//....
			}
		}
		
		throw new NoSuchDisciplineIdException("Não existe a disciplina leccionada");
	}
	
	public String getPersonType() {
		return "DOCENTE";
	}


	@Override
	public String toString(){
		
		String text = super.toString() + "\n";
		
		for(Discipline d : _disciplineListTeacher)
		{
			text += /*Como e que obtenho o curso?? + */" - " + d.getName(); 
		}
		
		return text;
	}

	protected String getStudentsOfDiscipline(String nameDiscpline) throws NoSuchDisciplineIdException
	{

		List<Student> listS = new ArrayList<>();

		for(Discipline d : _disciplineListTeacher){
			if(d.getName().equals(nameDiscpline))
			{										
				//Como e que obtenho a lista de estudantes..
			}
		}
		throw new NoSuchDisciplineIdException("Não existe a disciplina leccionada");
	}
	
}