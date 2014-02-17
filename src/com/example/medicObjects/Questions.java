package com.example.medicObjects;

public class Questions {
	String questionid;
	String qType;
	String isImage;
	String question;
	
	public Questions(String questionid, String qType, String isImage,
			String question) {
		super();
		this.questionid = questionid;
		this.qType = qType;
		this.isImage = isImage;
		this.question = question;
	}

	public String getQuestionid() {
		return questionid;
	}

	public void setQuestionid(String questionid) {
		this.questionid = questionid;
	}

	public String getqType() {
		return qType;
	}

	public void setqType(String qType) {
		this.qType = qType;
	}

	public String getIsImage() {
		return isImage;
	}

	public void setIsImage(String isImage) {
		this.isImage = isImage;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String toString(){
		return "\nQuestion ID: " + getQuestionid() + "\nQuestion:  "
				+ getQuestion() + "\nType: " + getqType() + "\nImages: " + getIsImage();
		
	}
	
}
