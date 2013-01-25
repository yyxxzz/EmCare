package com.company.emcare.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "person_vote_history")
public class PersonVoteHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "voiceId")
	private Voice voice;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "personId")
	private Person person;
	
	@Column
	private Boolean isVote;
	
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

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Boolean getIsVote() {
		return isVote;
	}

	public void setIsVote(Boolean isVote) {
		this.isVote = isVote;
	}
	
}
