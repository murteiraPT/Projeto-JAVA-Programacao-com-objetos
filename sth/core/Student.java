package sth.core;

import java.util.*;
import sth.core.exception.*;

public class Student extends Person implements java.io.Serializable{

	private boolean _isRepresentative;
	private HashMap<String, Discipline> _disciplineMap;  
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
		_disciplineMap = new HashMap<>();
	}

	Course getCourse(){
		return _course;
	}

	Discipline getDiscipline(String nameDisc) throws NoSuchDisciplineIdException{
		for (Map.Entry<String, Discipline> entry : _disciplineMap.entrySet()) {
			Discipline value = entry.getValue();
		    
		    if(value.getName().equals(nameDisc))
		    	return value;
		}
		
		throw new NoSuchDisciplineIdException(nameDisc);
	}

	void addDiscipline (Discipline d){
		_disciplineMap.put(d.getName(), d);
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
		
		/*Para ordenar a lista de disciplinas por nome*/
		List<Discipline> listOrderDiscipline = new ArrayList<>(_disciplineMap.values());
		Collections.sort(listOrderDiscipline, Comparator.comparing(Discipline::getName));
		
		
		if(_isRepresentative)
			text = "DELEGADO" + '|' + getId() + '|' + getPhone() + '|' + getName() + "\n";
		else
			text = "ALUNO" + '|' + getId() + '|' + getPhone() + '|' + getName() + "\n";
		
		for(Discipline d : listOrderDiscipline)	{
			text += "* "  + _course.getName() +  " - " + d.getName() + "\n"; 
		}
		
		return text;
	}
	
	
	public void submitProject(String nameDiscipline, String nameProject, String text) throws NoSuchDisciplineIdException, NoSuchProjectIdException {
		  Discipline discipline;
		  Project project;
		  
		  if((discipline = this.getDiscipline(nameDiscipline))!=null) 
		  {
			  if(((project = discipline.getProject(nameProject)) != null)&&(project.getStatus() == true))
					  project.addSubmission(this, text);
			  else
				  throw new NoSuchProjectIdException(nameDiscipline, nameProject);  
		  }
		  else
			  throw new NoSuchDisciplineIdException(nameDiscipline);
	}
	
	//funcionalidades do delegado
	
	public void createSurvey(String nameDiscipline, String nameProject) throws NoSuchDisciplineIdException, NoSuchProjectIdException, DuplicateSurveyIdException {
		
		Discipline discipline;
		Project project;
		Boolean hasProject = false;
		
		for(Discipline d : _course.getDisciplineMap().values()) {
			for (Project p: d.getProjectMap().values()) {
				if(p.getName().equals(nameProject) && d.getName().equals(nameDiscipline) && _disciplineMap.containsKey(nameDiscipline))
					hasProject=true;
			}
		}
		
		if(hasProject==false) {
			throw new NoSuchProjectIdException(nameDiscipline, nameProject);
		}
		
		discipline = this.getDiscipline(nameDiscipline);
		
		if(((project = discipline.getProject(nameProject))==null)||(project.getStatus()==false))
			throw new NoSuchProjectIdException(nameDiscipline, nameProject);
		
		project.addSurvey();
		
	}
	
	public void cancelSurvey(String nameDiscipline, String nameProject) throws NoSuchDisciplineIdException, NoSuchProjectIdException,
																NoSurveyIdException,NonEmptySurveyIdException, SurveyFinishedIdException {
		Discipline discipline;
		Project project;
		
		if((discipline = this.getDiscipline(nameDiscipline))==null)
			throw new NoSuchDisciplineIdException(nameDiscipline);
		
		if((project = discipline.getProject(nameProject))==null)
			throw new NoSuchProjectIdException(nameDiscipline, nameProject);
		
		project.cancelSurvey(nameDiscipline);
	}
	
	public void openSurvey(String nameDiscipline, String nameProject) throws NoSuchDisciplineIdException, NoSuchProjectIdException,
																					NoSurveyIdException, OpeningSurveyIdException {
		Discipline discipline;
		Project project;
		
		if((discipline = this.getDiscipline(nameDiscipline))==null)
			throw new NoSuchDisciplineIdException(nameDiscipline);
		
		if((project = discipline.getProject(nameProject))==null)
			throw new NoSuchProjectIdException(nameDiscipline, nameProject);
		
		project.openSurvey(nameDiscipline);
	}
	
	public void closeSurvey(String nameDiscipline, String nameProject) throws NoSuchDisciplineIdException, NoSuchProjectIdException,
																					NoSurveyIdException, ClosingSurveyIdException {
		Discipline discipline;
		Project project;
		
		if((discipline = this.getDiscipline(nameDiscipline))==null)
			throw new NoSuchDisciplineIdException(nameDiscipline);
		
		if((project = discipline.getProject(nameProject))==null)
			throw new NoSuchProjectIdException(nameDiscipline, nameProject);
		
		project.closeSurvey(nameDiscipline);
	}
	
	public void finishSurvey(String nameDiscipline, String nameProject) throws NoSuchDisciplineIdException, NoSuchProjectIdException,
																					NoSurveyIdException, FinishingSurveyIdException {
		Discipline discipline;
		Project project;
		
		if((discipline = this.getDiscipline(nameDiscipline))==null)
			throw new NoSuchDisciplineIdException(nameDiscipline);
		
		if((project = discipline.getProject(nameProject))==null)
			throw new NoSuchProjectIdException(nameDiscipline, nameProject);
		
		project.finishSurvey(nameDiscipline);
	}

}