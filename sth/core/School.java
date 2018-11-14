package sth.core;

import sth.core.exception.BadEntryException;
import sth.core.exception.NoSuchPersonIdException;

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

    /*DEvo implementar com arrblalalallalalallalallalalalalay list ou com dictionary*/
    private ArrayList<Course> _courseList = new ArrayList<>();
    private HashMap<Integer, Person> _personMap = new HashMap<>();

    /**
    * @param filename
    * @throws BadEntryException
    * @throws IOException
    */

    void importFile(String filename) throws IOException, BadEntryException {
        //FIXME implement text file reader
    }


    public School(String name){
        _name = name;
        _nextPersonID = 0;
    }

    /*é possivel remover pessoas????*/
    protected Person getPerson(int id) throws NoSuchPersonIdException{
        try { 
            return _personList[id]; 
        } catch (NullPointerException) {
            throw NoSuchPersonIdException;
        }  
    }

    protected void addPerson(Person person) throws BadEntryException{
        if (_personMap.containsKey(person.getId() - 100000))
            throw BadEntryException;
        
        _nextPersonID++;
        _personMap.put(_nexPerson, person);
    }

    protected void addCourse(Course course) throws BadEntryException{
        if (_courseList.contains(course))
            throw BadEntryException;
        
        _courseList.add(course);
    }

    protected HashMap<Integer, Person> getAllUsers(){
        return _personList;
    }

    protected int getNumberOfPersons(){
        return _nextPersonID;
    }

}
