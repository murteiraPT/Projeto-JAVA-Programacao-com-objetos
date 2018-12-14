package sth.core;


public class Aberto extends Condition {

  private static Aberto _instancia;

  protected Aberto() {}

  public static Aberto instancia() {
    if (_instancia == null)
      _instancia = new Aberto();

    return _instancia;
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