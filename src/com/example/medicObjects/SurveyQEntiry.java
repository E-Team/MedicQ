package com.example.medicObjects;

import java.util.ArrayList;

import org.json.JSONArray;
public class SurveyQEntiry {
	
	
	
	
	ArrayList<Questions> questions;
	ArrayList<Answers> ansArray;
	ArrayList<JSONArray> imageJsonArray;
	ArrayList<JSONArray>  texthighlighJsonArray;
	
	
	public SurveyQEntiry(ArrayList<Questions> questionArraylist,
			ArrayList<Answers> ansArrayList,
			ArrayList<JSONArray> imageJsonArray,
			ArrayList<JSONArray> texthighlighJsonArray) {
		super();
		this.questions = questionArraylist;
		this.ansArray = ansArrayList;
		this.imageJsonArray = imageJsonArray;
		this.texthighlighJsonArray = texthighlighJsonArray;
	}
	
	
	
	
	public ArrayList<Questions> getQuestions() {
		return questions;
	}
	public void setQuestions(ArrayList<Questions> questions) {
		this.questions = questions;
	}
	public ArrayList<Answers> getAnsArray() {
		return ansArray;
	}
	public void setAnsArray(ArrayList<Answers> ansArray) {
		this.ansArray = ansArray;
	}
	public ArrayList<JSONArray> getImageJsonArray() {
		return imageJsonArray;
	}
	public void setImageJsonArray(ArrayList<JSONArray> imageJsonArray) {
		this.imageJsonArray = imageJsonArray;
	}
	public ArrayList<JSONArray> getTexthighlighJsonArray() {
		return texthighlighJsonArray;
	}
	public void setTexthighlighJsonArray(ArrayList<JSONArray> texthighlighJsonArray) {
		this.texthighlighJsonArray = texthighlighJsonArray;
	}
	
	

}
