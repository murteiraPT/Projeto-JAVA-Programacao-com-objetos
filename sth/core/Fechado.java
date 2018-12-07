package sth.core;

public class Fechado extends Condition {

  private static Fechado instancia;

  protected Fechado() {}

  public static Fechado instancia() {
    if (instancia == null)
      instancia = new Fechado();

    return instancia;
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