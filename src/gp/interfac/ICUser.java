package gp.interfac;

import gp.javabeans.Activity;
import gp.javabeans.FileUploadBean;
import gp.javabeans.Log;
import gp.javabeans.Mail;
import gp.javabeans.Project;
import gp.javabeans.Reunion;
import gp.javabeans.User;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.Set;

public interface ICUser {

	// --------------------------------Administrateur----------------------------------------------
	public User findUser(String login);

	public List<User> findAllUsers();

	public void createUser(User u) throws NoSuchAlgorithmException;

	public void updateUserPwd(String login, String pwd) throws NoSuchAlgorithmException ;

	public void removeUser(String login);

	public void assignPDirectorToUser(String login);

	public void removePDirectorToUser(String login);

	public List<Log> consultLog(String login);

	public List<Log> consultAllLog();

	public void disconnect(Log l);
	
	

	// ------------------------------------Developpeur----------------------------------------------

	public List<FileUploadBean> readFile();

	public void saveFile(FileUploadBean file);

	public List<Reunion> consultInternalReunion();

	public List<Reunion> consultExternalReunion();

	public List<Activity> consultActivities();

	public void modifyPersonnalInformations(User user) throws NoSuchAlgorithmException;

	// -----------------------------------chooseProject----------------------------------------------

	public ICUser chooseProject(long idProject);

	// -----------------------------------client--------------------------------------------------

	public void validateFile(long idFile);

	// --------------------------------------Directeur de
	// projet----------------------------------------

	public Project createProject(Project project);

	public void removeProject(long idProject);

	public void modifyProject(Project project);

	public void associatClientToProject(long idProject, String idClient);

	public void associatProjectManagerToProject(long idProject,
			String idProjectManager);

	public void removeProjectManagerFromProject(long idProject,
			String idProjectManager);

	public void removeClientFromProject(long idProject, String idClient);

	// -------------------------------------ProjectManager-------------------------------------------

	void removeDevelopperFromProject(long idProject, String idDevelopper);

	Reunion createReunion(Date date, String time, String description,
			Set<User> participant, int type);
	
	Reunion findReunion(long idReunion);

	void associatDevelopperToProject(long idProject, String idDevelopper);

	List<Reunion> consultConcernedReunion();

	void createActivity(Activity activity);

	Activity findActivity(long idActivity);

	void removeActivity(Activity activity);

	public List<FileUploadBean> readAllFiles();

	User getUser();

	Set<Project> developperListProjects();

	Set<Project> managerListProjects();

	Set<Project> clientListProjects();

	int status();

	Project findProject(long idProject);

	List<Project> findAllProjects();

	FileUploadBean readFile(long idFile);

	long getIdCurrentProject();

	void setIdCurrentProject(long idCurrentProject);

	List<Reunion> consultCreatedReunion();

	Reunion modifyReunion(Reunion reunion);

	void modifyActivity(Activity activity);

	void cancelReunion(long idReunion);

	
	
	
	List<Reunion> findReunionOfClient(String idClient);

	boolean sendMail(Mail mail);

	List<Mail> reciveedMails();

	List<Mail> allMails();

	Mail findMail(long idMail);

	void readMail(long idMail);

	List<User> findProjectDirector();

	

}
