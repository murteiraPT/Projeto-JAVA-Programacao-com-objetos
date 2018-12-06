package sth.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class Survey implements java.io.Serializable{
	
	enum Condition{
		CRIADO, ABERTO, FECHADO, FINALIZADO;
	}
	
	private Set<Answer> _answerMap;
	private ArrayList<Student> _studentMap;
	private Project _project;
	
	
	Condition _condition;
	
	public Survey(Project project){
		this._condition = Condition.CRIADO;
		_answerMap = new HashSet<>();
		_studentMap = new ArrayList<>();
		_project = project;
	}
	
	ArrayList<Student> getStudentMap(){
		return _studentMap;
	}
	
	Set<Answer> getAnswerMap(){
		return _answerMap;
	}
	
	public void open(){
		_condition = Condition.ABERTO;
		Notification n = new Notification("Aberto", _project, _project.getDiscipline());
		_project.getDiscipline().sendAllNotification(n);
	}
	
	public void close(){
		this._condition = Condition.FECHADO;
	}
	
	public void finalize(){
		this._condition = Condition.FINALIZADO;
		Notification n = new Notification("Finalizado", _project, _project.getDiscipline());
		_project.getDiscipline().sendAllNotification(n);
	}
	
	void addAnswer(Student s, int numberHours, String comment) {
		
		if(!_studentMap.contains(s))
		{
			Answer a = new Answer(comment,numberHours);
			_answerMap.add(a);
			_studentMap.add(s);
		}
	}
	
	boolean isEmptyAnswer() {
		return _answerMap.isEmpty();
	}
	
	String getResultsForStudent() {
		String text="";
		int sum = 0;
		int number = _answerMap.size();
		
		if(_condition == Condition.FINALIZADO) {
			for(Answer a : _answerMap)
				sum += a.getHours();
			
			text += " * Número de respostas: " + number + "\n";
			text += " * Tempo médio (horas): " + sum/number + "\n";
		}
		
		return text;
	}

	Project getProject(){
		return _project;
	}
	
	int getMinTime() {
		int min = Integer.MAX_VALUE;
		
		for(Answer a : _answerMap)
		{
			if(min>a.getHours())
				min = a.getHours();
		}
		return min;
	}
	
	int getMaxTime() {
		int min = Integer.MIN_VALUE;
		
		for(Answer a : _answerMap)
		{
			if(min<a.getHours())
				min = a.getHours();
		}
		return min;
	}
	int getMediumTime() {
		int sum = 0;
		
		for(Answer a : _answerMap)
			sum += a.getHours();
		return sum/_answerMap.size();
	}
}