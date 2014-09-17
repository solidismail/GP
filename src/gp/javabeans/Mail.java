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
public class Mail{

	/**
	 * 
	 */
	

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
	@Column(name = "expeditor")
	private String expeditor;	
	
	/**
	 * Nom de l'activite.
	 */
	@Basic(optional = true)
	@Column(name = "destinator")
	private String destinator;	
	
	/**
	 * Date de debut d'une activite.
	 */
	@Basic()
	@Temporal(TemporalType.DATE)
	@Column(name = "senddate")
    private Date sendDate;
	
	/**
	 * Nom de l'activite.
	 */
	@Basic(optional = true)
	@Column(name = "subject")
	private String subject;
	
	/**
	 * Nom de l'activite.
	 */
	@Basic(optional = true)
	@Column(name = "message")
	private String message;
	
	/**
	 * Nom de l'activite.
	 */
	@Basic(optional = true)
	@Column(name = "recieved")
	private int recieved;
	
	public Mail() {
		
	}



	public Mail(String expeditor, String destinator, 
			String subject, String message) {
		
		this.expeditor = expeditor;
		this.destinator = destinator;
		this.sendDate = sendDate;
		this.subject = subject;
		this.message = message;
		this.recieved = recieved;
	}



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getExpeditor() {
		return expeditor;
	}

	public void setExpeditor(String expeditor) {
		this.expeditor = expeditor;
	}

	public String getDestinator() {
		return destinator;
	}

	public void setDestinator(String destinator) {
		this.destinator = destinator;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getRecieved() {
		return recieved;
	}

	public void setRecieved(int recieved) {
		this.recieved = recieved;
	}

 
}
