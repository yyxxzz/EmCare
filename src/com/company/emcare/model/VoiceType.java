package com.company.emcare.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "voice_type")
public class VoiceType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;		
	
	@OneToMany(mappedBy="type",fetch=FetchType.LAZY)
	private List<Voice> voices;	
	
	@Column
	private String name;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Voice> getVoices() {
		return voices;
	}
	public void setVoices(List<Voice> voices) {
		this.voices = voices;
	}
	
}
