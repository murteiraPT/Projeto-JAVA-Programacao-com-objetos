package sth.core.exception;

/** Exception thrown when the requested person does not exist. */
public class NoSuchProjectIdException extends Exception {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 201809021324L;

  /** Person id. */
  private String _idProject;
  private String _idDiscipline;

  /**
   * @param id
   */
  public NoSuchProjectIdException(String idProject , String idDiscipline) {
	_idProject = idProject;
    _idDiscipline = idDiscipline;
  }

  /** @return id */
  public String getIdProject() {
    return _idProject;
  }
  
  public String getIdDiscipline() {
	    return _idDiscipline;
  }

}