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

/**
 * 
 * @author Abderrahmane BOUTITAOU & Ismail SEBBANE & Adel BOUFRAH & Fares SALEM CHERIF
 * Ce JavaBean d�fini les propri�t�s d'un log.
 * 
 */
@Entity
public class Log {
	
private static final long serialVersionUID = 1L;
	
	/**
	 * Identifie de maniere unique un log.
	 */
	@Id()
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	/**
	 * Login de l'utilisateur.
	 */
    @Basic(optional = true)
    @Column(name = "iduser", length = 50, nullable=true)
    private String idUser;

    /**
	 * Date du log.
	 */
    @Basic()
	@Temporal(TemporalType.DATE)
	@Column(name = "date")
    private Date date;
    

	/**
	 * Type du log.
	 */
    @Basic(optional = true)
    @Column(name = "logtype")
    private int logType;

	public Log() {
	}
	
	public Log(String idUser, Date date, int logType) {
		super();
		this.idUser = idUser;
		this.date = date;
		this.logType = logType;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLogin() {
		return idUser;
	}

	public void setLogin(String idUser) {
		this.idUser = idUser;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getLogType() {
		return logType;
	}

	public void setLogType(int logType) {
		this.logType = logType;
	}

}
