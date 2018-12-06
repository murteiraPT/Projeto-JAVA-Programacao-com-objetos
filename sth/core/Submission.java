package sth.core;

public class Submission implements java.io.Serializable{
	
	private int _studentId;
	private String _message;
	
	public Submission(int id, String message) {
		_studentId = id;
		_message = message;
	}
	
	public String toString() {
		return "* " + _studentId + " - " + _message + "\n";
	}
}