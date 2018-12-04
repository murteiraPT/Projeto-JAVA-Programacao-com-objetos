package sth.core;

import sth.app.exception.NoSuchDisciplineException;
import sth.core.exception.BadEntryException;
import sth.core.exception.ImportFileException;
import sth.core.exception.NoSuchDisciplineIdException;
import sth.core.exception.NoSuchPersonIdException;
import sth.core.exception.NoSuchProjectIdException;
import sth.core.exception.DuplicateIdProjectException;

import java.util.*;
import java.io.IOException;
import java.io.FileNotFoundException;


//FIXME import other classes if needed

public class SchoolManager {

  //FIXME add object attributes if needed
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
	  
	  if(newS.ExistId(idUser))
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
  
  public String doShowDisciplineStudents(String nameDiscipline) throws NoSuchDisciplineIdException, NoSuchDisciplineException{
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
  
  /*public void doDeliverProject(String nameDiscipline, String nameProject, String text) {
	  	  
  }
  
  public void doShowProjectSubmissions(String nameDiscipline, String nameProject) {
	  
  }*/
  
  

}
