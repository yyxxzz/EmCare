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
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
@Entity
@Table(name="voice_assignment",uniqueConstraints=
@UniqueConstraint(name="assign_person",
columnNames={"voiceId", "assignTo_personId"} )
)
public class VoiceAssignment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="voiceId")
	private Voice voice;
	@Column
	@Lob
	private String comments;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="assignTo_personId")
	private Person assignTo;
	@Column
	private Timestamp assignAt;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Voice getVoice() {
		return voice;
	}
	public void setVoice(Voice voice) {
		this.voice = voice;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public Person getAssignTo() {
		return assignTo;
	}
	public void setAssignTo(Person assignTo) {
		this.assignTo = assignTo;
	}
	public Timestamp getAssignAt() {
		return assignAt;
	}
	public void setAssignAt(Timestamp assignAt) {
		this.assignAt = assignAt;
	}
	
	
	
}
