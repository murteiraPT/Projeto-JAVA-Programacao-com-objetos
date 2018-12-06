package sth.core;
import sth.core.exception.FinishingSurveyIdException;


public class Aberto extends Condition {

  private static Aberto instancia;

  protected Aberto() {}

  public static Aberto instancia() {
    if (instancia == null)
      instancia = new Aberto();

    return instancia;
  }
  
  public void close(Survey s) {
	  s.transporte(Fechado.instancia());
  }
  
  public String toString() {
	  return "Aberto";
  }

@Override
public void open(Survey s) {
	// TODO Auto-generated method stub
	
}

@Override
public void finalize(Survey s) {
	// TODO Auto-generated method stub
	
}
  
}