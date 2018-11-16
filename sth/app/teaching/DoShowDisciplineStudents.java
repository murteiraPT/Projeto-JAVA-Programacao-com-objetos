package sth.app.teaching;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import sth.core.SchoolManager;
import pt.tecnico.po.ui.Form;


/**
 * 4.4.4. Show course students.
 */
public class DoShowDisciplineStudents extends Command<SchoolManager> {

	Input <String> _nameDiscipline;

	/**
	 * @param receiver
	 */
	public DoShowDisciplineStudents(SchoolManager receiver) {
		super(Label.SHOW_COURSE_STUDENTS, receiver);
		_nameDiscipline = _form.addStringInput(Message.requestDisciplineName());
	}

	/** @see pt.tecnico.po.ui.Command#execute() */
	@Override
	public final void execute() throws DialogException {
		_form.parse();
		String text = _receiver.doShowDisciplineStudents(_nameDiscipline.value());
		
		_display.add(text);
		_display.display();
	}

}
