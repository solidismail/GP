package gp.javabeans;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @author Abderrahmane BOUTITAOU & Ismail SEBBANE & Adel BOUFRAH & Fares SALEM CHERIF
 * Ce JavaBean defini les proprietes d'une reunion.
 * 
 */
@Entity
public class Reunion{
	
	/**
	 * Identifie de maniere unique une reunion.
	 */
	@Id()
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	 /**
	 * Date de la reunion.
	 */
    @Basic(optional = true)
	@Temporal(TemporalType.DATE)
	@Column(name = "date")
	private Date date;
    
    /**
	 * l'heure de la reunion.
	 */
    @Basic(optional = true)
    @Column(name = "time")
	private String time;
    
    /**
	 * Identifiant du projet concerne par la reunion.
	 */
    @Basic(optional = true)
    @Column(name = "idProject")
	private long idProject;
              
    /**
	 * Identificant du chef de projet concerne par la reunion.
	 */
    @Basic(optional = true)
    @Column(name = "idProjectManager")
	private String idProjectManager;

    /**
	 * Identifiant du client appartenant a la reunion.
	 */
    @Basic(optional = true)
    @Column(name = "participIdClient", length = 25)
    private String participIdClient;
   
    /**
	 * Description de la reunion.
	 */
    @Basic(optional = true)
    @Column(name = "description")
	private String description;
    
    /**
     * Type de la reunion :
     * 		interne 0
     * 		externe 1
     */
    @Basic(optional = true)
    @Column(name = "typereunion")
	private int typeReunion;

    @ManyToMany
    private Set<User> listParticipDevelopper;

    /**
	 * Liste des identifiants des developpeurs appartenant a la reunion.
	 */
    //@OneToMany(mappedBy="project",orphanRemoval = true)
    //private Set<String> listIdDevelopper;
    
    /**
	 * Identifiants des devloppeurs appartenant a la reunion.
	 */
    /* @Basic(optional = false)
    @Column(name = "participIdDev", length = 25)
    private Set<String> participIdDev;
    */
    
	public Reunion() {
		this.listParticipDevelopper = new HashSet<User>();
	}

	public Reunion(Date date,String time, long idProject, String idProjectManager,
		String participIdClient,String description, int typeReunion,Set<User>listParticipDevelopper) {
		this.date = date;
		this.time = time;
		this.idProject = idProject;
		this.idProjectManager = idProjectManager;
		this.participIdClient = participIdClient;
		this.description = description;
		this.typeReunion = typeReunion;
		this.listParticipDevelopper = new HashSet<User>();
		this.setListParticipDevelopper(listParticipDevelopper);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	
	
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public long getIdProject() {
		return idProject;
	}

	public void setIdProject(long idProject) {
		this.idProject = idProject;
	}

	public String getIdProjectManager() {
		return idProjectManager;
	}


	public String getParticipIdClient() {
		return participIdClient;
	}

	public void setParticipIdClient(String participIdClient) {
		this.participIdClient = participIdClient;
	}

	public void setIdProjectManager(String idProjectManager) {
		this.idProjectManager = idProjectManager;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getTypeReunion() {
		return typeReunion;
	}

	public void setTypeReunion(int typeReunion) {
		this.typeReunion = typeReunion;
	}

	public Set<User> getListParticipDevelopper() {
		return listParticipDevelopper;
	}

	public void setListParticipDevelopper(Set<User> listParticipDevelopper) {
		this.listParticipDevelopper = listParticipDevelopper;
	}
    
	public void addListParticipDevelopper(User u){
		this.listParticipDevelopper.add(u);
	}
	public void removeListParticipDevelopper(User user){
		this.getListParticipDevelopper().remove(user);
	}

	
}
