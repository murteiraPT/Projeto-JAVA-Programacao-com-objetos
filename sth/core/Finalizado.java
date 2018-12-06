package sth.core;

public class Finalizado extends Condition {

  private static Finalizado instancia;

  protected Finalizado() {}

  public static Finalizado instancia() {
    if (this.instancia == null)
      this.instancia = new Finalizado();

    return this.instancia;
  }
  
  public String toString() {
	  return "Finalizado";
  }
}