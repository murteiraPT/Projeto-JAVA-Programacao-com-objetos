package sth.core;

import sth.core.exception.BadEntryException;
import sth.core.exception.NoSuchPersonIdException;

import java.io.IOException;
import java.util.*;

/**
 * School implementation.
 */
/**
 * @author Antonio Murteira, Tiago Neves
 *
 */


public class School implements java.io.Serializable {

    /** Serial number for serialization. */
    private static final long serialVersionUID = 201810051538L;
    private static final int ID_START = 100000;

    private String _name;
    private static int _nextPersonID;

    private HashMap<String, Course> _courseMap ;
    private HashMap<Integer, Person> _personMap ;

    /**
    * @param filename
    * @throws BadEntryException
    * @throws IOException
    */

    void importFile(String filename) throws IOException, BadEntryException {
    	Parser p = new Parser(this);
    	p.parseFile(filename);
    }

    /**
     * @param name
     */
    public School(String name){
        _name = name;
        _nextPersonID = ID_START;
        _courseMap = new HashMap<>();
        _personMap = new HashMap<>();
    }

    /**
     * 
     * @param id
     * @return HashMap<Integer, Person>
     */
    
    Person getPerson (int id) throws NoSuchPersonIdException{
    	if(_personMap.containsKey(id))
    		return _personMap.get(id);
    	else
    		throw new NoSuchPersonIdException(id);
    }
    
    /**
     * 
     * @param name
     * @return HashMap <String,Course>
     */
    Course getCourse(String name) {
    	return _courseMap.get(name);
    }

    /**
     * @param person     * 
     */
    void addPerson(Person person) throws BadEntryException{
        if (_personMap.containsKey(person.getId()))
        	throw new BadEntryException("Person already exists");
            
        _nextPersonID++;
        _personMap.put(person.getId(), person);
    }

    /**
     * 
     * @param course
     * @throws BadEntryException
     */
    void addCourse(Course course) throws BadEntryException{
        if (_courseMap.containsKey(course.getName()))
            throw new BadEntryException("Course already exists");
            _courseMap.put(course.getName(), course);
    }

    /**
     * 
     * @return HashMap<Integer, Person>
     */
    HashMap<Integer, Person> getAllUsers(){
        return _personMap;
    }

    /**
     * @return int
     */
    int getNumberOfPersons(){
        return _nextPersonID;
    }
    
    /**
     * @return HashMap<String, Course>
     */
    HashMap<String, Course> getCourseMap(){
    	return _courseMap;
    }
    
    /**
     * @param name
     * @return Course
     */
    Course parseCourse(String name) {
    	if(_courseMap.get(name) == null) {
    		Course course = new Course(name);
        	_courseMap.put(name, course);
        	return course;
    	}
    	return _courseMap.get(name);
    }
}
