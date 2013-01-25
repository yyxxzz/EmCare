package com.company.emcare.model;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;

@Entity
@Table(name = "voice")
public class Voice {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long voiceId;
	@Column
	private String title;
	@Column
	@Lob
	private String content;
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = VoiceType.class)
    @JoinColumn(name = "type", referencedColumnName = "id")
	private VoiceType type;
	@Column
	private int status;
	@Column
	private Timestamp submitTime;
	@Column(columnDefinition="BIGINT default 0")
	private Long viewCount;
	@Column(columnDefinition="BIGINT default 0")
	private Long agreeCount;
	@Column(columnDefinition="BIGINT default 0")
	private Long disagreeCount;
	@Column
	private String signature;
	@OneToOne(mappedBy="voice")
	private VoiceAssignment assignment;
	@OneToMany(mappedBy="voice",fetch=FetchType.LAZY)
	private Set<VoiceComment> comments;
	@OneToMany(fetch =FetchType.LAZY,mappedBy="voice")
	private Set<AttachFile> attachFiles;
	
	public final static int STATUS_OPEN=1;
	public final static int STATUS_ASSIGNED=2;
	public final static int STATUS_PENDING=3;
	public final static int STATUS_RESOLVED=4;
	public final static int STATUS_CLOSED=6;
	public final static int STATUS_REJECTED=5;
	public Long getVoiceId() {
		return voiceId;
	}
	public void setVoiceId(Long voiceId) {
		this.voiceId = voiceId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Timestamp getSubmitTime() {
		return submitTime;
	}
	public void setSubmitTime(Timestamp submitTime) {
		this.submitTime = submitTime;
	}
	
	public VoiceAssignment getAssignment() {
		return assignment;
	}
	public void setAssignment(VoiceAssignment assignment) {
		this.assignment = assignment;
	}
	public String getStatusString(){
		switch (status){
		case STATUS_OPEN:
			return "Open";
		case STATUS_PENDING:
			return "Pending";
		case STATUS_RESOLVED:
			return "Resolved";
		case STATUS_CLOSED:
			return "Closed";
		case STATUS_REJECTED:
			return "Rejected";
		case STATUS_ASSIGNED:
			return "Assigned";
		default: return "Invalid status";
		}
	}
	public Long getViewCount() {
		return viewCount==null?0:viewCount;
	}
	public void setViewCount(Long viewCount) {
		this.viewCount = viewCount;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public VoiceType getType() {
		return type;
	}
	public void setType(VoiceType type) {
		this.type = type;
	}
	
	public boolean isResolved(){
		return STATUS_RESOLVED==status;
	}
	
	public Set<VoiceComment> getComments() {
		return comments;
	}
	public void setComments(Set<VoiceComment> comments) {
		this.comments = comments;
	}
	@Transient
	public static String getStatusString(int state){
		switch (state){
		case STATUS_OPEN:
			return "Open";
		case STATUS_PENDING:
			return "Pending";
		case STATUS_RESOLVED:
			return "Resolved";
		case STATUS_CLOSED:
			return "Closed";
		case STATUS_REJECTED:
			return "Rejected";
		case STATUS_ASSIGNED:
			return "Assigned";
		default: return "Invalid status";
		}
	}
	public boolean hasAssigned(){
		return getAssignment()!=null;
	}
	public boolean isClosed(){
		return status==STATUS_CLOSED;
	}
	@Transient
	public String getShortTitle(){
		String shortText =title;
		if(!StringUtils.isEmpty(title)){
			if(title.length()>=40){
				shortText = title.substring(0,40)+"...";
			}
		}
		return shortText;
	}
	@Transient
	public String getShortContent(){
		String shortText =content;
		if(!StringUtils.isEmpty(content)){
			if(content.length()>=250){
				shortText = content.substring(0,250)+"...";
			}
		}
		return shortText;
	}
	public Set<AttachFile> getAttachFiles() {
		return attachFiles;
	}
	public void setAttachFiles(Set<AttachFile> attachFiles) {
		this.attachFiles = attachFiles;
	}
	public Long getAgreeCount() {
		return agreeCount;
	}
	public void setAgreeCount(Long agreeCount) {
		this.agreeCount = agreeCount;
	}
	public Long getDisagreeCount() {
		return disagreeCount;
	}
	public void setDisagreeCount(Long disagreeCount) {
		this.disagreeCount = disagreeCount;
	}
	
}
