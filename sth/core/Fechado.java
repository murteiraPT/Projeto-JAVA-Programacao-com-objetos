package sth.core;

public class Fechado extends Condition {

  private static Fechado instancia;

  protected Fechado() {}

  public static Fechado instancia() {
    if (this.instancia == null)
      this.instancia = new Fechado();

    return this.instancia;
  }
  
  public void finalize(Survey s) {
	  s.transporte(Finalizado.instancia());
  }
  public String toString() {
	  return "Fecahdo";
  }
}