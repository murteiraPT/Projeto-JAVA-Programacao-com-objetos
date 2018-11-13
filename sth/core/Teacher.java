package sth.core;

public class Teacher extends Person{

	private List<Discpline> _disciplineListTeacher; //List ou ArrayList ?

	public Teacher(int numberOfPersons, String name, int phone){
		super(numberOfPersons, name, phone);
		_disciplineListTeacher = new ArrayList<>();
	}

	protected createProject(String name, String description){

		//Como e que verifico se o nome do projeto e unico no contexto da disciplina
		Project p = new Project(name,description);
		
	}

	
	protected void addDiscipline(Discipline d){
		//O UML do stor diz que e precisor de ter metodo addDiscipline mas no course.java ja tem o mesmo metodo??
	}

	@Override
	public String toString(){
		return 'DOCENTE' + _id + '|' + _phoneNumber + '|' + _name
	}

	protected String getProjectSubmissions(String nameDiscpline, Project proj) throws NoSuchDisciplineException
	{
		List<Discipline> list = new ArrayList<>();

		for(Discipline d : list){
			if(d.getName().equals(nameDiscpline))	//Procura se existe uma disciplina (por nome) do projeto na lista das 
			{										// discplinas leccionadas por Docente
				return proj.getSubmissions();
			}
		}

		throw NoSuchDisciplineException;
	}

	protected String getStudentsOfDiscipline(String nameDiscpline) throws NoSuchDisciplineException
	{
		List<Discipline> listD = new ArrayList<>();

		List<Student> listS = new ArrayList<>();

		for(Discipline d : listD){
			if(d.getName().equals(nameDiscpline))	//Procura se existe uma disciplina (por nome) do projeto na lista das 
			{										// discplinas leccionadas por Docente
				//falta a obter a lista de alunos
			}
		}

		throw NoSuchDisciplineException;
	}


	//Nao ha mais metodos no UML do stor
	//mas vi no enunciado 4.4 que se diz que ainda falta fecharProjeto(), verResultadosDeUmProjeto().....
}