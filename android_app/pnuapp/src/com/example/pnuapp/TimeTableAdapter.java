package com.example.pnuapp;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

public class TimeTableAdapter extends BaseAdapter {
	private Context mContext;
	private int count = 231;
	private String[] dayHeader = {"시간", "월", "화", "수", "목", "금", "토"};
	private String[] hours = {"", "8", "9", "10", "11", "12", "13", "14", "15"
								, "16", "17", "18", "19", "20", "21", "22", "23"};
	
	private String cell[][] = new String[33][7];
	
	TimeTableAdapter(Context context) {
		mContext = context;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return count;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View oldView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View v = null;
		
		if(oldView == null) {
			v = new TextView(mContext);
			v.setLayoutParams(new GridView.LayoutParams(40, 60));
		}
		else {
			if(position < 7) {
				v = new TextView(mContext);
				((TextView)v).setGravity(Gravity.CENTER);
				((TextView)v).setText(dayHeader[position]);
				v.setBackgroundColor(Color.GRAY);
			}
			else if(position%7 == 0) {
				String time = ((position/7)%2 == 1) ? hours[(position/7)/2+1] +":00" : hours[(position/7)/2]+":30";
						
				v = new TextView(mContext);
				((TextView)v).setGravity(Gravity.CENTER);
				((TextView)v).setText(time);
				v.setBackgroundColor(Color.GRAY);
			}
			else if(position < count) {
				v = new TextView(mContext);
				((TextView)v).setGravity(Gravity.CENTER);
				((TextView)v).setText(cell[position/7][position%7]);
			}
			else {
				v = oldView;
			}
		}
		return v;
	}

	public void addLecture(String lecture, String day, String time, String duration) {
		ArrayList<Integer> col = new ArrayList<Integer>();
		ArrayList<Integer> row = new ArrayList<Integer>();
		int dur;
		
		//요일 위치
		if(day.matches(".*월.*"))
			col.add(1);
		if(day.matches(".*화.*"))
			col.add(2);
		if(day.matches(".수.*"))
			col.add(3);
		if(day.matches(".*목.*"))
			col.add(4);
		if(day.matches(".*금.*"))
			col.add(5);
		
		//시간 위치
		for(int i=0; i<hours.length; i++)
			if(time.equals(hours[i]+":00"))
				row.add(i*2 - 1);
			else if(time.equals(hours[i]+":30"))
				row.add(i*2);
		
		//수업 길이
		dur = Integer.valueOf(duration)/30;
		
		for(int i=0; i<row.size(); i++)
			for(int j=0; j<col.size(); j++) {
				cell[row.get(i)][col.get(j)] = lecture;
				for(int k=0; k<dur; k++)
					cell[row.get(i)+1+k][col.get(j)] = "...";
			}
		
		this.notifyDataSetChanged();
		
		Log.i("mainmain", ""+col.get(0)+" " + row.get(0));
		Log.i("mainmain", ""+cell[row.get(0)][col.get(0)]);
	}
}
