package com.example.pnuapp;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

public class SetTimeTable extends Activity {
	private Spinner spinnerDay, spinnerTime, spinnerMajor, spinnerGrade, spinnerLecType;
	private Button backButton, searchButton;
	private ListView lecList;
	private ArrayList<String> listElement;
	private ArrayAdapter<String> listAdapter;
	private SQLiteDatabase lecDB;
	private Cursor lecCursor;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.timetable_set);
		
		//뒤로가기 버튼
		backButton = (Button)findViewById(R.id.sttBackButton);
		backButton.setOnClickListener(new OnClickListener() {
        	@Override
        	public void onClick(View v) {
        		Intent intent = new Intent();
        		setResult(0, intent);
        		finish();
        	}
        });
		
		//조건 선택
		spinnerDay = (Spinner)findViewById(R.id.spinnerDay);
		spinnerDay.setPrompt("요일 선택");
		ArrayAdapter adapterDay = ArrayAdapter.createFromResource(this, R.array.day, android.R.layout.simple_spinner_item);
		adapterDay.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerDay.setAdapter(adapterDay);
		
		spinnerTime = (Spinner)findViewById(R.id.spinnerTime);
		spinnerTime.setPrompt("시간 선택");
		ArrayAdapter adapterTime = ArrayAdapter.createFromResource(this, R.array.time, android.R.layout.simple_spinner_item);
		adapterTime.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerTime.setAdapter(adapterTime);
		
		spinnerMajor = (Spinner)findViewById(R.id.spinnerMajor);
		spinnerMajor.setPrompt("학과 선택");
		ArrayAdapter adapterMajor = ArrayAdapter.createFromResource(this, R.array.major, android.R.layout.simple_spinner_item);
		adapterMajor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerMajor.setAdapter(adapterMajor);
		
		spinnerGrade = (Spinner)findViewById(R.id.spinnerGrade);
		spinnerGrade.setPrompt("학년 선택");
		ArrayAdapter adapterGrade = ArrayAdapter.createFromResource(this, R.array.grade, android.R.layout.simple_spinner_item);
		adapterGrade.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerGrade.setAdapter(adapterGrade);
		
		spinnerLecType = (Spinner)findViewById(R.id.spinnerLecType);
		spinnerLecType.setPrompt("강의 유형 선택");
		ArrayAdapter adapterLecType = ArrayAdapter.createFromResource(this, R.array.lecType, android.R.layout.simple_spinner_item);
		adapterLecType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerLecType.setAdapter(adapterLecType);
		
		//탐색 버튼
		searchButton = (Button)findViewById(R.id.searchButton);
		searchButton.setOnClickListener(new OnClickListener() {
        	@Override
        	public void onClick(View v) {
        		String day = ""+spinnerDay.getSelectedItem();
        		String time = ""+spinnerTime.getSelectedItem();
        		String major = ""+spinnerMajor.getSelectedItem();
        		String grade = ""+spinnerGrade.getSelectedItem();
        		String lecType = ""+spinnerLecType.getSelectedItem();
        		showList(day, time, major, grade, lecType);
        	}
        });
		
		//결과 리스트
		lecList = (ListView)findViewById(R.id.lecList);
		listElement = new ArrayList<String>();
		listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listElement);
		lecList.setAdapter(listAdapter);
		lecList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView parent, View view, int position, long id) {
				Intent intent = new Intent();
				String element = listElement.get(position);
				intent.putExtra("lecture", element);
				intent.putExtra("day", parseDay(element));
				intent.putExtra("time", parseTime(element));
				intent.putExtra("duration", parseDuration(element));
				setResult(0, intent);
				finish();
			}
		});
		
		//DB open
        lecDB = openOrCreateDatabase("lectures.sqlite", Context.MODE_PRIVATE, null);
	}
	
	private void showList(String day, String time, String major, String grade, String lecType) {
		//query 준비
		String query = "SELECT * FROM lecture141 WHERE ";
		query += "department='" + major + "'";
		if(!grade.equals("ALL"))
			query += " AND grade='" + grade + "'";
		if(!lecType.equals("ALL"))
			query += " AND division='" + lecType + "'";
		
		Log.i("dbdb", query);
		
		//db에 쿼리 적용
		listElement.clear();
		lecCursor = lecDB.rawQuery(query, null);
		lecCursor.moveToFirst();

		//커서의 결과 리스트에 저장
		String col14;
		String pDay;
		String pTime;
		String pDuration;
		for(int i=0; i<lecCursor.getCount(); i++) {
			col14 = lecCursor.getString(14);			
			
			if(col14 != null) {
				pDay = parseDay(col14);
				pTime = parseTime(col14);
				pDuration = parseDuration(col14);

				if(day.equals("ALL") || day.equals(parseDay(col14)))
					if(time.equals("ALL") || time.equals(parseTime(col14)))
						listElement.add(lecCursor.getString(6) + ", " + pDay + ", " + pTime + "(" + pDuration + ")");
			}
			else
				listElement.add(lecCursor.getString(6));
			
			lecCursor.moveToNext();
		}
		listAdapter.notifyDataSetChanged();
		lecList.setSelection(0);
	}
	
	private String parseDay(String col14) {
		if(col14.matches(".*월.*") && col14.matches(".*수.*"))
			return "월수";
		else if(col14.matches(".*화.*") && col14.matches(".*목.*"))
			return "화목";
		else if(col14.matches(".*월.*") && !col14.matches(".*수.*"))
			return "월";
		else if(col14.matches(".*화.*") && !col14.matches(".*목.*"))
			return "화";
		else if(!col14.matches(".*월.*") && col14.matches(".*수.*"))
			return "수";
		else if(!col14.matches(".*화.*") && col14.matches(".*목.*"))
			return "목";
		else if(col14.matches(".*금,*"))
			return "금";
		return "";
	}
	
	private String parseTime(String col14) {
		String times[] = {"8:00", "9:00", "10:30", "12:00", "13:00", "13:30", "14:00"
							,"14:30", "15:00", "15:30", "16:00", "16:30", "18:00", "18:30" };
		
		for(int i=0; i<times.length; i++)
			if(col14.matches(".*"+times[i]+".*"))
				return times[i];
		
		return null;
	}
	
	private String parseDuration(String col14) {
		String durations[] = {"75", "100", "180"};
		
		for(int i=0; i<durations.length; i++)
			if(col14.matches(".*"+durations[i]+".*"))
				return durations[i];
		
		return "180";
	}
}
