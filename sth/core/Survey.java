package sth.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Survey implements java.io.Serializable{
	
	enum Condition{
		CRIADO, ABERTO, FECHADO, FINALIZADO;
	}
	
	private HashMap<Integer,String> _answerMap;
	private ArrayList<Student> _studentMap;
	private Project _project;
	
	
	Condition _condition;
	
	public Survey(Project project){
		this._condition = Condition.CRIADO;
		_answerMap = new HashMap<>();
		_studentMap = new ArrayList<>();
		_project = project;
	}
	
	ArrayList<Student> getStudentMap(){
		return _studentMap;
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
			_answerMap.put(numberHours,comment);
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
			for(Map.Entry<Integer,String> entry : _answerMap.entrySet())
				sum += entry.getKey();
			
			text += " * Número de respostas: " + number + "\n";
			text += " * Tempo médio (horas): " + sum/number + "\n";
		}
		
		return text;
	}

	Project getProject(){
		return _project;
	}
}