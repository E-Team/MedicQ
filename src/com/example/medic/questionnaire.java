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
import android.widget.Toast;

public class questionnaire extends Activity {

	TextView tv;
	ArrayList<String> questions;
	ArrayList<String> answers;
	String g = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.questions);
		new Executer().execute();
		tv = (TextView) findViewById(R.id.textView1);
	}

	private class Executer extends AsyncTask<String, String, String> {
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			if(result == null){
			super.onPostExecute(result);
				Toast.makeText(getApplicationContext(), "RESULT EMPTY", Toast.LENGTH_SHORT).show();
			}else{
				tv.setText(result + "\n");
				
			}
		}

		@Override
		protected String doInBackground(String... arg0) {
			JSONParser jsonHandler = new JSONParser("http://ismailzd.co.uk/JSONData.txt");
			JSONObject patientList = jsonHandler.patientDetails();
			JSONObject questionnaire = jsonHandler.patientQuestionnaire();
			JSONArray answers = jsonHandler.patientAnswers();
			JSONArray images = jsonHandler.patientImage();
			JSONArray TextHighlight = jsonHandler.patientTextHighlight();
			String result = "";
			try {
				String patientID = patientList.getString("patientID")
						.toString();
				String name = patientList.getString("patientName").toString();
				String gender = patientList.getString("gender").toString();
				String questionNum = patientList.getString("totalquestions")
						.toString();
				System.out.println(name);
				result = "Patient ID: " + patientID + " \nName: " + name
						+ "\nGender: " + gender + "\nQuestion Number: "
						+ questionNum;
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

					result += i+1+"."+answers.get(i).toString()+"\n";
				}
				result += "\nImage URL: \n";
				for (int i = 0; i <images.length(); i++) {

					result += i+1+"."+images.get(i).toString()+"\n";
				}
				
				result += "\nText to be Highlighted: \n";
				for (int i = 0; i <TextHighlight.length(); i++) {

					result += i+1+"."+TextHighlight.get(i).toString()+"\n";
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return result;
		}

	}
}
