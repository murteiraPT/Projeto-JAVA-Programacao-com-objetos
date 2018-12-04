package sth.app.student;

import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import sth.app.student.Message;
import sth.core.SchoolManager;

import sth.core.exception.NoSuchProjectIdException;
import sth.core.exception.NoSuchDisciplineIdException;

/**
 * 4.5.1. Deliver project.
 */
public class DoDeliverProject extends sth.app.common.ProjectCommand {

	private Input<String> _text;

	/**
	 * @param receiver
	 */
	public DoDeliverProject(SchoolManager receiver) {
		super(Label.DELIVER_PROJECT, receiver);
	    _text = _form.addStringInput(Message.requestDeliveryMessage());
	}

	/** @see pt.tecnico.po.ui.Command#execute() */
	@Override
	public final void myExecute() throws NoSuchProjectIdException, NoSuchDisciplineIdException, DialogException {
		
		String disciplineName = _discipline.value();
		String projectName = _project.value();
		
		_receiver.doDeliverProject(disciplineName, projectName, _text.value());
	}

}
