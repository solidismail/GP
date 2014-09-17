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

public class ProjectDirector implements ICUser{
	
	private User user;
	private DaoFactory df ;
	private Dao dao ;
	
//---------------------------------------à developper----------------------------------------
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
	
	public ProjectDirector() {
		df = new DaoFactory();
		dao = df.createDAO();
	}


	public User getUser() {
		
		return dao.find(User.class, user.getLogin());
	}


	public void setUser(User user) {
		this.user = user;
	}


	public ProjectDirector(User user) {
		df = new DaoFactory();
		dao = df.createDAO();
		this.user = user;
	}

	
	public void testAddEntities(Object entity){
		
		dao.add(entity);
	}
	
	@Override
	public Project createProject(Project project){
		
		Project p  = dao.add(project);
		
		return p;
	}
	
	@Override
	public Project findProject(long idProject){
		
		Project p= dao.find(Project.class, idProject);
				
		return(p);
	}
	
	@Override
	public List<Project> findAllProjects(){
		
		List<Project> p= dao.findAll("FROM Project", Project.class);
				
		return(p);
	}
	
	//A refaire
	public void removeProject(long idProject){
		
		
	    dao.remove(Project.class, idProject);
	    for(User us:dao.findAll("From User", User.class)){
	    	dao.refresh(us);
	    	
	    }
	  
		
	}
	

	@Override
	public void associatClientToProject(long idProject, String idClient){
		
		
		User u =  dao.find(User.class, idClient);
		
		u.setStatus(1);
		dao.update(u);
		//dao.refresh(u);
		
		
		

			Project p = dao.find(Project.class, idProject);
			p.setClient(u);
			dao.update(p);
			dao.refresh(u);
				

		

	}
	
	@Override
	public void associatProjectManagerToProject(long idProject, String idProjectManager){
				
		User u =  dao.find(User.class, idProjectManager);
		

		u.setStatus(3);
		dao.update(u);
		
		
			
			Project p = dao.find(Project.class, idProject);
			p.setProjectManager(u);
			dao.update(p);
			dao.refresh(u);
			
	
		
	
	}
	
	
	public Reunion findReunion(long idReunion) {
		return dao.find(Reunion.class, idReunion);
		
	}
	
	
	
	//A refaire
	public void modifyProject(Project project){
						
		dao.update(project);
				
	}
	
	@Override
	public void removeProjectManagerFromProject(long idProject, String idProjectManager){
		
		
		User u =  dao.find(User.class, idProjectManager);
		
		if(u.getStatus()==3){
			Project p = dao.find(Project.class, idProject);
			p.setProjectManager(null);
			dao.update(p);
			dao.refresh(u);
						
		}
						
	}
	
	@Override
	public void removeClientFromProject(long idProject, String idClient){
	
		
		User u =  dao.find(User.class, idClient);
		
		if(u.getStatus()==1){
			Project p = dao.find(Project.class, idProject);
			p.setClient(null);
			dao.update(p);
			dao.refresh(u);
						
		}
		
		
	}
	
	@Override
	public List<Reunion> consultInternalReunion(){
		
		//String query = "FROM Reunion WHERE typereunion=0";
		
	   // List<Reunion> l= dao.findAll(query, Reunion.class);
	    List<Reunion> reunionList = new ArrayList<Reunion>();
	    /*
	    for(int i = 0; i < l.size();i++){
	    	for(Reunion r : this.user.getListDevelopperReunion())
				 
					reunionList.add(l.get(i));
	    
	    }
*/
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
	public List<Reunion> consultExternalReunion(){
		
		String query = "FROM Reunion WHERE typereunion=1";
		
	    List<Reunion> l= dao.findAll(query, Reunion.class);
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
	public List<FileUploadBean> readAllFiles() {
		
		List<FileUploadBean> l = dao.findAll("From FileUploadBean", FileUploadBean.class);
		
		return l;
	}
	
	@Override
	public FileUploadBean readFile(long idFile) {
				
		FileUploadBean l = dao.find(FileUploadBean.class, idFile);
		
		return l;
	}

	
	
	@Override
	public void modifyPersonnalInformations(User user) throws NoSuchAlgorithmException {
		user.setLogin(this.getUser().getLogin());
		
		user.setPwd( computeHash(this.getUser().getPwd()) );
		
		dao.update(user);

	}

	
	@Override
	public void disconnect(Log l){
		
		dao.add(l);
		
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
//*******************************************************************************************


	@Override
	public User findUser(String login) {
		
		
		User u = dao.find(User.class, login);
		
		return u;
	}


	@Override
	public List<User> findAllUsers() {
		List<User> l =dao.findAll("From User", User.class);
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
	public void saveFile(FileUploadBean file) {
		// TODO Auto-generated method stub
		
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
	public List<FileUploadBean> readFile() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Activity> consultActivities() {
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
		return user.getStatus();
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





}
