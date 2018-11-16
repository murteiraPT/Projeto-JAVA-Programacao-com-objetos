package sth.app.teaching;

import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import sth.core.SchoolManager;

import sth.core.exception.NoSuchProjectIdException;
import sth.core.exception.NoSuchDisciplineIdException;

/**
 * 4.4.2. Close project.
 */
public class DoCloseProject extends sth.app.common.ProjectCommand {

	Input <String> _nameDiscipline;
	Input <String> _nameProject;
	
	public DoCloseProject(SchoolManager receiver) {
		super(Label.CLOSE_PROJECT, receiver);
	    _nameDiscipline = _form.addStringInput(Message.requestDisciplineName());
	    _nameProject = _form.addStringInput(Message.requestProjectName());
	}

	/** @see sth.app.common.ProjectCommand#myExecute() */
	@Override
	public final void myExecute() throws DialogException, NoSuchDisciplineIdException, NoSuchProjectIdException {
		_receiver.doCloseProject(_nameDiscipline.value(), _nameProject.value());
	}

}
