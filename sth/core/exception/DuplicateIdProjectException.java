package sth.core.exception;

public class DuplicateIdProjectException extends Exception {
  /** Class serial number. */
    private static final long serialVersionUID = 201409301048L;

    private String _nameDiscipline;
    private String _nameProject;
    

    public DuplicateIdProjectException(String discName, String projName) {
    	_nameDiscipline = discName;
    	_nameProject = projName;
    }

    public String getNameDisc() {
      return _nameDiscipline;
    }

    public String getNameProj() {
      return _nameProject;
    }
}
