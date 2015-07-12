package com.example.pnuapp;

import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;


public class MainActivity extends Activity {

	private Backend backend;
	public Button btn1;	
	public Button btn2;	
	public Button btn3;	
	public Button btn4;	
	public Button btn5;
	public Button btn6;
	private TextToSpeech tts;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_total);



		backend = new Backend(this);
		btn1 = (Button)findViewById(R.id.btn1);
		btn2 = (Button)findViewById(R.id.btn2);
		btn3 = (Button)findViewById(R.id.btn3);	
		btn4 = (Button)findViewById(R.id.btn4);	
		btn5 = (Button)findViewById(R.id.btn5);
		btn6 = (Button)findViewById(R.id.btn6);		
		try {
			tts.setLanguage(Locale.US);
			tts.speak("Hello~", TextToSpeech.QUEUE_FLUSH, null);
		}catch(Exception e) {
			
		}
		
		btn1.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v){
				Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.pusan.ac.kr/uPNU_common/common_boardPopup.asp?conf_no=3"));
				startActivity(intent);
				try {
					tts.setLanguage(Locale.US);
					tts.speak("Notice", TextToSpeech.QUEUE_FLUSH, null);
				}catch(Exception e) {
					
				}
			}
		});

		btn2.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v){
				Intent intent = new Intent(MainActivity.this, Schedule.class);
				startActivity(intent);
				try {
					tts.setLanguage(Locale.US);
					tts.speak("Schedule", TextToSpeech.QUEUE_FLUSH, null);
				}catch(Exception e) {
					
				}
			}
		});


		btn3.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v){
				Intent intent = new Intent(MainActivity.this, Homepage.class);
				startActivity(intent);
				try {
					tts.setLanguage(Locale.US);
					tts.speak("Homepage", TextToSpeech.QUEUE_FLUSH, null);
				}catch(Exception e) {
					
				}

			}
		});

		btn4.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v){
				Intent intent = new Intent(MainActivity.this, Pnucall.class);
				startActivity(intent);
				try {
					tts.setLanguage(Locale.US);
					tts.speak("PNU call", TextToSpeech.QUEUE_FLUSH, null);
				}catch(Exception e) {
					
				}

			}
		});

		btn5.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, TimeTable.class);
				startActivity(intent);
				try {
					tts.setLanguage(Locale.US);
					tts.speak("Time Table", TextToSpeech.QUEUE_FLUSH, null);
				}catch(Exception e) {
					
				}
			}
		});
		
		btn6.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, Webview.class);
				startActivity(intent);
				try {
					tts.setLanguage(Locale.US);
					tts.speak("Club Activity", TextToSpeech.QUEUE_FLUSH, null);
				}catch(Exception e) {
					
				}
			}
		});
	}

	public void onBackPressed(){
		backend.onBackPressed();
	}
}

