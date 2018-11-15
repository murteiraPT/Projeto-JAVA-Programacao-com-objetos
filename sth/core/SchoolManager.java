package sth.core;

import sth.core.exception.BadEntryException;
import sth.core.exception.ImportFileException;
import sth.core.exception.NoSuchPersonIdException;

import java.io.IOException;
import java.util.HashMap;
import java.io.FileNotFoundException;


//FIXME import other classes if needed

public class SchoolManager {

  //FIXME add object attributes if needed
  private School _school;
  private Person _loggedInUser;

  public SchoolManager(School school) {
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
	  return _loggedInUser instanceof Employee;
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
  
  public String showAllUsers() {
	  
	  String s="";
	  HashMap<Integer, Person> personMap = getAllUsers();
	  for (HashMap.Entry<Integer, Person> entry : personMap.entrySet()) {
		  s += entry.getValue().toString();
	  }
	  
	  return s;
  }
  
  public String showUser(int id) {
	  return _school.getPerson(id).toString();
  }
  
  public void setPhoneNr(int id, int phone) {
	  _school.getPerson(id).setPhone(phone);
  }

  public String searchPerson(String name) {
	  
	  HashMap<Integer, Person> personMap = getAllUsers();
	  String s ="";
	  
	  for (HashMap.Entry<Integer, Person> entry : personMap.entrySet()) {
		  if(entry.getValue().getName().equals(name))
			  s += entry.getValue().toString();
	  }
	  
	  return s;
  }
  
  public void doCreateProject(String nameDiscipline, String nameProject) {
	  
  }
  
  public void newDiscipline(String name, int cap, Course course) {
	  Discipline d = new Discipline(name, cap, course);
	  course.addDiscipline(d);
	  	  
  }

}
