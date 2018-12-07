package sth.app.student;

import sth.core.SchoolManager;

import sth.core.exception.NoSuchDisciplineIdException;
import sth.core.exception.NoSuchProjectIdException;
import sth.core.exception.NoSurveyIdException;
import sth.app.exception.NoSurveyException;

/**
 * 4.5.3. Show survey results.
 */
public class DoShowSurveyResults extends sth.app.common.ProjectCommand {

  /**
   * @param receiver
   */
  public DoShowSurveyResults(SchoolManager receiver) {
    super(Label.SHOW_SURVEY_RESULTS, receiver);
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void myExecute() throws NoSuchDisciplineIdException, NoSuchProjectIdException, NoSurveyException{
	  String disciplineName = _discipline.value();
	  String projectName = _project.value();
	  
	  try {
	  
	  String text = _receiver.doShowSurveysResultsStudent(disciplineName, projectName);
		
	  _display.add(text);
	  _display.display();
	  }
	  catch(NoSurveyIdException e)
	  {
		  throw new NoSurveyException(e.getDiscipline(),e.getProject());
	  }
  }

}
