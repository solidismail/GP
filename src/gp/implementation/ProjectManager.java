package gp.implementation;

import gp.dao.Dao;
import gp.dao.DaoFactory;
import gp.interfac.ICUser;
import gp.javabeans.Activity;
import gp.javabeans.FileUploadBean;
import gp.javabeans.Log;
import gp.javabeans.Mail;
import gp.javabeans.Project;
import gp.javabeans.Reunion;
import gp.javabeans.User;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ProjectManager implements ICUser {

	private User user;
	private long idCurrentProject;
	private DaoFactory df;
	private Dao dao;
	private int status;

	private String seed = "d03c2015bcc148276834f66276e76a18aa87f8f0";

	//fonction qui permet de chiffrer les mot de passe en MD5
	public String computeHash(String string) throws NoSuchAlgorithmException {
        MessageDigest m;
        byte[] data;
        byte[] seed_data = seed.getBytes();
        BigInteger i;

        // compute password hash
        m = MessageDigest.getInstance("MD5");
        data = string.getBytes();
        m.update(seed_data, 0, seed_data.length);
        m.update(data, 0, data.length);
        i = new BigInteger(1, m.digest());
        return String.format("%1$032X", i);
    }
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public ProjectManager(User user, long idCurrentProject) {
		this.user = user;
		this.idCurrentProject = idCurrentProject;
		df = new DaoFactory();
		dao = df.createDAO();
		
	}

	public ProjectManager() {
		df = new DaoFactory();
		dao = df.createDAO();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public long getIdCurrentProject() {
		return idCurrentProject;
	}

	public void setIdCurrentProject(long idCurrentProject) {
		this.idCurrentProject = idCurrentProject;
	}

	// ---------------------------------à
	// developper---------------------------------------------

	public void testAddEntities(Object entity) {

		dao.add(entity);
	}

	@Override
	public Project findProject(long idProject) {

		Project p = dao.find(Project.class, idProject);

		return (p);
	}

	@Override
	public void associatDevelopperToProject(long idProject, String idDevelopper) {

		User u = dao.find(User.class, idDevelopper);

		if ((u.getStatus() == 3)||(u.getStatus()==-1)) {
			u.setStatus(3);
			Project p = dao.find(Project.class, idProject);
			p.addListDevelopper(u);
			dao.update(p);
			dao.refresh(u);
		}
	}

	@Override
	public void removeDevelopperFromProject(long idProject, String idDevelopper) {

		User u = dao.find(User.class, idDevelopper);

		if (u.getStatus() == 3) {
			
			Project p = dao.find(Project.class, idProject);
			
			p.removeListDevelopper(u);
			dao.update(p);
			
			dao.refresh(u);

		}

	}
	
	@Override
	public Reunion createReunion(Date date,String time, String description,
			Set<User> participant, int type) {

		Reunion r = null;
		if (participant.size() != 0) {
			// reunion avec le client

			if (type == 1) {
				User client = findProject(this.idCurrentProject).getClient();

				r = new Reunion(date,time, this.idCurrentProject, this.getUser()
						.getLogin(), client.getLogin(), description, 1,
						participant);
			} else {
				r = new Reunion(date,time, this.idCurrentProject, this.getUser()
						.getLogin(), null, description, 0, participant);
			}
			dao.add(r);

			for (User us : r.getListParticipDevelopper()) {
				dao.refresh(us);
			}

		}// if participant
		return r;
	}
	
	@Override
	public Reunion modifyReunion(Reunion reunion) {
		
		if (reunion.getTypeReunion()== 1){
			
			User client = findProject(this.idCurrentProject).getClient();
			reunion.setParticipIdClient(client.getLogin());
			
		}
		else {
			reunion.setParticipIdClient(null);
		}
		Reunion r =dao.update(reunion);
		for (User us : r.getListParticipDevelopper()) {
			dao.refresh(us);
		}
		return r;
	}
	
	@Override
	public void cancelReunion(long idReunion) {
		
		
		List<User> listUsers = new ArrayList<User>();
		
		for(User us: dao.find(Reunion.class, idReunion).getListParticipDevelopper()){
		listUsers.add(us);
		}
		/* = dao.find(Reunion.class, idReunion)
				.getListParticipDevelopper();
*/
		dao.remove(Reunion.class, idReunion);

		for (User us : listUsers) {
			dao.refresh(us);
		}
	}

	@Override
	public Reunion findReunion(long idReunion) {
		return dao.find(Reunion.class, idReunion);

	}

	@Override
	public List<Reunion> consultConcernedReunion() {

		List<Reunion> reunionList = new ArrayList<Reunion>();

		for (Reunion r : dao.findAll("FROM Reunion", Reunion.class)) {
			dao.refresh(r);
			for (User us : r.getListParticipDevelopper()) {
				if (this.user.getLogin().equals(us.getLogin()))
					reunionList.add(r);
			}

		}

		return reunionList;
	}

	
	@Override
	public List<Reunion> consultCreatedReunion() {

		List<Reunion> reunionList ;

		//for (Reunion r :
		reunionList = dao.findAll("FROM Reunion WHERE idProjectManager='"+this.user.getLogin()+"'", Reunion.class);
			/*{

			if (this.user.getLogin().equals(
					dao.find(User.class, r.getIdProjectManager()).getLogin()))
				reunionList.add(r);
		}*/

		return reunionList;
	}
	
	
	@Override
	public List<Reunion> findReunionOfClient(String idClient) {

	
		return dao.findAll("FROM Reunion WHERE participIdClient='"+idClient+"'", Reunion.class);

	}
	

	public List<FileUploadBean> readAllFiles() {

		List<FileUploadBean> l = dao.findAll("From FileUploadBean", FileUploadBean.class);

		return l;
	}

	public FileUploadBean readFile(long idFile) {

		FileUploadBean l = dao.find(FileUploadBean.class, idFile);

		return l;
	}
	
	@Override
	public void saveFile(FileUploadBean file) {

		dao.add(file);

	}

	@Override
	public void modifyPersonnalInformations(User user) throws NoSuchAlgorithmException {
		user.setLogin(this.getUser().getLogin());
		
		user.setPwd( computeHash(this.getUser().getPwd()) );
		
		dao.update(user);

	}

	
	@Override
	public void createActivity(Activity activity) {

		activity.setIdProjectManager(this.user.getLogin());
		activity.setIdProject(this.getIdCurrentProject());
		dao.add(activity);
		dao.refresh(activity.getDevelopper());
	}
	
	@Override
	public void modifyActivity(Activity activity) {

		dao.update(activity);

	}
	
	@Override
	public void removeActivity(Activity activity) {

		User us = (User) dao.find(Activity.class, activity.getId())
				.getDevelopper();

		dao.remove(Activity.class, activity.getId());
		dao.refresh(us);
	}

	@Override
	public Activity findActivity(long idActivity) {

		Activity a = dao.find(Activity.class, idActivity);
		return a;

	}

	@Override
	public List<Activity> consultActivities() {

		String query = "FROM Activity";

		List<Activity> l = dao.findAll(query, Activity.class);
		List<Activity> activityList = new ArrayList<Activity>();

		for (int i = 0; i < l.size(); i++) {

			if (this.user.getLogin().equals(l.get(i).getIdProjectManager()))
				activityList.add(l.get(i));

		}

		return activityList;
	}

	@Override
	public void disconnect(Log l) {

		dao.add(l);

	}
@Override
public boolean sendMail(Mail mail){
		try {
				mail.setSendDate(new Date());
			   Properties props = System.getProperties();
			   props.put("mail.smtp.localhost", "localhost");
			   //démarrage d'une session de courrier
			   Session session = Session.getDefaultInstance(props, null);
			   MimeMessage message = new MimeMessage(session);
			   //configuration de l'emetteur du message
			   message.setFrom(new InternetAddress(mail.getExpeditor()));
			   //configuration du récepteur
			   message.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(mail.getDestinator()));
			   //sujet de message
			   message.setSubject(mail.getSubject());
			   //date de l'envoi de message
			   message.setSentDate(new Date());
		
			   /*Envoi en HTML
			   message.setContent(content, "text/html");*/
			   //ou alors pour l'envoi en texte
			   message.setText(mail.getMessage());
			   
			   //Emission du message
			   javax.mail.Transport.send(message);
			   
			   dao.add(mail);
			   return true;
			}
			catch (Exception e) {
			   return false;
			}

	}


@Override
public List<Mail> reciveedMails(){
	
	return dao.findAll("FROM Mail WHERE destinator='"+this.getUser().getLogin()+"' AND recieved=0", Mail.class);
	
}

@Override
public List<Mail> allMails(){
	
	return dao.findAll("FROM Mail WHERE destinator='"+this.getUser().getLogin()+"'", Mail.class);
	
}


@Override
public Mail findMail(long idMail){
	
	return dao.find(Mail.class, idMail);
	
}

@Override
public void readMail(long idMail){
	
	Mail mail =  dao.find(Mail.class, idMail);
	mail.setRecieved(1);
	dao.update(mail);
	
}

@Override
public List<User> findProjectDirector() {


	return dao.findAll("FROM User WHERE status=2", User.class);

}

	// ************************************FIN***************************************************

	public User findUser(String login) {

		User u = dao.find(User.class, login);

		return u;
	}

	public List<User> findAllUsers() {
		List<User> l = dao.findAll("From User", User.class);
		return l;
	}

	@Override
	public void createUser(User u) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateUserPwd(String login, String pwd) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeUser(String login) {
		// TODO Auto-generated method stub

	}

	@Override
	public void assignPDirectorToUser(String login) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removePDirectorToUser(String login) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Log> consultLog(String login) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Log> consultAllLog() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FileUploadBean> readFile() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reunion> consultInternalReunion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reunion> consultExternalReunion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ICUser chooseProject(long idProject) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void validateFile(long idFile) {
		// TODO Auto-generated method stub

	}

	@Override
	public Project createProject(Project project) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeProject(long idProject) {
		// TODO Auto-generated method stub

	}

	@Override
	public void modifyProject(Project project) {
		// TODO Auto-generated method stub

	}

	@Override
	public void associatClientToProject(long idProject, String idClient) {
		// TODO Auto-generated method stub

	}

	@Override
	public void associatProjectManagerToProject(long idProject,
			String idProjectManager) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeProjectManagerFromProject(long idProject,
			String idProjectManager) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeClientFromProject(long idProject, String idClient) {
		// TODO Auto-generated method stub

	}



	@Override
	public Set<Project> developperListProjects() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Project> managerListProjects() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Project> clientListProjects() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int status() {
		// TODO Auto-generated method stub
		return 4;
	}

	@Override
	public List<Project> findAllProjects() {
		// TODO Auto-generated method stub
		return null;
	}

}
