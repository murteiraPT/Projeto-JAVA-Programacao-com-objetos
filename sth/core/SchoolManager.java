package sth.core;

import sth.core.exception.BadEntryException;
import sth.core.exception.ImportFileException;
import sth.core.exception.NoSuchPersonIdException;

import java.util.*;
import java.io.IOException;
import java.util.HashMap;
import java.io.FileNotFoundException;


//FIXME import other classes if needed

public class SchoolManager {

  //FIXME add object attributes if needed
  private School _school;
  private Person _loggedInUser;

  public SchoolManager() {
	  School school = new School("IST");
	  _school = school;
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
	  HashMap<Integer, Person> unsortMap = _school.getAllUsers();
	  String text ="";
	  Map<Integer, Person> treeMap = new TreeMap<Integer, Person>(
              new Comparator<Integer>() {
                  @Override
                  public int compare(Integer o1, Integer o2) {
                      return o1.compareTo(o2);
                  }
              });
	  treeMap.putAll(unsortMap);
	  for(Person p : treeMap.values()) {
		  text += p.toString();
	  }
	  return text;
  }
    
  public String setPhoneNr(int phone) {
	  
	  _loggedInUser.setPhone(phone);
	  
	  return _loggedInUser.toString();
  }
  
  public String searchPerson(String name) {
	  
	  HashMap<Integer, Person> personMap = getAllUsers();
	  String s ="";
	  
	  for (HashMap.Entry<Integer, Person> entry : personMap.entrySet()) {
		  if(entry.getValue().getName().contains(name))
			  s += entry.getValue().toString();
	  }
	  
	  return s;
  }
  
  
  public void doCreateProject(String nameDiscipline, String nameProject) {
	  HashMap<String, Course> courseMap = _school.getCourseMap();
	  for(Course c : courseMap.values()) {
		  if(c.getDiscipline(nameDiscipline)!= null) {
			  c.getDiscipline(nameDiscipline).createProject(nameProject, "");
			  break;
		  }
	  }
  }
  
  
  public void doCloseProject(String nameDiscipline, String nameProject) {
	  HashMap<String, Course> courseMap = _school.getCourseMap();
	  for(Course c : courseMap.values()) {
		  if(c.getDiscipline(nameDiscipline)!= null) {
			  c.getDiscipline(nameDiscipline).getProject(nameProject).close();
			  break;
		  }
	  }
  }
  
  public String doShowDisciplineStudents(String nameDiscipline) {
	  HashMap<String, Course> courseMap = _school.getCourseMap();
	  HashMap<String, Student> studentMap;
	  
	  String text = "";
	  
	  for(Course c : courseMap.values()) {
		  if(c.getDiscipline(nameDiscipline)!= null) {
			  studentMap = c.getDiscipline(nameDiscipline).getStudentMap();
			  
			  for(Student s : studentMap.values()) {
				  text += s.toString();
			  }
			  break;
		  }
	  }
	  
	  return text;
  }
  
  /*public void doDeliverProject(String nameDiscipline, String nameProject, String text) {
	  	  
  }
  
  public void doShowProjectSubmissions(String nameDiscipline, String nameProject) {
	  
  }*/
  
  

}
