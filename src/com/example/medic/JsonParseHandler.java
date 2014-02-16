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

import com.example.medicObjects.SurveyQEntiry;

import android.util.Log;

public class JsonParseHandler {
	private String URL;
	private JSONArray globalArray;
	ArrayList<JSONArray> js;

	public JsonParseHandler(String URL) {
		this.URL = URL;
	}

	public JSONArray topLevelJSON() {
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

	public JSONObject getPatientDetailObject() {
		JSONObject patientObj = null;
		try {
			patientObj = topLevelJSON().getJSONArray(0).getJSONObject(0);
		} catch (JSONException e) {
		}
		return patientObj;
	}

	public ArrayList<JSONArray> allQsANDAnsJSONArray() {
		JSONArray allQuestionAnswers = null;
		js = new ArrayList<JSONArray>();
		try {
			for (int i = 1; i < topLevelJSON().length(); i++) {
				allQuestionAnswers = topLevelJSON().getJSONArray(i);
				js.add(allQuestionAnswers);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return js;
	}

	public ArrayList<JSONObject> getQuestionsArray() {

		JSONObject questionnaireObj = null;
		ArrayList<JSONObject> qArrays = new ArrayList<JSONObject>();
		try {
			int x = allQsANDAnsJSONArray().size();

			for (int i = 0; i < x; i++) {
				JSONObject questionnaireArray = allQsANDAnsJSONArray().get(i)
						.getJSONObject(0);
				questionnaireObj = questionnaireArray
						.getJSONObject("QuestionInfo");
				qArrays.add(questionnaireObj);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return qArrays;
	}

	public ArrayList<JSONArray> getAnswerArray() {
		JSONArray questionnaireObj = null;
		ArrayList<JSONArray> AArrays = new ArrayList<JSONArray>();
		try {
			int x = allQsANDAnsJSONArray().size();
			for (int i = 0; i < x; i++) {
				JSONObject questionnaireArray = allQsANDAnsJSONArray().get(i)
						.getJSONObject(0);
				questionnaireObj = questionnaireArray.getJSONArray("Answers");
				AArrays.add(questionnaireObj);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return AArrays;
	}

	public JSONArray getImagesArray() {
		JSONArray imageObj = null;
		try {
			JSONArray image = allQsANDAnsJSONArray().get(0);
			JSONObject questionnaireArray = image.getJSONObject(0);
			imageObj = questionnaireArray.getJSONArray("Images");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return imageObj;
	}

	public JSONArray getTextHighlightArray() {
		JSONArray highlightObj = null;
		try {
			JSONArray textHighlight = allQsANDAnsJSONArray().get(0);
			JSONObject highlightArray = textHighlight.getJSONObject(0);
			highlightObj = highlightArray.getJSONArray("textFormat");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return highlightObj;
	}

}
