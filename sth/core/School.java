package sth.core;

import sth.core.exception.BadEntryException;

import java.io.IOException;
import java.util.*;

/**
 * School implementation.
 */
public class School implements java.io.Serializable {

    /** Serial number for serialization. */
    private static final long serialVersionUID = 201810051538L;

    private String _name;
    private static int _nextPersonID;

    private ArrayList<Course> _courseList = new ArrayList<>();
    private HashMap<Integer, Person> _personMap = new HashMap<>();

    /**
    * @param filename
    * @throws BadEntryException
    * @throws IOException
    */

    void importFile(String filename) throws IOException, BadEntryException {
    	
    }


    public School(String name){
        _name = name;
        _nextPersonID = 100000;
    }

    Person getPerson (int id){
        return _personMap.get(id);
    }

    protected void addPerson(Person person) throws BadEntryException{
        try {
        	if (_personMap.containsKey(person.getId()))
        		throw new BadEntryException("Person already exists");
            
            _nextPersonID++;
            _personMap.put(_nextPersonID, person);
        }finally{}
    }

    protected void addCourse(Course course) throws BadEntryException{
        try {
        	if (_courseList.contains(course))
                throw new BadEntryException("Course already exists");
            _courseList.add(course);
        }finally {}
    }

    protected HashMap<Integer, Person> getAllUsers(){
        return _personMap;
    }

    protected int getNumberOfPersons(){
        return _nextPersonID;
    }
    
    ArrayList<Course> getCourseList(){
    	return _courseList;
    }
    Course parseCourse(String name) {
    	for ( Course c : _courseList){
    		if(c.getName().equals(name))
    			return c;
    	}
    	Course course = new Course(name);
    	_courseList.add(course);
    	return course;
    }
}
