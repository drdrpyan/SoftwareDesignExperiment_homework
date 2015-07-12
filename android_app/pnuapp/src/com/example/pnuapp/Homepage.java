package com.example.pnuapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;

public class Homepage extends Activity {
	
	public Button Button01;	
	public Button Button02;
	public Button Button03;
	public Button Button04;
	public Button Button05;
	public Button Button06;	
	public Button Button07;
	public Button Button08;
	public Button Button09;
	public Button Button10;
	public Button Button11;	
	public Button Button12;
	public Button Button13;
	public Button Button14;	
	
@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.homepage_layout);
	
	Button01 = (Button)findViewById(R.id.Button01);
	Button02 = (Button)findViewById(R.id.Button02);
	Button03 = (Button)findViewById(R.id.Button03);
	Button04 = (Button)findViewById(R.id.Button04);
	Button05 = (Button)findViewById(R.id.Button05);
	Button06 = (Button)findViewById(R.id.Button06);
	Button07 = (Button)findViewById(R.id.Button07);
	Button08 = (Button)findViewById(R.id.Button08);
	Button09 = (Button)findViewById(R.id.Button09);
	Button10 = (Button)findViewById(R.id.Button10);
	Button11 = (Button)findViewById(R.id.Button11);
	Button12 = (Button)findViewById(R.id.Button12);
	Button13 = (Button)findViewById(R.id.Button13);
	Button14 = (Button)findViewById(R.id.Button14);
	
	Button01.setOnClickListener(new Button.OnClickListener() {
		public void onClick(View v){
		    Intent intent = new Intent(Homepage.this, NurseHome.class);
			startActivity(intent);		
			
		}
	});
	
	Button02.setOnClickListener(new Button.OnClickListener() {
		public void onClick(View v){
		    Intent intent = new Intent(Homepage.this, BusinessHome.class);
			startActivity(intent);		
			
		}
	});
	Button03.setOnClickListener(new Button.OnClickListener() {
		public void onClick(View v){
		    Intent intent = new Intent(Homepage.this, EconomyHome.class);
			startActivity(intent);		
			
		}
	});
	Button04.setOnClickListener(new Button.OnClickListener() {
		public void onClick(View v){
		    Intent intent = new Intent(Homepage.this, EngineHome.class);
			startActivity(intent);		
			
		}
	});
	Button05.setOnClickListener(new Button.OnClickListener() {
		public void onClick(View v){
		    Intent intent = new Intent(Homepage.this, NanoHome.class);
			startActivity(intent);		
			
		}
	});
	Button06.setOnClickListener(new Button.OnClickListener() {
		public void onClick(View v){
		    Intent intent = new Intent(Homepage.this, TeachHome.class);
			startActivity(intent);		
			
		}
	});
	Button07.setOnClickListener(new Button.OnClickListener() {
		public void onClick(View v){
		    Intent intent = new Intent(Homepage.this, SoscienceHome.class);
			startActivity(intent);		
			
		}
	});
	Button08.setOnClickListener(new Button.OnClickListener() {
		public void onClick(View v){
		    Intent intent = new Intent(Homepage.this, LifescienceHome.class);
			startActivity(intent);		
			
		}
	});
	Button09.setOnClickListener(new Button.OnClickListener() {
		public void onClick(View v){
		    Intent intent = new Intent(Homepage.this, EnviromentHome.class);
			startActivity(intent);		
			
		}
	});
	Button10.setOnClickListener(new Button.OnClickListener() {
		public void onClick(View v){
		    Intent intent = new Intent(Homepage.this, SportHome.class);
			startActivity(intent);		
			
		}
	});
	Button11.setOnClickListener(new Button.OnClickListener() {
		public void onClick(View v){
		    Intent intent = new Intent(Homepage.this, MedicineHome.class);
			startActivity(intent);		
			
		}
	});
	Button12.setOnClickListener(new Button.OnClickListener() {
		public void onClick(View v){
		    Intent intent = new Intent(Homepage.this, ArtHome.class);
			startActivity(intent);		
			
		}
	});
	Button13.setOnClickListener(new Button.OnClickListener() {
		public void onClick(View v){
		    Intent intent = new Intent(Homepage.this, HumanHome.class);
			startActivity(intent);		
			
		}
	});
	Button14.setOnClickListener(new Button.OnClickListener() {
		public void onClick(View v){
		    Intent intent = new Intent(Homepage.this, NatureHome.class);
			startActivity(intent);		
			
		}
	});
}
}
