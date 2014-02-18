package com.example.medicObjects;

public class patientInfo {
	
	String Pname;
	String Pnum;
	String Pemail;
	String Pdob;
	String Pgender;
	String Pid;
	
	public patientInfo(String pname, String pnum, String pemail, String pdob,
			String pgender, String patientID) {
		super();
		Pname = pname;
		Pnum = pnum;
		Pemail = pemail;
		Pdob = pdob;
		Pgender = pgender;
		this.Pid = patientID;
	}
	
	public String getPname() {
		return Pname;
	}
	public void setPname(String pname) {
		Pname = pname;
	}
	public String getPnum() {
		return Pnum;
	}
	public void setPnum(String pnum) {
		Pnum = pnum;
	}
	public String getPemail() {
		return Pemail;
	}
	public void setPemail(String pemail) {
		Pemail = pemail;
	}
	public String getPdob() {
		return Pdob;
	}
	public void setPdob(String pdob) {
		Pdob = pdob;
	}
	public String getPgender() {
		return Pgender;
	}
	public void setPgender(String pgender) {
		Pgender = pgender;
	}
	public String getPid() {
		return Pid;
	}
	public void setPid(String pid) {
		Pid = pid;
	}
	
	public String toString(){
		return "Patient ID: " + getPid() +
				" \nName: " + Pname      + 
				"\nGender: " + Pgender   +
				" \nDOB: " + getPdob()   +
				" \nEmail: " + getPemail()+
				" \nNumber: " + getPnum();
	}

}
