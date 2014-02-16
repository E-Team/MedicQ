package com.example.medic;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.medicObjects.Answers;
import com.example.medicObjects.Questions;
import com.example.medicObjects.SurveyQEntiry;
import com.example.medicObjects.patientInfo;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class questionnaire extends Activity {

	TextView tv;
	ArrayList<String> questions;
	ArrayList<String> answers;
	String g = null;
	Questions q;

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
			if (result == null) {
				super.onPostExecute(result);
				Toast.makeText(getApplicationContext(), "RESULT EMPTY",
						Toast.LENGTH_SHORT).show();
			} else {
				tv.setText(result + "\n");

			}
		}

		@Override
		protected String doInBackground(String... arg0) {
			JsonParseHandler jsonHandler = new JsonParseHandler(
					"http://ismailzd.co.uk/JSONData.txt");
			JSONObject patientList = jsonHandler.getPatientDetailObject();		
			ArrayList<JSONObject> questionArraylist = jsonHandler.getQuestionsArray();
			ArrayList<JSONArray> answers = jsonHandler.getAnswerArray();
			JSONArray images = jsonHandler.getImagesArray();
			JSONArray TextHighlight = jsonHandler.getTextHighlightArray();
			String result = "";
			
			
			
			try {

				String patientID = patientList.getString("patientID")
						.toString();
				String name = patientList.getString("patientName").toString();
				String gender = patientList.getString("gender").toString();
				String email = patientList.getString("email").toString();
				String DoB = patientList.getString("DOB").toString();
				String pnumber = patientList.getString("phoneNumber")
						.toString();

				// patient info
				ArrayList<patientInfo> patientinfoArrayList = new ArrayList<patientInfo>();
				patientInfo p = new patientInfo(name, pnumber, email, DoB,
						gender, patientID);
				patientinfoArrayList.add(p);	
				// //////////////////////////////////////////////////////////////////

				
				// Question info
				ArrayList<Questions> questionsArrayList = new ArrayList<Questions>();
				for(int i=0;i<questionArraylist.size();i++){
					String questionID = questionArraylist.get(i).getString("QuestionID")
							.toString();
					String question = questionArraylist.get(i).getString("Question")
							.toString();
					String type = questionArraylist.get(i).getString("Type").toString();
					String isImage = questionArraylist.get(i).getString("isImage")
							.toString();
					q = new Questions(questionID, type, isImage, question);
					questionsArrayList.add(q);
				}
				///////////////////////////////
				
				
				////Answers info
				ArrayList<Answers> ansArrayList = new ArrayList<Answers>();
				Answers an = null;
				for (int j = 0; j < answers.size(); j++){				
					JSONArray tempArray = answers.get(j);	
					for (int i = 0; i < tempArray.length(); i++) {
						JSONArray arrayAnswers = tempArray.getJSONArray(i);
						String answerID = arrayAnswers.get(0).toString();
						String answer = arrayAnswers.get(1).toString();	
						 an  = new Answers(answer, answerID);
						
						
					}
					ansArrayList.add(an);
					System.out.println(ansArrayList);
				}

				/////Survey Entity object
				SurveyQEntiry se = new SurveyQEntiry(questionsArrayList, ansArrayList, null, null);
				
				for (int i = 0;i<se.getQuestions().size();i++){
					System.out.println(se.getQuestions().get(i).getqType().toString());
					System.out.println(se.getQuestions().get(i).getQuestion().toString());
					System.out.println(se.getQuestions().get(i).getIsImage().toString());
					System.out.println(se.getQuestions().get(i).getQuestionid().toString());
					
					System.out.println(se.getAnsArray().get(i).getAnswer());
					System.out.println(se.getAnsArray().get(i).getAnswerid());
				}
				
				result = p.toString();
				result += q.toString();
				result += "\nAnswers: \n";
				result += an.toString()+"\n";
				// Kazim: SKIPPED THIS FOR NOW
				/*
				 * result += "\nImage URL: \n"; for (int i = 0; i
				 * <images.length(); i++) {				 
				 * result += i+1+"."+images.get(i).toString()+"\n"; }
				 * result += "\nText to be Highlighted: \n"; for (int i = 0; i
				 * <TextHighlight.length(); i++) {
				 * 
				 * result += i+1+"."+TextHighlight.get(i).toString()+"\n";}
				 */

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return result;
		}
	}
}
