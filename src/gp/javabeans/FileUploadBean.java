package gp.javabeans;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
public class FileUploadBean {

    
	@Id()
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
		

	@Basic(optional = false)
    @Column(name = "name")
    private String name;
	

	@Basic(optional = true)
    @Column(name = "description")
    private String description;
	
	
	

    public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Date de creation du fichier.
	 */
    @Basic()
	@Temporal(TemporalType.DATE)
	@Column(name = "date")
    private Date date;
    
	/**
	 * Login de l'utilisateur.
	 */
    @Basic(optional = true)
    @Column(name = "idUser", length = 50, nullable=true)
    private String idUser;
    
    /**
     * Identifiant du projet auquel le fichier appartient.
     */
    @Basic(optional = true)
    @Column(name = "idproject")
    private long idProject;
    
    /**
     * Identifiant du client auquel le fichier est destinaire.
     */
    @Basic(optional = true)
    @Column(name = "idclient")
    private String idClient;

	@Basic(optional = true)
    @Column(name = "url")
    private String url;
    
	@Basic(optional = true)
    @Column(name = "fileType")
    private int fileType;
	
	@Basic(optional = true)
    @Column(name = "validate")
    private boolean validate;
	
	@Basic()
	@Temporal(TemporalType.DATE)
	@Column(name = "validdate")
	private Date validDate;
	
	
	public Date getValidDate() {
		return validDate;
	}

	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}

	public FileUploadBean() {
	}

	public FileUploadBean(String name, String idUser, Date date, long idProject,
			String idClient, String url, int fileType, boolean validate) {
		super();
		this.name = name;
		this.idUser = idUser;
		this.date = date;
		this.idProject = idProject;
		this.idClient = idClient;
		this.url = url;
		this.fileType = fileType;
		this.validate = validate;
	}


	public int getFileType() {
		return fileType;
	}

	public void setFileType(int fileType) {
		this.fileType = fileType;
	}

	public boolean isValidate() {
		return validate;
	}

	public void setValidate(boolean validate) {
		this.validate = validate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public long getIdProject() {
		return idProject;
	}

	public void setIdProject(long idProject) {
		this.idProject = idProject;
	}

	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}
	
    public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}


	public String getIdClient() {
		return idClient;
	}


	public void setIdClient(String idClient) {
		this.idClient = idClient;
	}
	
    public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
    
    
}