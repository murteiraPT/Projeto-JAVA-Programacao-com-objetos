package sth.core.exception;

/** Exception thrown when the requested person does not exist. */
public class NoSuchProjectIdException extends Exception {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 201809021324L;

  /** Person id. */
  private String _id;

  /**
   * @param id
   */
  public NoSuchProjectIdException(String id) {
    _id = id;
  }

  /** @return id */
  public String getId() {
    return _id;
  }

}