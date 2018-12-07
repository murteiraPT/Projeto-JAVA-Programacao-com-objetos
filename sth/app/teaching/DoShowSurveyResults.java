package sth.app.teaching;

import pt.tecnico.po.ui.DialogException;
import sth.app.exception.NoSurveyException;
import sth.core.SchoolManager;

import sth.core.exception.NoSuchProjectIdException;
import sth.core.exception.NoSurveyIdException;
import sth.core.exception.NoSuchDisciplineIdException;

/**
 * 4.4.5. Show survey results.
 */
public class DoShowSurveyResults extends sth.app.common.ProjectCommand {

  /**
   * @param receiver
   */
  public DoShowSurveyResults(SchoolManager receiver) {
    super(Label.SHOW_SURVEY_RESULTS, receiver);
    //FIXME initialize input fields if needed
  }

  /** @see sth.app.common.ProjectCommand#myExecute() */
  @Override
  public final void myExecute() throws DialogException, NoSuchDisciplineIdException, NoSuchProjectIdException {
	  String disciplineName = _discipline.value();
	  String projectName = _project.value();
	  
	  try {
	  
		  String text = _receiver.doShowSurveysResultsTeacher(disciplineName, projectName);
			
		  _display.add(text);
		  _display.display();
  		}
	  catch(NoSurveyIdException e)
	  {
		  throw new NoSurveyException(e.getDiscipline(),e.getProject());
	  }
  }

}
