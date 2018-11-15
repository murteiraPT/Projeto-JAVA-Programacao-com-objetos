package sth.core.exception;


/** Exception thrown when the requested person does not exist. */
public class NumberOfDelegatesExceed extends Exception {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 201809021324L;
  private String _courseName;
 


  public NumberOfDelegatesExceed(String course) {
	 _courseName = course;
  }

  public String getName() {
	  return _courseName;
  }
}