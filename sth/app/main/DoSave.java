package sth.app.main;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Input;
import sth.core.SchoolManager;


/**
 * 4.1.1. Save to file under current name (if unnamed, query for name).
 */
public class DoSave extends Command<SchoolManager> {
  private Input<String> _serialFile;

  /**
   * @param receiver
   */
  public DoSave(SchoolManager receiver) {
    super(Label.SAVE, receiver);
    _serialFile = _form.addStringInput(Message.newSaveAs());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() {
    if(_receiver.getSerialFile() == null) {
    	_form.parse();
    	_receiver.setSerialFile(_serialFile.value());
    }
    
    try(ObjectOutputStream objOut = new ObjectOutputStream(new FileOutputStream(_receiver.getSerialFile()))){
    	objOut.writeObject(_receiver.getSchool());
    }
    catch(IOException e) {
    	e.printStackTrace();
    }
  }

}
