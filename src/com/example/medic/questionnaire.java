package com.example.medic;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.R.integer;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class questionnaire extends Activity{

	TextView tv;
	ArrayList<String> questions;
	ArrayList<String> answers;
	String g = null;
<<<<<<< HEAD
=======

>>>>>>> parent of 9c7b606... Changed Quite a lot
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.questions);
		new Executer().execute();
		tv  = (TextView) findViewById(R.id.textView1);					
	}

	private class Executer extends AsyncTask<String, String, String> {
		@Override
		protected void onPostExecute(String result) {
<<<<<<< HEAD
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			tv.setText(g + "\n");
			for(int i=0;i<questions.size();i++){
				tv.setText(questions.get(i)+"\n");
=======
			if(result == null){
			super.onPostExecute(result);
				Toast.makeText(getApplicationContext(), "RESULT EMPTY", Toast.LENGTH_SHORT).show();
			}else{
				tv.setText(result + "\n");
				
>>>>>>> parent of 9c7b606... Changed Quite a lot
			}
		}

		@Override
		protected String doInBackground(String... arg0) {
<<<<<<< HEAD
			JsonParser jsonHandler = new JsonParser("http://ismailzd.co.uk/JsonQ.json");
			ArrayList<JSONObject> y = jsonHandler.getQuestionData();
			JSONObject z = jsonHandler.getuserdata();
			
			 questions = new ArrayList<String>();
			 answers = new ArrayList<String>();		
			 try {
				g = z.get("totalquestions").toString();
				
				for(JSONObject j : y){
					String temp = (String) j.getJSONObject("Question").getString(("value".toString()));
					String temp2 = (String) j.getJSONObject("Answers").getJSONObject("0").getString(("value".toString()));
					questions.add(temp);
					answers.add(temp2);
					
				}	
				Log.d("Karim is my Sidekick","QUESTIONS: "+ questions);
				Log.d("Karim is my Sidekick","ANSWERS: "+ answers);
				
=======
			JsonParser jsonHandler = new JsonParser("http://ismailzd.co.uk/JSONData.txt");
			JSONObject patientList = jsonHandler.getPatientDetailObject();
			JSONObject questionnaire = jsonHandler.getQuestionsArray();
			JSONArray answers = jsonHandler.getAnswerArray();
			JSONArray images = jsonHandler.getImagesArray();
			JSONArray TextHighlight = jsonHandler.getTextHighlightArray();
			String result = "";
			try {
				
				
				
				String patientID = patientList.getString("patientID")
						.toString();
				String name = patientList.getString("patientName").toString();
				String gender = patientList.getString("gender").toString();
				
				System.out.println(name);
				
				ArrayList<PatientInfo> questionsArrayList = new ArrayList<PatientInfo>();
				PatientInfo p = new PatientInfo(name, gender, patientID);
				questionsArrayList.add(p);
				
				
				result = "Patient ID: " + patientID + " \nName: " + name
						+ "\nGender: " + gender + "\nQuestion Number: ";
				
				String questionID = questionnaire.getString("QuestionID")
						.toString();
				String question = questionnaire.getString("Question")
						.toString();
				String type = questionnaire.getString("Type").toString();
				String isImage = questionnaire.getString("isImage").toString();
				
				
				result += "\nQuestion ID: " + questionID + "\nQuestion:  "
						+ question + "\nType: " + type + "\nImages: " + isImage;
				result += "\nAnswers: \n";

				
				
				for (int i = 0; i < answers.length(); i++) {
					
					//answers.get(0).get

					result += i+1+"."+answers.get(0).toString()+"\n";
				}
			
				//SKIPPED THIS FOR NOW KAZIM
			/*result += "\nImage URL: \n";
				for (int i = 0; i <images.length(); i++) {

					result += i+1+"."+images.get(i).toString()+"\n";
				}
				
				
				
				result += "\nText to be Highlighted: \n";
				for (int i = 0; i <TextHighlight.length(); i++) {

					result += i+1+"."+TextHighlight.get(i).toString()+"\n";}
			 * 	
			 */
				
				
>>>>>>> parent of 9c7b606... Changed Quite a lot
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
<<<<<<< HEAD
			  
						
		
				
					
				
			
			
			
			
			
			
			
			
			
			return null;
		}

		
		
=======
			
			return result;
		}

>>>>>>> parent of 9c7b606... Changed Quite a lot
	}
}
