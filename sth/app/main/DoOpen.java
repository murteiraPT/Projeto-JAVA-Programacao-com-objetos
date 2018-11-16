package sth.app.main;

import java.io.FileNotFoundException;
import java.io.IOException;
import sth.core.exception.ImportFileException;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import sth.core.SchoolManager;

//FIXME import other classes if needed

/**
 * 4.1.1. Open existing document.
 */
public class DoOpen extends Command<SchoolManager> {

  Input<String> _nameFile;
  
  /**
   * @param receiver
   */
  public DoOpen(SchoolManager receiver) {
    super(Label.OPEN, receiver);
    _nameFile = _form.addStringInput(Message.openFile());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws DialogException {
    try {
      _receiver.importFile(_nameFile.value());
      
    }catch (ImportFileException fnfe) {
      _display.popup(Message.fileNotFound());
    }
  }

}
