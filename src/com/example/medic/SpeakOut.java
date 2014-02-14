package com.example.medic;

import java.util.Locale;

import android.content.Context;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.widget.Toast;

public class SpeakOut implements OnInitListener {

	TextToSpeech texts;
	Context context;
	String string;
	int MY_DATA_CHECK_CODE = 0;

	public SpeakOut(Context c, int CODE, String string) {
		this.context = c;
		this.MY_DATA_CHECK_CODE = CODE;
		this.string = string;
		
		
		Intent checkTTSIntent = new Intent();
		checkTTSIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		checkTTSIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
		context.startActivity(checkTTSIntent);
		if(texts==null){
		texts = new TextToSpeech(context, this);
		}

	}
	public void texttospeach(String string) {	
		texts.speak(string, TextToSpeech.QUEUE_FLUSH, null);
	}
	

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == MY_DATA_CHECK_CODE) {
			if (texts == null) {
				Intent installIntent = new Intent();
				installIntent
						.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
				context.startActivity(installIntent);
			}
		}
	}

	@Override
	public void onInit(int status) {
		if (status == TextToSpeech.SUCCESS) {
			if (texts.isLanguageAvailable(Locale.UK) == TextToSpeech.LANG_AVAILABLE)
				texts.setLanguage(Locale.US);
			texttospeach(string);
		} else if (status == TextToSpeech.ERROR) {
			Toast.makeText(context, "Sorry! Text To Speech failed...",
					Toast.LENGTH_LONG).show();
		}

	}

	

}
