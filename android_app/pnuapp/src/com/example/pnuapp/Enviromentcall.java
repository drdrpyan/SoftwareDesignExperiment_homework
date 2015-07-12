package com.example.pnuapp;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Comparator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebSettings;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Enviromentcall extends Activity {

	// 리스트뷰 선언
	private ListView listview;
	// 데이터를 연결할 Adapter
	DataAdapter adapter;
	// 데이터를 담을 자료구조
	ArrayList<CData> alist;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.homecall_list);

		// 선언한 리스트뷰에 사용할 리스뷰연결
		listview = (ListView) findViewById(R.id.nurselist);
		// 객체를 생성합니다
		alist = new ArrayList<CData>();
		// 데이터를 받기위해 데이터어댑터 객체 선언
		adapter = new DataAdapter(this, alist);
		// 리스트뷰에 어댑터 연결
		listview.setAdapter(adapter);

		// ArrayAdapter를 통해서 ArrayList에 자료 저장
		// 하나의 String값을 저장하던 것을 CData클래스의 객체를 저장하던것으로 변경
		// CData 객체는 생성자에 리스트 표시 텍스트뷰1의 내용과 텍스트뷰2의 내용 그리고 사진이미지값을 어댑터에 연결

		// CData클래스를 만들때의 순서대로 해당 인수값을 입력
		// 한줄 한줄이 리스트뷰에 뿌려질 한줄 한줄이라고 생각하면 됨
		adapter.add(new CData(getApplicationContext(), "아동가족학과",
				"전화번호 : 051-510-1717", R.drawable.telephone));
		adapter.add(new CData(getApplicationContext(), "의류학과",
				"전화번호 : 051-510-1719", R.drawable.ic_launcher));
		adapter.add(new CData(getApplicationContext(), "식품영양학과",
				"전화번호 : 051-510-1718", R.drawable.ic_launcher));
		adapter.add(new CData(getApplicationContext(), "주거환경학과",
				"전화번호 : 051-510-1711", R.drawable.ic_launcher));
	}

	private class DataAdapter extends ArrayAdapter<CData> {
		// 레이아웃 XML을 읽어들이기 위한 객체
		private LayoutInflater mInflater;
		public DataAdapter(Context context, ArrayList<CData> object) {
			// 상위 클래스의 초기화 과정
			// context, 0, 자료구조
			super(context, 0, object);
			mInflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}

		// 보여지는 스타일을 자신이 만든 xml로 보이기 위한 구문
		@Override
		public View getView(int position, View v, ViewGroup parent) {
			View view = null;
			// 현재 리스트의 하나의 항목에 보일 컨트롤 얻기
			if (v == null) {
				// XML 레이아웃을 직접 읽어서 리스트뷰에 넣음
				view = mInflater.inflate(R.layout.homecall_listitem, null);
			} else {
				view = v;
			}

			// 자료를 받는다.
			final CData data = this.getItem(position);

			if (data != null) {
				// 화면 출력
				TextView tv = (TextView) view.findViewById(R.id.nurseClass);
				TextView tv2 = (TextView) view.findViewById(R.id.nurseNumber);
				// 텍스트뷰1에 getName()을 출력 즉 첫번째 인수값
				tv.setText(data.getName());
				// 텍스트뷰2에 getData()을 출력 즉 두번째 인수값
				tv2.setText(data.getData());
				ImageButton iv = (ImageButton) view.findViewById(R.id.nurseImage_button);
				// 이미지뷰에 뿌려질 해당 이미지값을 연결 즉 세번째 인수값
				iv.setImageResource(data.getButton());				
				iv.setOnClickListener(new Button.OnClickListener() {
					public void onClick(View v){
					    Intent intent = new Intent(Intent.ACTION_CALL, 
					    		Uri.parse("tel:" + data.getData())); 
						startActivity(intent);		
						
					}
				});

			}
			return view;
		}
	}

	// CData안에 받은 값을 직접 할당

	class CData {

		private String classname;
		private String Dataname;
		private int buttonstart;

		public CData(Context context, String cname, String dname,
				int btnstart) {

			classname = cname;

			Dataname = dname;

			buttonstart = btnstart;

		}

		public String getName() {
			return classname;
		}

		public String getData() {
			return Dataname;
		}

		public int getButton() {
			return buttonstart;
		}
	}
}
