package sth.core;

import java.util.*;
import sth.core.exception.*;

public class Student extends Person{

	private boolean _isRepresentative;
	private Set<Discipline> _disciplineSet;  
	private Course _course;

	@Override
	void parseContext(String lineContext, School school) throws BadEntryException {
	    String components[] =  lineContext.split("\\|");

	    if (components.length != 2)
	      throw new BadEntryException("Invalid line context " + lineContext);

	    if (_course == null) {
	      _course = school.parseCourse(components[0]);
	      _course.addStudent(this);
	    }

	    Discipline dis = _course.parseDiscipline(components[1]);

	    dis.enrollStudent(this);
	 }
	public Student(int id, int phone, String name, boolean isRepresentative){
		super(id, phone, name);
		_isRepresentative = isRepresentative;
		_disciplineSet = new HashSet<>();
	}

	Course getCourse(){
		return _course;
	}

	void addDiscipline (Discipline d){
		_disciplineSet.add(d);
	}

	void setRepresentative(boolean representative){
		_isRepresentative = representative;
	}

	boolean isRepresentative(){
		return _isRepresentative;
	}
	
	
	@Override
	public String toString(){
		String text;
		
		if(_isRepresentative)
			text = "DELEGADO" + '|' + getId() + '|' + getPhone() + '|' + getName() + "\n";
		else
			text = "ALUNO" + '|' + getId() + '|' + getPhone() + '|' + getName() + "\n";
		
		for(Discipline d : _disciplineSet)
		{
			text += "* "  + _course.getName() +  " - " + d.getName() + "\n"; 
		}
		
		return text;
	}
	
	/*
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
	}*/





}