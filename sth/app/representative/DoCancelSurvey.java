package sth.app.representative;

import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import sth.app.exception.NoSuchDisciplineException;
import sth.app.exception.NoSuchProjectException;
import sth.app.exception.NoSurveyException;
import sth.app.exception.NonEmptySurveyException;
import sth.app.exception.SurveyFinishedException;
import sth.core.SchoolManager;

import sth.core.exception.NoSuchDisciplineIdException;
import sth.core.exception.NoSuchProjectIdException;
import sth.core.exception.NoSurveyIdException;
import sth.core.exception.NonEmptySurveyIdException;
import sth.core.exception.SurveyFinishedIdException;

/**
 * 4.5.2. Cancel survey.
 */
public class DoCancelSurvey extends sth.app.common.ProjectCommand {

  /**
   * @param receiver
   */
  public DoCancelSurvey(SchoolManager receiver) {
    super(Label.CANCEL_SURVEY, receiver);
    //FIXME initialize input fields if needed
  }

  /** @see sth.app.common.ProjectCommand#myExecute() */
  @Override
  public final void myExecute() throws DialogException {
	  String disciplineName = _discipline.value();
	  String projectName = _project.value();
	  
	  try {
		  _receiver.doCancelSurvey(disciplineName, projectName);
	  }
	  catch(NoSuchDisciplineIdException e) {
		  throw new NoSuchDisciplineException(e.getId());
	  }
	  catch(NoSuchProjectIdException e) {
		  throw new NoSuchProjectException(e.getIdDiscipline(),e.getIdProject());
	  }
	  catch(NoSurveyIdException e) {
		  throw new NoSurveyException(e.getDiscipline(),e.getProject());
	  }
	  catch(NonEmptySurveyIdException e) {
		  throw new NonEmptySurveyException(e.getDiscipline(),e.getProject());
	  }
	  catch(SurveyFinishedIdException e) {
		  throw new SurveyFinishedException(e.getDiscipline(),e.getProject());
	  }
  }

}
