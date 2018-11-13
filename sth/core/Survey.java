package sth.core;

import sth.core.Answer;
import java.util.List;
import java.util.ArrayList;

public class Survey{

	private ArrayList<Answer> _answerList = new ArrayList<>();
	private ArrayList<Student> _studentList = new ArrayList<>();
	private enum _condition{
		CREATED,
		CLOSED,
		OPENED,
		FINALIZED 
	}

	public Survey(){
		_condition = CREATED;
	}

	protected void open(){
		_condition = OPENED;
	}

	protected void close(){
		_condition = CLOSED;
	}

	protected void finalize(){
		_condition = FINALIZED;
	}

}