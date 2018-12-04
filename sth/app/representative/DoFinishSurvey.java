package sth.app.representative;

import pt.tecnico.po.ui.DialogException;

import sth.app.exception.FinishingSurveyException;
import sth.app.exception.NoSuchDisciplineException;
import sth.app.exception.NoSuchProjectException;
import sth.app.exception.NoSurveyException;
import sth.core.SchoolManager;

//FIXME import other classes if needed

import sth.core.exception.FinishingSurveyIdException;
import sth.core.exception.NoSuchProjectIdException;
import sth.core.exception.NoSurveyIdException;
import sth.core.exception.NoSuchDisciplineIdException;

/**
 * 4.6.5. Finish survey.
 */
public class DoFinishSurvey extends sth.app.common.ProjectCommand {

  /**
   * @param receiver
   */
  public DoFinishSurvey(SchoolManager receiver) {
    super(Label.FINISH_SURVEY, receiver);
  }

  /** @see sth.app.common.ProjectCommand#myExecute() */ 
  @Override
  public final void myExecute() throws DialogException{
	  String disciplineName = _discipline.value();
	  String projectName = _project.value();
	  
	  try {
	  _receiver.doFinishSurvey(disciplineName, projectName);
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
	  catch(FinishingSurveyIdException e)
	  {
		  throw new FinishingSurveyException(e.getDiscipline(),e.getProject());
	  }
  }

}
