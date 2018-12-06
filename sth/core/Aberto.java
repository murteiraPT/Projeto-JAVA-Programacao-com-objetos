package sth.core;
import sth.core.exception.FinishingSurveyIdException;


public class Aberto extends Condition {

  private static Aberto instancia;

  protected Aberto() {}

  public static Aberto instancia() {
    if (this.instancia == null)
      this.instancia = new Aberto();

    return this.instancia;
  }
  
  public void close(Survey s) {
	  s.transporte(Fechado.instancia());
  }
  
  public String toString() {
	  return "Aberto";
  }
  
}