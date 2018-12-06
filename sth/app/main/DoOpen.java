package sth.app.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import sth.core.exception.ImportFileException;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;

import sth.core.SchoolManager;
import sth.core.exception.NoSuchPersonIdException;
import sth.app.exception.NoSuchPersonException;
import sth.core.School;


//FIXME import other classes if needed

/**
 * 4.1.1. Open existing document.
 */
public class DoOpen extends Command<SchoolManager> {

  private Input<String> _nameFile;
  
  /**
   * @param receiver
   */
  public DoOpen(SchoolManager receiver) {
    super(Label.OPEN, receiver);
    _nameFile = _form.addStringInput(Message.openFile());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws DialogException, NoSuchPersonException {
	  _form.parse();
	  String _serial = _nameFile.value();
    try (ObjectInputStream obj = new ObjectInputStream(new FileInputStream(_serial)))
    {
    	_receiver.setSerialFile(_serial);
    	_receiver.updateSchool( (School) obj.readObject() );
    	String text = _receiver.showAllNotifications();
        if(text != "") {
          _display.add(text);
          _display.display();
        }
    }
    catch (FileNotFoundException fnfe) {
		_display.popup(Message.fileNotFound());
	} 
	catch (ClassNotFoundException | IOException e) {
		e.printStackTrace();
	}
	catch (NoSuchPersonIdException np) {
		throw new NoSuchPersonException(np.getId());
	}
    
  }

}
