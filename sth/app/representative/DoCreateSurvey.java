package sth.app.representative;

import pt.tecnico.po.ui.DialogException;
import sth.app.exception.NoSuchDisciplineException;
import sth.app.exception.NoSuchProjectException;
import sth.core.SchoolManager;

import sth.core.exception.NoSuchDisciplineIdException;
import sth.core.exception.NoSuchProjectIdException;

/**
 * 4.5.1. Create survey.
 */
public class DoCreateSurvey extends sth.app.common.ProjectCommand {

  /**
   * @param receiver
   */
  public DoCreateSurvey(SchoolManager receiver) {
    super(Label.CREATE_SURVEY, receiver);
    //FIXME initialize input fields if needed
  }

  /** @see sth.app.common.ProjectCommand#myExecute() */ 
  @Override
  public final void myExecute() throws DialogException {
	  String disciplineName = _discipline.value();
	  String projectName = _project.value();
	  
	  try {
	  _receiver.doCreateSurvey(disciplineName, projectName);
	  }
	  catch(NoSuchDisciplineIdException e)
	  {
		  throw new NoSuchDisciplineException(e.getId());
	  }
	  catch(NoSuchProjectIdException e)
	  {
		  throw new NoSuchProjectException(e.getIdDiscipline(),e.getIdProject());
	  }
  }

}
