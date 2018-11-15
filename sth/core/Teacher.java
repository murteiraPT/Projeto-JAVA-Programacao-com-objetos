package sth.core;

import java.util.*;
import sth.core.exception.*;

public class Teacher extends Person{

	private Set<Discipline> _disciplineListTeacher; //List ou ArrayList ?

	public Teacher(int numberOfPersons, String name, int phone){
		super(numberOfPersons, name, phone);
		_disciplineListTeacher = new HashSet<>();
	}

	protected void createProject(String name, String description){

		//Como e que verifico se o nome do projeto e unico no contexto da disciplina
		Project p = new Project(name,description);
		
	}

	
	protected void addDiscipline(Discipline d){
		_disciplineListTeacher.add(d);
	}

	@Override
	public String toString(){
		return "DOCENTE" + super.getId() + '|' + super.getPhone() + '|' + super.getName();
	}
	
	protected String getProjectSubmissions(String nameDiscpline, Project proj) throws NoSuchDisciplineIdException
	{

		for(Discipline d : _disciplineListTeacher){
			if(d.getName().equals(nameDiscpline))	//Procura se existe uma disciplina (por nome) do projeto na lista das 
			{										// discplinas leccionadas por Docente
				return proj.getSubmissions();
			}
		}

		throw new NoSuchDisciplineIdException("Não existe a disciplina");
	}

	protected String getStudentsOfDiscipline(String nameDiscpline) throws NoSuchDisciplineIdException
	{
		List<Discipline> listD = new ArrayList<>();

		List<Student> listS = new ArrayList<>();

		for(Discipline d : listD){
			if(d.getName().equals(nameDiscpline))	//Procura se existe uma disciplina (por nome) do projeto na lista das 
			{										// discplinas leccionadas por Docente
				//falta a obter a lista de alunos
			}
		}

		throw new NoSuchDisciplineIdException("Não existe a disciplina");
	}


	//Nao ha mais metodos no UML do stor
	//mas vi no enunciado 4.4 que se diz que ainda falta fecharProjeto(), verResultadosDeUmProjeto().....
}