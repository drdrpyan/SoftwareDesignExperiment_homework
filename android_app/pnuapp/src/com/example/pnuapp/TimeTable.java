package com.example.pnuapp;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

public class TimeTable extends Activity {
	private Button setButton;
	private ListView list;
	private GridView timeTable;
	private TimeTableAdapter ttAdapter;
	static final int REQUEST_CODE = 0;
	TextView tv;
	
	public static final String DB_PATH = "/data/data/com.example.pnuapp/databases/";
	public static final String DB_NAME = "lectures.sqlite";
	
    
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.timetable_main);
		
		timeTable = (GridView)findViewById(R.id.timeTable);
		ttAdapter = new TimeTableAdapter(this);
		timeTable.setAdapter(ttAdapter);
		
//		combineFiles();
		
//		initialize(getApplicationContext());
        
		tv = (TextView)findViewById(R.id.textView);
		tv.setText("");
		
        setButton = (Button)findViewById(R.id.setButton);
        setButton.setOnClickListener(new OnClickListener() {
        	@Override
        	public void onClick(View v) {
        		Intent intent = new Intent(TimeTable.this, SetTimeTable.class);
        		startActivityForResult(intent, REQUEST_CODE);
        	}
        });
        
        //db 테스트, 병합
        SQLiteDatabase checkDB = null;
        try {        	
        	String dbPath = DB_PATH + DB_NAME;
        	checkDB = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READONLY);        
        }catch(SQLiteException e) {
        	e.printStackTrace();
        }
        if(checkDB != null)
        	checkDB.close();
        else
        	assembleDB();
	}
	
	@Override
	protected void onActivityResult(int request, int resultCode, Intent data) {
		try {
			tv.setText(data.getStringExtra("lecture") + "추가됨");
			ttAdapter.addLecture(data.getStringExtra("lecture"), data.getStringExtra("day"), data.getStringExtra("time"), data.getStringExtra("duration"));
		}catch(Exception e){
			tv.setText("");
		}
	}
	
	private void assembleDB() {
		Log.i("TimeTable", "assembleDB");
		try {
			InputStream input1 = getApplicationContext().getAssets().open(DB_NAME + ".0");
			InputStream input2 = getApplicationContext().getAssets().open(DB_NAME + ".1");
			OutputStream output = new FileOutputStream(DB_PATH + DB_NAME);
			
			byte[] buffer = new byte[1024];
			int length;
			
			while((length = input1.read(buffer)) > 0) {
				output.write(buffer, 0, length);
			}
			output.flush();
			while((length = input2.read(buffer)) > 0) {
				output.write(buffer, 0, length);
			}
			output.flush();
			output.close();
			input1.close();
			input2.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}