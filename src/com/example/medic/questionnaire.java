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
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			tv.setText(g + "\n");
			
			for(int i=0;i<questions.size();i++){
				tv.setText(questions.get(i)+"\n");
			}
		}

		@Override
		protected String doInBackground(String... arg0) {
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
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  
						
		
				
					
				
			
			
			
			
			
			
			
			
			
			return null;
		}

		
		
	}
}
