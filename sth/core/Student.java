package sth.core;

import java.util.*;
import sth.core.exception.*;

public class Student extends Person{

	private boolean _isRepresentative;
	private Set<Discipline> _listDisciplinas;  //Set e melhor que List para nao ter duplicados
	private Course _course;	 // Achei que faz sentido pois o aluno tem um curso.. 


	public Student(int numberOfPersons, String name, int phone, boolean isRepresentative){
		super(numberOfPersons,name,phone);
		_isRepresentative = isRepresentative;
		_listDisciplinas = new HashSet<>();
	}

	protected Course getCourse(){
		return _course;
	}

	protected void addDiscipline (Discipline d){
			_listDisciplinas.add(d);
	}

	protected void setRepresentative(boolean representative){
		_isRepresentative = representative;

		//falta addRepresentative ou removeRepresentative 
	}

	protected boolean isRepresentative(){
		return _isRepresentative;
	}

	
	public String toString(){
		return "ALUNO" + super.getId() + "|" + super.getPhone() + "|" + super.getName();
		//Falta imprimir a lista da disciplina..
	}

	protected void submitAnswerToSurvey(String nameDiscipline, Project proj) throws NoSuchDisciplineIdException
	{
		private int numberHours = proj.requestProjectHours();
		private String comment = proj.requestComment();


		for(Discipline d : _listDisciplinas)
		{
			if(d.getName().equals(nameDiscipline))
			{
				//...
			}

		}
		throw new NoSuchDisciplineIdException("Invalido com o nome da disciplina");


	}

	protected void submitProject(String nameDiscipline, Project proj){

		private String text = proj.requestDeliveryMessage();

		proj.addSubmission(this, text);   //addSubmission(Student s, String message)
	}





}