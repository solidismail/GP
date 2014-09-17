package gp.javabeans;

import java.util.Date;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @author Abderrahmane BOUTITAOU & Ismail SEBBANE & Adel BOUFRAH & Fares SALEM CHERIF
 * Ce JavaBean defini les proprietes d'une activite.
 * 
 */

@Entity
public class Activity {
	
	/**
	 * Identifie de maniere unique une reunion.
	 */
	@Id()
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	/**
	 * Nom de l'activite.
	 */
	@Basic(optional = true)
	@Column(name = "name", length = 50)
	private String name;	
	
	/**
	 * Date de debut d'une activite.
	 */
	@Basic()
	@Temporal(TemporalType.DATE)
	@Column(name = "bp")
    private Date beginDate;
    
    /**
	 * Date previssionnelle de fin d'une activite.
	 */
	@Basic()
	@Temporal(TemporalType.DATE)
	@Column(name = "epd")
    private Date endPrevisionnalDate;
    
    /**
	 * Date reelle de fin d'une activite.
	 */
	@Basic()
	@Temporal(TemporalType.DATE)
	@Column(name = "erd")
	private Date endRealDate;
    
    /**
     * Description de l'activite.
     */
	@Basic(optional = true)
	@Column(name = "description")
	private String description;
	
	
	/**
	 * Le chef de projet qui a affecter une tache.
	 */
	@Basic(optional = true)
	@Column(name = "idprojectmanager")
	private String idProjectManager;
	

	/**
	 *
	 */
	@Basic(optional = true)
	@Column(name = "idproject")
	private long idProject;
	
	@ManyToOne(optional=true)
    @JoinColumn(name = "developper")
    private User developper;
	
	
	public Activity(){
		
	}
	
	public Activity(String name, Date beginDate,Date endPrevisionnalDate, Date endRealDate, String Description, String idProjectManager, long idProject){
		this.name= name;
		this.beginDate = beginDate;
		this.endPrevisionnalDate = endPrevisionnalDate;
		this.endRealDate= endRealDate;
		this.description= Description;
		this.idProjectManager =idProjectManager;
		this.idProject=idProject;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIdProjectManager() {
		return idProjectManager;
	}

	public void setIdProjectManager(String idProjectManager) {
		this.idProjectManager = idProjectManager;
	}

	public long getIdProject() {
		return idProject;
	}

	public void setIdProject(long idProject) {
		this.idProject = idProject;
	}

	public User getDevelopper() {
		return developper;
	}

	public void setDevelopper(User developper) {
		this.developper = developper;
	}

	
}
