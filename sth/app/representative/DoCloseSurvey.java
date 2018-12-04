package sth.app.representative;

import pt.tecnico.po.ui.DialogException;
import sth.app.exception.ClosingSurveyException;
import sth.app.exception.NoSuchDisciplineException;
import sth.app.exception.NoSuchProjectException;
import sth.app.exception.NoSurveyException;

import sth.core.SchoolManager;
import sth.core.exception.ClosingSurveyIdException;
import sth.core.exception.NoSuchDisciplineIdException;
import sth.core.exception.NoSuchProjectIdException;
import sth.core.exception.NoSurveyIdException;

/**
 * 4.5.4. Close survey.
 */
public class DoCloseSurvey extends sth.app.common.ProjectCommand {
  /**
   * @param receiver
   */
  public DoCloseSurvey(SchoolManager receiver) {
    super(Label.CLOSE_SURVEY, receiver);
    //FIXME initialize input fields if needed
  }

  /** @see sth.app.common.ProjectCommand#myExecute() */
  @Override
  public final void myExecute() throws DialogException {
	  String disciplineName = _discipline.value();
	  String projectName = _project.value();
	  
	  try {
	  _receiver.doCloseSurvey(disciplineName, projectName);
	  }
	  catch(NoSuchDisciplineIdException e)
	  {
		  throw new NoSuchDisciplineException(e.getId());
	  }
	  catch(NoSuchProjectIdException e)
	  {
		  throw new NoSuchProjectException(e.getIdDiscipline(),e.getIdProject());
	  }
	  catch(NoSurveyIdException e)
	  {
		  throw new NoSurveyException(e.getDiscipline(),e.getProject());
	  }
	  catch(ClosingSurveyIdException e)
	  {
		  throw new ClosingSurveyException(e.getDiscipline(),e.getProject());
	  }
  }

}
