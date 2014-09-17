package gp.javabeans;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;



/**
 * 
 * @author Abderrahmane BOUTITAOU & Ismail SEBBANE & Adel BOUFRAH & Fares SALEM CHERIF
 * Ce JavaBean d�fini les propri�t�s d'un utilisateur.
 * 
 */
@Entity
public class User implements Serializable{
	

	private static final long serialVersionUID = 1L;    
	/**
	 * Login de l'utilisateur.
	 */
    @Id()
    @Basic(optional = false)
    @Column(name = "login")
    private String login;
    
	/**
	 * Mot de passe de l'utilisateur.
	 */
    @Basic(optional = false)
    @Column(name = "pwd")
    private String pwd;
    
    /**
	 * Nom de l'utilisateur.
	 */
    @Basic(optional = true)
    @Column(name = "firtname")
    private String firstName;
    
    /**
	 * Prenom de l'utilisateur.
	 */
    @Basic(optional = true)
    @Column(name = "lastname")
    private String lastName;
    
    /**
	 * Numero de telephone de l'utilisateur.
	 */
    @Basic(optional = true)
    @Column(name = "phone")
    private String phone;
    
    /**
	 * Adresse de l'utilisateur.
	 * @see Adress
	 */
    @Embedded
    private Address address;
    
    /**
	 * Mail de l'utilisateur.
	 */
    @Basic(optional = true)
    @Column(name = "mail")
    private String mail;
    
    /**
     * Status de l'utilisateur.
     */
    @Basic(optional = true)
    @Column(name = "status")
    private int status;
    
    /**
	 * Liste des projets de l'utilisateur.
	 */
    @ManyToMany( fetch = FetchType.LAZY,mappedBy="listDevelopper")
    private Set<Project> listDevelopperProject;
   
    
    @ManyToMany( fetch = FetchType.LAZY,mappedBy="listParticipDevelopper")
    private Set<Reunion> listDevelopperReunion;
    
    @OneToMany(
  	      fetch = FetchType.LAZY,mappedBy = "developper")
    private Set<Activity> listActivities;
    
    @OneToMany(
    	      fetch = FetchType.LAZY,mappedBy = "client")
    private Set<Project> listClientProject;
    
    @OneToMany(
  	      fetch = FetchType.LAZY,mappedBy = "projectManager")
    private Set<Project> listProjectManager;
    
   
    
   	/**
     * Constructeur de la classe User.
     */
    public User(){
    	this.listDevelopperProject = new HashSet<Project>();
    	this.listClientProject = new HashSet<Project>();
    	
    }

    public User(String login, String pwd){
    	this.listDevelopperProject = new HashSet<Project>();
    	this.listClientProject = new HashSet<Project>();
    	
		this.login = login;
		this.pwd = pwd;
		
    }
    
	public User(String login, String pwd, String firtName, String lastName,
			String phone, Address address, String mail, int status) {
		this.login = login;
		this.pwd = pwd;
		this.firstName = firtName;
		this.lastName = lastName;
		this.phone = phone;
		this.address = address;
		this.mail = mail;
		this.status = status;
		this.listDevelopperProject = new HashSet<Project>();
    	this.listClientProject = new HashSet<Project>();
		
	}
	
	/**
	 * Recupere le login.
	 * @return	Le login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Modifie le login.
	 * @param login
	 * 		Le nouveau login.
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * Recupere le mot de passe.
	 * @return	Le mot de passe
	 */
	public String getPwd() {
		return pwd;
	}

	/**
	 * Modifie le mot de passe.
	 * @param pwd
	 * 		Le nouveau mot de passe.
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firtName) {
		this.firstName = firtName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public Address getAddress() {
		return address;
	}


	public void setAddress(Address address) {
		this.address = address;
	}


	public String getMail() {
		return mail;
	}


	public void setMail(String mail) {
		this.mail = mail;
	}


	/**
	 * Recupere le status.
	 * @return Le status
	 */
	public int getStatus() {
		return status;
	}
	
	/**
	 * Modifie le status.
	 * @param status
	 * 		Le nouveau status.
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	public Set<Project> getListDevelopperProject() {
		return listDevelopperProject;
	}


	public void setListDevelopperProject(Set<Project> listDevelopperProject) {
		this.listDevelopperProject = listDevelopperProject;
	}


	public Set<Project> getListClientProject() {
		return listClientProject;
	}


	public void setListClientProject(Set<Project> listClientProject) {
		this.listClientProject = listClientProject;
	}
	
	
	public void addListClientProject(Project project){
		this.listClientProject.add(project);
	}
	
	public void addListDevelopperProject(Project project){
		this.listDevelopperProject.add(project);
	}
	
	public void removeListDevelopperProject(Project project){
		this.getListDevelopperProject().remove(project);
	}

	public Set<Project> getListProjectManager() {
		return listProjectManager;
	}

	public void setListProjectManager(Set<Project> listProjectManager) {
		this.listProjectManager = listProjectManager;
	}
	
	public void addListProjectManager(Project project){
		this.listProjectManager.add(project);
	}
	
	public void removeListProjectManager(Project project){
		this.getListProjectManager().remove(project);
	}
	
	public Set<Reunion> getListDevelopperReunion() {
		return listDevelopperReunion;
	}


	public void setListDevelopperReunion(Set<Reunion> listDevelopperReunion) {
		this.listDevelopperReunion = listDevelopperReunion;
	}
	
	public void addListDevelopperReunion(Reunion reunion){
		this.listDevelopperReunion.add(reunion);
	}
	
	public void removeListDevelopperReunion(Reunion reunion){
		this.getListDevelopperReunion().remove(reunion);
	}
	
	//les activités
	public Set<Activity> getListActivities() {
		return listActivities;
	}


	public void setListActivities(Set<Activity> listActivities) {
		this.listActivities = listActivities;
	}
	
	public void addListActivities(Activity activity){
		this.listActivities.add(activity);
	}
	
	public void removeListActivities(Activity activity){
		this.getListActivities().remove(activity);
	}
	
}
