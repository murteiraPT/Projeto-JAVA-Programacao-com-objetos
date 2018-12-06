package sth.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public abstract class Condition implements java.io.Serializable{

	  public abstract void open(Survey s);
	  public abstract void close(Survey s);
	  public abstract void finalize(Survey s);
}