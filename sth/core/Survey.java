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
	
	
	Condition _condition;
	
	public Survey(){
		this._condition = Condition.CRIADO;
		_answerMap = new HashMap<>();
		_studentMap = new ArrayList<>();
	}
	
	ArrayList<Student> getStudentMap(){
		return _studentMap;
	}
	
	public void open(){
		this._condition = Condition.ABERTO;
	}
	
	public void close(){
		this._condition = Condition.FECHADO;
	}
	
	public void finalize(){
		this._condition = Condition.FINALIZADO;
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
}