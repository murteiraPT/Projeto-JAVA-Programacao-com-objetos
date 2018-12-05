package sth.core;

public class Notification{
	private String _message;
	private Project _project;
	private Discipline _discipline;

	public Notification (String m, Project p, Discipline d){
		_message = m;
		_project = p;
		_discipline = d;
	}

	public String getMessage(){
		if(_message == "Aberto")
			String s = "Pode preencher inquérito do projeto " + _project.getName() + " da disciplina " + _discipline.getName();
		else

			String s = "Resultados do inquérito do projeto " + _project.getName() + " da disciplina " + _discipline.getName();
		return s;
	}
}