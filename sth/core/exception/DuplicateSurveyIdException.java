package sth.core.exception;

/** Exception thrown when the requested person does not exist. */
public class DuplicateSurveyIdException extends Exception {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 201809021324L;

  /** Person id. */
  private String _nameDiscipline;
  private String _nameProject;

  /**
   * @param id
   */
  public DuplicateSurveyIdException(String nameDiscipline,String nameProject) {
	  _nameDiscipline = nameDiscipline;
	  _nameProject = nameProject;
  }

  /** @return id */
  public String getDiscipline() {
    return _nameDiscipline;
  }
  
  public String getProject() {
	    return _nameProject;
  }

}
