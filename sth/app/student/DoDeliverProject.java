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

	/*String _nameDiscipline;
	String _nameProject;
	String _text;*/

	/**
	 * @param receiver
	 */
	public DoDeliverProject(SchoolManager receiver) {
		super(Label.DELIVER_PROJECT, receiver);
	    /*_nameDiscipline = _form.addStringInput(Message.requestDisciplineName());
	    _nameProject = _form.addStringInput(Message.requestProjectName());
	    _text = _form.addStringInput(Message.requestDeliveryMessage());*/
	}

	/** @see pt.tecnico.po.ui.Command#execute() */
	@Override
	public final void myExecute() throws NoSuchProjectIdException, NoSuchDisciplineIdException, DialogException {
		//_receiver.doDeliverProject(_nameDiscipline, _nameProject, _text);
	}

}
