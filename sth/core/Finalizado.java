package sth.core;

public class Finalizado extends Condition {

  private static Finalizado instancia;

  protected Finalizado() {}

  public static Finalizado instancia() {
    if (instancia == null)
      instancia = new Finalizado();

    return instancia;
  }
  
  public String toString() {
	  return "Finalizado";
  }

@Override
public void open(Survey s) {
	// TODO Auto-generated method stub
	
}

@Override
public void close(Survey s) {
	// TODO Auto-generated method stub
	
}

@Override
public void finalize(Survey s) {
	// TODO Auto-generated method stub
	
}
}