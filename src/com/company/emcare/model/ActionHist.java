package com.company.emcare.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
@Entity(name="action_hist")
public class ActionHist {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name="keyPerosnId")
	private Person keyPerson;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="minorPersonId")
	private Person minorPerson;
	@Column
	private int actionType;
	@ManyToOne
	@JoinColumn(name="voiceId")
	private Voice voice;
	private Timestamp updateTime;
	@Column
	@Lob
	private String comments;
	public final static int NEW = 0x1;
	public final static int ASSIGN= 0x2;
	public final static int RESOLVE = 0x3;
	public final static int CLOSE = 0x4;
	public final static int REJECT =0x5;
	public final static int REOPEN = 0x6;
	public final static int INPROGRESS=0x7;
	
	public static ActionHist constructActionHist(Voice voice,Person primaryPerson,Person secondaryPerson, int actionType,String comment){
		ActionHist ah = new ActionHist();
		ah.setKeyPerson(primaryPerson);
		ah.setMinorPerson(secondaryPerson);
		ah.setActionType(actionType);
		ah.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		ah.setVoice(voice);
		ah.setComments(comment);
		return ah;
	}
	
	public static ActionHist constructActionHist(Voice voice,Person primaryPerson, int actionType,String comment){
		return constructActionHist(voice,primaryPerson,null,actionType,comment);
	}
	
	public static ActionHist constructActionHist(Voice voice, Person primaryPerson, int actionType){
		return constructActionHist(voice, primaryPerson, null, actionType,null);
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Person getKeyPerson() {
		return keyPerson;
	}
	public void setKeyPerson(Person keyPerson) {
		this.keyPerson = keyPerson;
	}
	public Person getMinorPerson() {
		return minorPerson;
	}
	public void setMinorPerson(Person minorPerson) {
		this.minorPerson = minorPerson;
	}
	public int getActionType() {
		return actionType;
	}
	public void setActionType(int actionType) {
		this.actionType = actionType;
	}
	public Voice getVoice() {
		return voice;
	}
	public void setVoice(Voice voice) {
		this.voice = voice;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	
	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	/*
	 * get action description
	 */
	public String getActionDesc(){
		switch(actionType){
		case NEW:
			return "Submit";
		case ASSIGN:
			return "Assigned to " + minorPerson.getRealname();
		case RESOLVE:
			return "Resolved";
		case CLOSE:
			return "Closed";
		case REJECT:
			return "Rejected";
		case REOPEN:
			return "Reopened";
		case INPROGRESS:
			return "start processing";
			default:
				return "Submit";
		}
	}
	
}
