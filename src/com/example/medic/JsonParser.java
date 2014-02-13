package com.example.medic;

import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;

public class JsonParser {
	String URL;
	String result;
	ArrayList<JSONObject> list;
	JSONArray jRay;
	JSONObject userdata;

	public JsonParser(String URL) {
		this.URL = URL;
	}

	public ArrayList<JSONObject> getQuestionData() {
		
		list = new ArrayList<JSONObject>();
		
		
		DefaultHttpClient httpclient = new DefaultHttpClient(
				new BasicHttpParams());
		HttpGet httpget = new HttpGet(URL);

		try {
			HttpResponse response = httpclient.execute(httpget);
			int stat = response.getStatusLine().getStatusCode();
			HttpEntity entity = null;
			if (stat == HttpStatus.SC_OK) {
				entity = response.getEntity();
			}
			jRay = new JSONArray(EntityUtils.toString(entity));
			Log.d("JRAY", jRay.toString());
			
			JSONArray j1 = jRay.getJSONArray(1);
			userdata = (JSONObject) jRay.get(0);
			for (int i = 0; i < j1.length(); i++) {	
			list.add(j1.getJSONObject(i));
			}

		} catch (Exception e) {
			Log.d("JSON Exc", e.toString());
			result = "nothing";
		}
		return list;
	}	
	
	public JSONObject getuserdata(){
		return userdata;
	}

}

