package com.example.medicObjects;

public class Answers {

	String answer;
	String answerid;
	public Answers(String answer, String answerid) {
		super();
		this.answer = answer;
		this.answerid = answerid;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getAnswerid() {
		return answerid;
	}
	public void setAnswerid(String answerid) {
		this.answerid = answerid;
	}
	@Override
	public String toString() {
		return  "+"+getAnswerid() +" "+getAnswer()+"\n";
	}
}
