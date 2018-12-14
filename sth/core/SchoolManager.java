package sth.core;

import sth.core.exception.BadEntryException;
import sth.core.exception.ClosingSurveyIdException;
import sth.core.exception.ImportFileException;
import sth.core.exception.NoSuchDisciplineIdException;
import sth.core.exception.NoSuchPersonIdException;
import sth.core.exception.NoSuchProjectIdException;
import sth.core.exception.NoSurveyIdException;
import sth.core.exception.NonEmptySurveyIdException;
import sth.core.exception.OpeningSurveyIdException;
import sth.core.exception.SurveyFinishedIdException;
import sth.core.exception.DuplicateIdProjectException;
import sth.core.exception.DuplicateSurveyIdException;
import sth.core.exception.FinishingSurveyIdException;

import java.util.*;
import java.util.HashMap;
import java.io.IOException;



public class SchoolManager {

  private School _school;
  private Person _loggedInUser;
  private String _serialFile;

  public SchoolManager() {
	  _school = new School("IST");
  }
  
  public String getSerialFile() {
	  return _serialFile;
  }
  
  public void setSerialFile(String serial) {
	  _serialFile = serial;
  }
  
  public School getSchool() {
	  return _school;
  }
  
  /**
   * @param datafile
   * @throws ImportFileException
   * @throws InvalidCourseSelectionException
   */
  public void importFile(String datafile) throws ImportFileException {
    try {
      _school.importFile(datafile);
    } catch (IOException | BadEntryException e) {
      throw new ImportFileException(e);
    }
  }
  
  public void updateSchool(School newS) throws NoSuchPersonIdException{
	  int idUser = _loggedInUser.getId();
	  
	  if(newS.getAllUsers().containsKey(idUser))
	  {
		  _school = newS;
		  login(idUser);
	  }
	  else
		  throw new NoSuchPersonIdException(idUser);
  }
  

  /**
   * Do the login of the user with the given identifier.

   * @param id identifier of the user to login
   * @throws NoSuchPersonIdException if there is no users with the given identifier
   */

  public void login(int id) throws NoSuchPersonIdException {
    if (_school.getPerson(id)==null)
    	throw new NoSuchPersonIdException(id);
    _loggedInUser = _school.getPerson(id);

  }

  /**
   * @return true when the currently logged in person is an administrative
   */
  public boolean isLoggedUserAdministrative() {
	  return _loggedInUser instanceof Employee;
  }

  /**
   * @return true when the currently logged in person is a professor
   */
  public boolean isLoggedUserProfessor() {
	  return _loggedInUser instanceof Teacher;
  }

  /**
   * @return true when the currently logged in person is a student
   */
  public boolean isLoggedUserStudent() {
	  return _loggedInUser instanceof Student;
  }

  /**
   * @return true when the currently logged in person is a representative
   */  
  
  public boolean isLoggedUserRepresentative() {
	  if(!isLoggedUserStudent())
		  return false;
	  Student s = (Student) _loggedInUser;
	  return s.isRepresentative();
  }
  
  public Person newUser(String name, int phoneNumber){
    int numberOfUsers = _school.getNumberOfPersons();
    Person person = new Person(numberOfUsers,phoneNumber, name);
    return person;
  }
  
  public HashMap<Integer, Person> getAllUsers(){
	  return _school.getAllUsers();
  }
  
  //4.3 abaixo
  
  public String showUser() {
	  return _loggedInUser.toString();
  }
  
  public String showAllUsers() {
	  List<Person> listPersons = new ArrayList<>();
	  
	  listPersons.addAll(_school.getAllUsers().values());
	  Collections.sort(listPersons, Comparator.comparing(Person::getId));
	  
	  String text ="";
	  
	  for(Person p : listPersons) {
		  text += p.toString();
	  }
	  return text;
  }

  public String showAllNotifications(){
  	String t ="";
  	if (_loggedInUser.hasNotifications()) {
  		for(Notification n : _loggedInUser.getNotificationList())
  			t += n.getMessage();
  		_loggedInUser.clearNotifications();
  	}
  	return t;
  }
    
  public String setPhoneNr(int phone) {
	  
	  _loggedInUser.setPhone(phone);
	  
	  return _loggedInUser.toString();
  }
  
  public String searchPerson(String name) {
	  
	  List<Person> personMap = new ArrayList<>(_school.getAllUsers().values());
	  String s ="";
	  
	  Collections.sort(personMap, Comparator.comparing(Person::getName));
	  
	  for (Person entry : personMap) {
		  if(entry.getName().contains(name))
			  s += entry.toString();
	  }
	  
	  return s;
  }
  
  
  public void doCreateProject(String nameDiscipline, String nameProject) throws NoSuchDisciplineIdException, DuplicateIdProjectException{
	  if(isLoggedUserProfessor())
	  {
		 ((Teacher) _loggedInUser).getDiscipline(nameDiscipline).createProject(nameProject, "");
	  }
  }
  
  
  public void doCloseProject(String nameDiscipline, String nameProject) throws NoSuchDisciplineIdException, NoSuchProjectIdException {
	  if(isLoggedUserProfessor())
	  {
		 ((Teacher) _loggedInUser).getDiscipline(nameDiscipline).closeProject(nameProject);
	  }
  }	
  
