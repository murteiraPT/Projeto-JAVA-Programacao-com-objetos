package sth.core;

import java.util.*;

import sth.core.Survey.Condition;
import sth.core.exception.ClosingSurveyIdException;
import sth.core.exception.FinishingSurveyIdException;
import sth.core.exception.NoSurveyIdException;
import sth.core.exception.NonEmptySurveyIdException;
import sth.core.exception.OpeningSurveyIdException;
import sth.core.exception.SurveyFinishedIdException;

public class Project implements java.io.Serializable{
	private String _name;
	private String _description;
	private Discipline _discipline;
	private Boolean _isOpen;
	private Survey _survey;
	private HashMap<Student, Submission> _submissionMap;
	
	public Project(String name, String description, Discipline discipline) {
		_name = name;
		_description = description;
		_isOpen = true;
		_discipline = discipline;
	}
	
	public String getName() {
		return _name;
	}
	
	void close() {
		_isOpen = false;
	}
	
	Boolean getStatus() {
		return _isOpen;
	}
	
	Survey getSurvey() {
		return _survey;
	}

	void setDescription(String description) {
		_description = description;
	}

	void addSubmission(Student s, String message) {
		
		final Submission sub = new Submission(s.getId(),message);
		_submissionMap.put(s, sub);
	}
	
	HashMap<Student, Submission> getSubmissions(){
		return _submissionMap;
	}
	
	void addSurvey() {
		_survey = new Survey();
	}
	
	void cancelSurvey(String nameDiscipline) throws NoSurveyIdException, NonEmptySurveyIdException, SurveyFinishedIdException {
		
		if(_survey == null)
			throw new NoSurveyIdException(nameDiscipline,this.getName());
		if((_survey._condition == Condition.ABERTO)||(_survey._condition == Condition.CRIADO))
		{
			if(_survey.isEmptyAnswer())
				_survey = null;
			else
				throw new NonEmptySurveyIdException(nameDiscipline,this.getName());
		}
		else
		{
			if(_survey._condition == Condition.FECHADO)
				_survey.open();
			else
			{
				if(_survey._condition == Condition.FINALIZADO)
					throw new SurveyFinishedIdException(nameDiscipline,this.getName());	
			}
		}
	}
	
	void openSurvey(String nameDiscipline) throws NoSurveyIdException, OpeningSurveyIdException {
		
		if(_survey == null)
			throw new NoSurveyIdException(nameDiscipline,this.getName());
		
		if(((!this.getStatus())&&(_survey._condition== Condition.CRIADO)) || (_survey._condition== Condition.FECHADO))
			_survey.open();
		else
			throw new OpeningSurveyIdException(nameDiscipline,this.getName());
	}
	
	void closeSurvey(String nameDiscipline) throws NoSurveyIdException, ClosingSurveyIdException {
		
		if(_survey == null)
			throw new NoSurveyIdException(nameDiscipline,this.getName());
		
		if(_survey._condition == Condition.ABERTO)
			_survey.close();
		
		if(_survey._condition == Condition.FECHADO)
			return;
		else
			throw new ClosingSurveyIdException(nameDiscipline,this.getName());
	}
	
	void finishSurvey(String nameDiscipline) throws NoSurveyIdException, FinishingSurveyIdException {
		
		if(_survey == null)
			throw new NoSurveyIdException(nameDiscipline,this.getName());
		
		if(_survey._condition == Condition.FECHADO)
			_survey.close();
		
		if(_survey._condition == Condition.FINALIZADO)
			return;
		else
			throw new FinishingSurveyIdException(nameDiscipline,this.getName());
	}

	Discipline getDiscipline(){
		return _discipline;
	}	
}

