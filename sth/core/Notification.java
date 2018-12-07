package sth.core;

public class Notification implements java.io.Serializable{
	private String _message;
	private Project _project;
	private Discipline _discipline;

	public Notification (String m, Project p, Discipline d){
		_message = m;
		_project = p;
		_discipline = d;
	}
	
	public String getMessage(){
		String nomep = _project.getName();
		String nomed = _discipline.getName();
		String s;
		if(_message.equals("Aberto"))
			s = "Pode preencher inquérito do projecto " + nomep + " da disciplina " + nomed + "\n";
		else
			s = "Resultados do inquérito do projecto " + nomep + " da disciplina " + nomed + "\n";
		return s;
	}
}