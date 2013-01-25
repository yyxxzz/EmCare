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
import javax.persistence.Table;

@Entity
@Table(name = "voice_comment")
public class VoiceComment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "voiceId")
	private Voice voice;
	@Column
	@Lob
	private String content;
    @Column
	private String author;
	@Column
	private Timestamp commitDate;
	public VoiceComment() {

	}

	public VoiceComment(Voice voice,String comment,String author) {
		this.voice = voice;
		this.content = comment;
		this.author=author;
		this.commitDate = new Timestamp(System.currentTimeMillis());
	}

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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}


	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Timestamp getCommitDate() {
		return commitDate;
	}

	public void setCommitDate(Timestamp commitDate) {
		this.commitDate = commitDate;
	}
	
}