  public String doShowDisciplineStudents(String nameDiscipline) throws NoSuchDisciplineIdException{
	  HashMap<String, Course> courseMap = _school.getCourseMap();
	  HashMap<Integer, Student> studentMap;
	  
	  String text = "";
	  
	  if(((Teacher)_loggedInUser).getDiscipline(nameDiscipline)!=null)
	  {
		  for(Course c : courseMap.values()) {
			  if(c.getDiscipline(nameDiscipline)!= null) {
				  studentMap = c.getDiscipline(nameDiscipline).getStudentMap();
				
			      TreeMap<Integer, Student> sorted = new TreeMap<>(); 
			  
			      sorted.putAll(studentMap); 
				  
				  for(Student s : sorted.values()) {
					  text += s.toString();
				  }
				  break;
			  }
		  }
	  }
	  
	  
	  return text;
  }
  
  public void doDeliverProject(String nameDiscipline, String nameProject, String text) throws NoSuchDisciplineIdException, NoSuchProjectIdException {
	  if(isLoggedUserStudent())
		 ((Student) _loggedInUser).submitProject(nameDiscipline, nameProject, text);
  }
  
  public String doShowProjectSubmissions(String nameDiscipline, String nameProject) throws NoSuchDisciplineIdException, NoSuchProjectIdException{
	  
	  String text = "" + nameDiscipline + " - " + nameProject + "\n";
	  Discipline discipline;
	  HashMap<Integer,Submission> listSubmissions;
	  
	  if((discipline = ((Teacher)_loggedInUser).getDiscipline(nameDiscipline))!=null)
	  {
		  listSubmissions = ((Teacher) _loggedInUser).getProjectSubmissions(discipline, nameProject);
		  
		  TreeMap<Integer, Submission> sorted = new TreeMap<>(); 
		  
	      sorted.putAll(listSubmissions); 
		  
		  for(Submission s : sorted.values())
			  text += s.toString();
		  
		  return text;
	  }
	  else
		  throw new NoSuchDisciplineIdException(nameDiscipline);
  }
  
  public void doCreateSurvey(String nameDiscipline, String nameProject) throws NoSuchDisciplineIdException, NoSuchProjectIdException, DuplicateSurveyIdException {
	  if(isLoggedUserRepresentative())
		 ((Student) _loggedInUser).createSurvey(nameDiscipline, nameProject);
  }
  
  public void doCancelSurvey(String nameDiscipline, String nameProject) throws NoSuchDisciplineIdException, NoSuchProjectIdException,
  																NoSurveyIdException, NonEmptySurveyIdException, SurveyFinishedIdException {
	  if(isLoggedUserRepresentative())
		 ((Student) _loggedInUser).cancelSurvey(nameDiscipline, nameProject);
  }
  
  public void doOpenSurvey(String nameDiscipline, String nameProject) throws NoSuchDisciplineIdException, NoSuchProjectIdException,
  																				NoSurveyIdException, OpeningSurveyIdException {
	  
	  if(isLoggedUserRepresentative())
		 ((Student) _loggedInUser).openSurvey(nameDiscipline, nameProject);
	  	  
  }
  
  public void doCloseSurvey(String nameDiscipline, String nameProject) throws NoSuchDisciplineIdException, NoSuchProjectIdException,
																					NoSurveyIdException, ClosingSurveyIdException {
	if(isLoggedUserRepresentative())
		((Student) _loggedInUser).closeSurvey(nameDiscipline, nameProject);
  }
  
  public void doFinishSurvey(String nameDiscipline, String nameProject) throws NoSuchDisciplineIdException, NoSuchProjectIdException,
																					NoSurveyIdException, FinishingSurveyIdException {
	if(isLoggedUserRepresentative())
		((Student) _loggedInUser).finishSurvey(nameDiscipline, nameProject);
  }
  
