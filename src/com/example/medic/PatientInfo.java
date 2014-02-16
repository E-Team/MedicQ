package com.example.medic;

import java.util.ArrayList;

public class PatientInfo {
	
	
	
	String PatientName = null;
	String Patientid = null;
	String gender = null;
	String numquestions = null;
	
	public PatientInfo(String pname, String gender,String questionNum,String pid){
		PatientName = pname;
		this.gender = gender;
		numquestions = questionNum;
		Patientid = pid;
	}

	public String getPatientName() {
		return PatientName;
	}

	public void setPatientName(String patientName) {
		PatientName = patientName;
	}

	public String getPatientid() {
		return Patientid;
	}

	public void setPatientid(String patientid) {
		Patientid = patientid;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getNumquestions() {
		return numquestions;
	}

	public void setNumquestions(String numquestions) {
		this.numquestions = numquestions;
	}

}
