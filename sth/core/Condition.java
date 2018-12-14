package sth.core;


public abstract class Condition implements java.io.Serializable{

	  public abstract void open(Survey s);
	  public abstract void close(Survey s);
	  public abstract void finalize(Survey s);
}