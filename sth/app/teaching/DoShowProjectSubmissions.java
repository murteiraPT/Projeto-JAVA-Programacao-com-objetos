package sth.app.teaching;

import pt.tecnico.po.ui.DialogException;
import sth.core.SchoolManager;

import sth.core.exception.NoSuchDisciplineIdException;
import sth.core.exception.NoSuchProjectIdException;

/**
 * 4.4.3. Show project submissions.
 */
public class DoShowProjectSubmissions extends sth.app.common.ProjectCommand {

	
	public DoShowProjectSubmissions(SchoolManager receiver) {
		super(Label.SHOW_PROJECT_SUBMISSIONS, receiver);
	}

	/** @see sth.app.common.ProjectCommand#myExecute() */
	@Override
	public final void myExecute() throws DialogException, NoSuchDisciplineIdException, NoSuchProjectIdException {
		
		String disciplineName = _discipline.value();
		String projectName = _project.value();

		String text = _receiver.doShowProjectSubmissions(disciplineName, projectName);
		
		_display.add(text);
		_display.display();
	}

}
