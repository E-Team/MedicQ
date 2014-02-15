package com.example.medic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class JSONParser {
	private String URL;
	private JSONArray globalArray;
	private JSONObject userdata;

	public JSONParser(String URL) {
		this.URL = URL;
	}

	public JSONArray arrayData() {
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(URL);
		StringBuilder content = new StringBuilder();
		try {
			HttpResponse response = client.execute(get);
			int responseCode = response.getStatusLine().getStatusCode();
			if (responseCode == 200) {
				InputStream in = response.getEntity().getContent();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(in));
				String readLine = reader.readLine();
				while (readLine != null) {
					content.append(readLine);
					readLine = reader.readLine();
				}
				/**
				 * Important
				 */
				try {
					globalArray = new JSONArray(content.toString());
				} catch (JSONException e) {
					e.printStackTrace();
				}
			} else {
				Log.w("DATA RETRIEVAL",
						"Unable to read data.  HTTP response code = "
								+ responseCode);
				content = null;
			}
		} catch (ClientProtocolException e) {
			Log.e("readData", "ClientProtocolException:\n" + e.getMessage());
		} catch (IOException e) {
			Log.e("readData", "IOException:\n+e.getMessage()");
		}
		return globalArray;
	}

	public JSONObject patientDetails() {
		System.out.println(arrayData().toString());
		JSONObject patientObj = null;
		try {
			JSONArray patientGlobal = arrayData().getJSONArray(0);
			patientObj = patientGlobal.getJSONObject(0);

		} catch (JSONException e) {
		}
		return patientObj;
	}
	public JSONArray accessToMainObject() {
		JSONArray questionnaire = null;
		try {
			if (patientDetails().getString("gender").equals("female")) {
				questionnaire = arrayData().getJSONArray(1);// Female JSON
			} else {
				questionnaire = arrayData().getJSONArray(2);// Male JSON
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return questionnaire;
	}
	public JSONObject patientQuestionnaire() {
		JSONObject questionnaireObj = null;
		try {
			JSONArray questionnaire = accessToMainObject();
			JSONObject questionnaireArray = questionnaire.getJSONObject(0);
			questionnaireObj = questionnaireArray.getJSONObject("QuestionInfo");

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return questionnaireObj;
	}

	public JSONArray patientAnswers() {
		JSONArray questionnaireObj = null;
		try {
			JSONArray questionnaire = accessToMainObject();// Male JSON
			JSONObject questionnaireArray = questionnaire.getJSONObject(0);
			questionnaireObj = questionnaireArray.getJSONArray("Answers");
			Log.v("Questionnaire Answers", questionnaireObj.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return questionnaireObj;
	}
	public JSONArray patientImage() {
		JSONArray imageObj = null;
		try {
			JSONArray image = accessToMainObject();
			JSONObject questionnaireArray = image.getJSONObject(0);
			imageObj = questionnaireArray.getJSONArray("Images");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return imageObj;
	}
	public JSONArray patientTextHighlight() {
		JSONArray highlightObj = null;
		try {
			JSONArray textHighlight = accessToMainObject();
			JSONObject highlightArray = textHighlight.getJSONObject(0);
			highlightObj = highlightArray.getJSONArray("textFormat");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return highlightObj;
	}

	

	

}
