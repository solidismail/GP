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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @author Abderrahmane BOUTITAOU & Ismail SEBBANE & Adel BOUFRAH & Fares SALEM
 *         CHERIF Ce JavaBean d�fini les propri�t�s d'un projet.
 * 
 */
@Entity
public class Project implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Identifie de maniere unique un projet
	 */
	@Id()
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	/**
	 * Nom du projet.
	 */
	@Basic(optional = true)
	@Column(name = "name", length = 50)
	private String name;

	/**
	 * Date de debut du projet.
	 */
	@Basic()
	@Temporal(TemporalType.DATE)
	@Column(name = "bd")
	private Date beginDate;

	/**
	 * Date previssionnelle de fin d'un projet.
	 */
	@Basic()
	@Temporal(TemporalType.DATE)
	@Column(name = "epd")
	private Date endPrevisionnalDate;

	/**
	 * Date reelle de fin d'un projet.
	 */
	@Basic()
	@Temporal(TemporalType.DATE)
	@Column(name = "erd")
	private Date endRealDate;

	
	/**
	 * Liste des identifiants des developpeurs appartenant � ce projet.
	 */
	@ManyToMany
	private Set<User> listDevelopper;

	/**
	 * Le client a qui appartient le projet.
	 */
	@ManyToOne(optional = true)
	@JoinColumn(name = "client")
	private User client;

	/**
	 * Le chef de projet a qui appartient le projet.
	 */
	@ManyToOne(optional = true)
	@JoinColumn(name = "projectManager")
	private User projectManager;

	/**
	 * Description du projet.
	 */
	@Basic(optional = true)
	@Column(name = "description", length = 25)
	private String description;

	/**
	 * Identifiant du directeur de projet appartenant � ce projet.
	 */
	/*
	 * @Basic(optional = false)
	 * 
	 * @Column(name = "idprojectdirector", length = 25) private String
	 * idProjectDirector;
	 */

	/**
	 * Constructeur de la classe Project.
	 */
	public Project() {
		this.listDevelopper = new HashSet<User>();
	}

	/**
	 * Constrcuteur de la classe Project.
	 */
	public Project(String name, Date beginDate, Date endPrevisionnalDate,
			Date endRealDate,String idProjectDirector, String description) {

		this.name = name;
		this.beginDate = beginDate;
		this.endPrevisionnalDate = endPrevisionnalDate;
		this.endRealDate = endRealDate;
		this.listDevelopper = new HashSet<User>();
		this.description = description;
		//this.client=client;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndPrevisionnalDate() {
		return endPrevisionnalDate;
	}

	public void setEndPrevisionnalDate(Date endPrevisionnalDate) {
		this.endPrevisionnalDate = endPrevisionnalDate;
	}

	public Date getEndRealDate() {
		return endRealDate;
	}

	public void setEndRealDate(Date endRealDate) {
		this.endRealDate = endRealDate;
	}

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<User> getListDevelopper() {
		return listDevelopper;
	}

	public void setListDevelopper(Set<User> listDevelopper) {
		this.listDevelopper = listDevelopper;
	}

	public void addListDevelopper(User u) {
		this.listDevelopper.add(u);
	}

	public void removeListDevelopper(User user) {
		this.getListDevelopper().remove(user);
	}

	public User getProjectManager() {
		return projectManager;
	}

	public void setProjectManager(User projectManager) {
		this.projectManager = projectManager;
	}

}
