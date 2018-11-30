package sth.app.person;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Display;
import pt.tecnico.po.ui.Input;
import sth.core.SchoolManager;

//FIXME import other classes if needed

/**
 * 4.2.2. Change phone number.
 */
public class DoChangePhoneNumber extends Command<SchoolManager> {

  Input<String> _numberPhone;
  String show;

  /**
   * @param receiver
   */
  public DoChangePhoneNumber(SchoolManager receiver) {
    super(Label.CHANGE_PHONE_NUMBER, receiver);
    _numberPhone = _form.addStringInput(Message.requestPhoneNumber());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() {
	  _form.parse();
	  show = _receiver.setPhoneNr(Integer.parseInt(_numberPhone.value()));
	  _display.add(show);
	  _display.display();
  }

}
