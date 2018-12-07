package sth.core;

import java.util.*;

import sth.core.exception.ClosingSurveyIdException;
import sth.core.exception.DuplicateSurveyIdException;
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
	private HashMap<Integer, Submission> _submissionMap;
	
	public Project(String name, String description, Discipline discipline) {
		_name = name;
		_description = description;
		_isOpen = true;
		_discipline = discipline;
		_submissionMap = new HashMap<>();
	}
	
	public String getName() {
		return _name;
	}
	
	void close() {
		_isOpen = false;
		if(_survey!=null)
			_survey.open();
	}
	
	Boolean getStatus() {
		return _isOpen;
	}
	
	Survey getSurvey() {
		return _survey;
	}
	
	Discipline getDiscipline() {
		return _discipline;
	}

	void setDescription(String description) {
		_description = description;
	}

	void addSubmission(Student s, String message) {
		Submission sub = new Submission(s.getId(),message);
		_submissionMap.put(s.getId(), sub);
	}
	
	HashMap<Integer, Submission> getSubmissions(){
		return _submissionMap;
	}
	
	void addSurvey() throws DuplicateSurveyIdException {
		if(_survey!=null)
			throw new DuplicateSurveyIdException(_discipline.getName(),_name);
		_survey = new Survey(this);
	}
	
	void cancelSurvey(String nameDiscipline) throws NoSurveyIdException, NonEmptySurveyIdException, SurveyFinishedIdException {
		
		if(_survey == null)
			throw new NoSurveyIdException(nameDiscipline,this.getName());
				
		if((_survey.getState().equals("Aberto")||(_survey.getState().equals("Criado")))){
			if(_survey.isEmptyAnswer())
				_survey = null;
			else
				throw new NonEmptySurveyIdException(nameDiscipline,this.getName());
		}
		else
		{
			if(_survey.getState().equals("Fechado"))
				_survey.open();
			else
			{
				if(_survey.getState().equals("Finalizado"))
					throw new SurveyFinishedIdException(nameDiscipline,this.getName());	
			}
		}
	}
	
	void openSurvey(String nameDiscipline) throws NoSurveyIdException, OpeningSurveyIdException {
		
		if(_survey == null)
			throw new NoSurveyIdException(nameDiscipline,this.getName());
		
		if(_survey.getState().equals("Fechado"))
			_survey.open();
		else
			throw new OpeningSurveyIdException(nameDiscipline,this.getName());
	}
	
	void closeSurvey(String nameDiscipline) throws NoSurveyIdException, ClosingSurveyIdException {
		
		if(_survey == null)
			throw new NoSurveyIdException(nameDiscipline,this.getName());
		
		if(_survey.getState().equals("Aberto"))
			_survey.close();
		else
		{
			if(_survey.getState().equals("Fechado"))
				return;
			else
				throw new ClosingSurveyIdException(nameDiscipline,this.getName());
		}
	}
	
	void finishSurvey(String nameDiscipline) throws NoSurveyIdException, FinishingSurveyIdException {
		
		if(_survey == null)
			throw new NoSurveyIdException(nameDiscipline,this.getName());
				
		if(_survey.getState().equals("Fechado"))
			_survey.finalize();
		else
		{
			if(_survey.getState().equals("Finalizado"))
				return;
			else
				throw new FinishingSurveyIdException(nameDiscipline,this.getName());
		}
		
	}
	
	String toStringSurveyState() {
		return _survey.getState();
	}
	
	Set<Answer> getAnswerMap(){
		return _survey.getAnswerMap();
	}
	
	
}

