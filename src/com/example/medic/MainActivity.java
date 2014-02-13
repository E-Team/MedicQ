package com.example.medic;

import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.ZoomControls;
import android.view.Window;
import android.view.WindowManager.LayoutParams;

public class MainActivity extends Activity implements OnInitListener,
		OnClickListener {
	
	
	static String K = "Karim is my Sidekick";

	private int MY_DATA_CHECK_CODE = 0;
	TextToSpeech tts;
	TextView tv;
	ToggleButton readOutQuestionsTButton;
	Button increaseBrightness;
	Button decreaseBrightness;
	private int currentBrightness = 40; // values between 20(min) & 100(max) -
										// default is 40

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		increaseBrightness = (Button) findViewById(R.id.increase_brightness_button);
		decreaseBrightness = (Button) findViewById(R.id.decrease_brightness_button);
		Button beginbutton = (Button) findViewById(R.id.beginQbutton);
		beginbutton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getBaseContext(),
						questionnaire.class);
				
				startActivity(i);
				
			}
		});

		// Allows Button clicks to change the Brightness
		increaseBrightness.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				changeBrightness(true);
			}
		});
		decreaseBrightness.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				changeBrightness(false);
			}
		});

		Intent checkTTSIntent = new Intent();
		checkTTSIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
		startActivityForResult(checkTTSIntent, MY_DATA_CHECK_CODE);

		tv = (TextView) findViewById(R.id.textView1);
		tv.setTextSize((float) 30.0);

		final ToggleButton voiceCommandsTButton = (ToggleButton) findViewById(R.id.voice_Commands_TButton);
		voiceCommandsTButton
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						if (voiceCommandsTButton.isChecked()) {

							texttospeach("voice commands activated");
						}
						;
						if (!voiceCommandsTButton.isChecked()) {
							texttospeach("voice commands dee activated");
						}
						;

					}
				});
		readOutQuestionsTButton = (ToggleButton) findViewById(R.id.readOut_Questions_TButton);
		readOutQuestionsTButton.setOnClickListener(this);

		ZoomControls zc = (ZoomControls) findViewById(R.id.zoomControls1);
		zc.setOnZoomInClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				float x = tv.getTextSize();
				Log.d(K, x + " zoom in  " + x + 10);
				if (x < 80)
					tv.setTextSize(TypedValue.COMPLEX_UNIT_PX,
							(float) (x + 10));
			}
		});

		zc.setOnZoomOutClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				float x = tv.getTextSize();
				Log.d(K, x + " Zoom out  " + x + 10);
				if (x > 40)
					tv.setTextSize(TypedValue.COMPLEX_UNIT_PX,
							(float) (x - 10));
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onInit(int status) {
		if (status == TextToSpeech.SUCCESS) {
			if (tts.isLanguageAvailable(Locale.UK) == TextToSpeech.LANG_AVAILABLE)
				tts.setLanguage(Locale.US);
		} else if (status == TextToSpeech.ERROR) {
			Toast.makeText(this, "Sorry! Text To Speech failed...",
					Toast.LENGTH_LONG).show();
		}
	}

	@Override
	public void onClick(View v) {
		if (readOutQuestionsTButton.isChecked()) {
			texttospeach(tv.getText().toString());
		}
		if (!readOutQuestionsTButton.isChecked()) {
			tts.stop();
		}

	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == MY_DATA_CHECK_CODE) {
			if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
				// the user has the necessary data - create the TTS
				tts = new TextToSpeech(this, this);
			} else {
				// no data - install it now
				Intent installTTSIntent = new Intent();
				installTTSIntent
						.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
				startActivity(installTTSIntent);
			}
		}
	}

	private void texttospeach(String string) {
		tts.speak(string, TextToSpeech.QUEUE_FLUSH, null);

	}

	// Changes current brightness of window. If (isIncrease=true) then
	// {brightness+20%}, else {brightness-20%}
	public void changeBrightness(boolean isIncrease) {
		Window currentWindow = getWindow();
		if (isIncrease) {
			if (currentBrightness != 100) { // since 100 is the maximum
											// brightness
				currentBrightness = currentBrightness + 20;
			}
		} else {
			if (currentBrightness != 20) { // since 20 is the minimum brightness
				currentBrightness = currentBrightness - 20;
			}
		}
		LayoutParams layoutpars = currentWindow.getAttributes();
		// change currentBrightness
		layoutpars.screenBrightness = currentBrightness / (float) 100;
		// apply brightness changes
		currentWindow.setAttributes(layoutpars);
	}
}