  public String doShowSurveysResultsTeacher(String nameDiscipline, String nameProject) throws NoSuchDisciplineIdException, NoSuchProjectIdException, NoSurveyIdException {
	  HashMap<Integer,Submission> submissionMap;
	  Set<Answer> answerMap;
	  Discipline discipline;
	  Project project;
	  String text = "";
	  
	  if((discipline = ((Teacher)_loggedInUser).getDiscipline(nameDiscipline))!=null)
	  {
		  submissionMap = ((Teacher)_loggedInUser).getProjectSubmissions(discipline, nameProject);
		  project = discipline.getProject(nameProject);
		  
		  if(project.getSurvey()==null)
			  throw new NoSurveyIdException(nameDiscipline,nameProject);
		  
		  text += nameDiscipline + " - " + nameProject;
		  
		  if(project.toStringSurveyState().equals("Criado"))
			  text += " (por abrir)\n";
		  if(project.toStringSurveyState().equals("Aberto"))
			  text += " (aberto)\n";
		  if(project.toStringSurveyState().equals("Fechado"))
			  text += " (fechado)\n";
		  if(project.toStringSurveyState().equals("Finalizado"))
		  {
			  answerMap = project.getAnswerMap();
			  text += "\n * Número de submissões: " + submissionMap.size();
			  text += "\n * Número de respostas: " + answerMap.size();
			  text += "\n * Tempos de resolução (horas) (mínimo, médio, máximo): " + project.getSurvey().getMinTime() + ", " + project.getSurvey().getMediumTime() + ", " + project.getSurvey().getMaxTime() + "\n";
		  }
	  }
	  
	  return text;
  }
  public String doShowSurveysResultsStudent(String nameDiscipline, String nameProject) throws NoSuchDisciplineIdException, NoSuchProjectIdException, NoSurveyIdException {
	  Discipline discipline;
	  Project project;
	  String text = "";

	  	  
	  if((discipline = ((Student)_loggedInUser).getDiscipline(nameDiscipline))!=null)
	  {
		  project = discipline.getProject(nameProject);
		  
		  text += nameDiscipline + " - " + nameProject;
		  
		  if(project == null)
			  throw new NoSuchProjectIdException(nameDiscipline,nameProject);
		  
		  if(project.getSurvey()==null)
			  throw new NoSurveyIdException(nameDiscipline,nameProject);
		  
		  
		  if(project.toStringSurveyState().equals("Criado"))
			  text += " (por abrir)\n";
		  if(project.toStringSurveyState().equals("Aberto"))
			  text += " (aberto)\n";
		  if(project.toStringSurveyState().equals("Fechado"))
			  text += " (fechado)\n";
		  if(project.toStringSurveyState().equals("Finalizado"))
		  {
			  text += "\n * Número de respostas: " + project.getAnswerMap().size();
			  text += "\n * Tempo médio (horas): " + project.getSurvey().getMediumTime() + "\n";
		  }
	  }
	  
	  return text;
  }
  
  public String doShowSurveysDiscipline(String nameDiscipline) throws NoSuchDisciplineIdException{
	  String text = "";
	  Discipline discipline;
	  Course course;
	  HashMap<String, Project> projectMap;
	  
	  course = ((Student) _loggedInUser).getCourse();	  
	  discipline = course.getDiscipline(nameDiscipline);
	  
	  
	  if(discipline!= null){
			  
		  projectMap = discipline.getProjectMap();
		  
		  for(Project p : projectMap.values())
		  {
			  if(p.getSurvey()!=null)
			  {
				  text += nameDiscipline + " - " + p.getName();
				  
				  if(p.toStringSurveyState().equals("Criado"))
					  text += " (por abrir)\n";
				  
				  if(p.toStringSurveyState().equals("Aberto"))
					  text += " (aberto)\n";
				  
				  if(p.toStringSurveyState().equals("Fechado"))
					  text += " (fechado)\n";
				  
				  if(p.toStringSurveyState().equals("Finalizado"))
					  text += " - " + p.getAnswerMap().size() + " respostas - " + p.getSurvey().getMediumTime() + " horas\n";
			  }
		  } 
	}
	else
		throw new NoSuchDisciplineIdException(nameDiscipline);
	  
	return text;
  }
  
  public void doAnswerSurvey(String nameDiscipline,String nameProject, int hours, String comment) throws NoSuchDisciplineIdException, NoSuchProjectIdException, NoSurveyIdException {
	  Discipline discipline;
	  Project project;
	  HashMap<Integer,Submission> listSubmission;
	  if((discipline = ((Student) _loggedInUser).getDiscipline(nameDiscipline)) != null){
		  
		  project = discipline.getProject(nameProject);
		  if(project==null)
			  throw new NoSuchProjectIdException(nameDiscipline, nameProject);
		  
		  if((project.getSurvey()==null)||(!(project.toStringSurveyState().equals("Aberto"))))
			  throw new NoSurveyIdException(nameDiscipline,nameProject);
		  
		  listSubmission = project.getSubmissions();
		  if(!(listSubmission.containsKey(_loggedInUser.getId())))
			  throw new NoSuchProjectIdException(nameDiscipline, nameProject);
		  
		  project.getSurvey().addAnswer((Student)_loggedInUser, hours, comment);
	  }
  }
  
  
}
