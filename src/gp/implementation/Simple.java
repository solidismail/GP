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
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Simple implements ICUser {

	private User user;
	private DaoFactory df;
	private Dao dao;

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
	public Simple(User u) {
		
		user = u;
		df = new DaoFactory();
		dao = df.createDAO();
	}

	public Simple() {
		df = new DaoFactory();
		dao = df.createDAO();
		user= new User();
	}
	
	
	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		
		this.user = user;
	}


	public DaoFactory getDf() {
		return df;
	}


	public void setDf(DaoFactory df) {
		this.df = df;
	}

	
	public Dao getDao() {
		return dao;
	}


	public void setDao(Dao dao) {
		this.dao = dao;
	}

	

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
	public User findUser(String login) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findAllUsers() {
		// TODO Auto-generated method stub
		return null;
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
	public void disconnect(Log l) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<FileUploadBean> readFile() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveFile(FileUploadBean file) {
		// TODO Auto-generated method stub

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
	public List<Activity> consultActivities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void modifyPersonnalInformations(User user) throws NoSuchAlgorithmException {
		user.setLogin(this.getUser().getLogin());
		
		user.setPwd( computeHash(this.getUser().getPwd()) );
		
		dao.update(user);

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
	public List<FileUploadBean> readAllFiles() {
		// TODO Auto-generated method stub
		return null;
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
		return 0;
	}

	@Override
	public Project findProject(long idProject) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Project> findAllProjects() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FileUploadBean readFile(long idFile) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getIdCurrentProject() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setIdCurrentProject(long idCurrentProject) {
		// TODO Auto-generated method stub

	}

	@Override
	public Reunion findReunion(long idReunion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeDevelopperFromProject(long idProject, String idDevelopper) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void associatDevelopperToProject(long idProject, String idDevelopper) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Reunion> consultConcernedReunion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createActivity(Activity activity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Activity findActivity(long idActivity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeActivity(Activity activity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Reunion createReunion(Date date, String time, String description,
			Set<User> participant, int type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reunion> consultCreatedReunion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reunion modifyReunion(Reunion reunion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void modifyActivity(Activity activity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cancelReunion(long idReunion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Reunion> findReunionOfClient(String idClient) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Mail> reciveedMails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Mail> allMails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mail findMail(long idMail) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void readMail(long idMail) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<User> findProjectDirector() {
		// TODO Auto-generated method stub
		return null;
	}

}
