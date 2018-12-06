package sth.app.representative;

import pt.tecnico.po.ui.DialogException;

import sth.app.exception.NoSuchDisciplineException;
import sth.app.exception.NoSuchProjectException;
import sth.app.exception.NoSurveyException;
import sth.app.exception.OpeningSurveyException;
import sth.core.SchoolManager;

import sth.core.exception.NoSuchProjectIdException;
import sth.core.exception.NoSurveyIdException;
import sth.core.exception.OpeningSurveyIdException;
import sth.core.exception.NoSuchDisciplineIdException;

/**
 * 4.6.3. Open survey.
 */
public class DoOpenSurvey extends sth.app.common.ProjectCommand {

  /**
   * @param receiver
   */
  public DoOpenSurvey(SchoolManager receiver) {
    super(Label.OPEN_SURVEY, receiver);
  }

  /** @see sth.app.common.ProjectCommand#myExecute() */ 
  @Override
  public final void myExecute() throws DialogException {
	  String disciplineName = _discipline.value();
	  String projectName = _project.value();
	  
	  System.out.println("cheguei a app e tentei abrir um survey");
	  try {
	  _receiver.doOpenSurvey(disciplineName, projectName);
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
	  catch(OpeningSurveyIdException e)
	  {
		  throw new OpeningSurveyException(e.getDiscipline(),e.getProject());
	  }
  }

}
