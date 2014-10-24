package com.casemanager.domain;

import java.util.Date;

public class Case {

	private int id;
	private CaseType type;
	private String description;
	private Date createDate;
	private Date modifiedDate;

	private PatientInfo patientInfo;
	private Doctor doctor;
	private CareProvider careProvider;

	public CaseType getType() {
		return type;
	}

	public void setType(CaseType type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public PatientInfo getPatientInfo() {
		return patientInfo;
	}

	public void setPatientInfo(PatientInfo patientInfo) {
		this.patientInfo = patientInfo;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public CareProvider getCareProvider() {
		return careProvider;
	}

	public void setCareProvider(CareProvider casreProvider) {
		this.careProvider = casreProvider;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

}
