package sth.core;

public class Fechado extends Condition {

  private static Fechado _instancia;

  protected Fechado() {}

  public static Fechado instancia() {
    if (_instancia == null)
      _instancia = new Fechado();

    return _instancia;
  }
  
  public void finalize(Survey s) {
	  s.transporte(Finalizado.instancia());
  }
  public String toString() {
	  return "Fechado";
  }

@Override
public void open(Survey s) {
	s.transporte(Aberto.instancia());
	
}

@Override
public void close(Survey s) {
	// TODO Auto-generated method stub
	
}
}