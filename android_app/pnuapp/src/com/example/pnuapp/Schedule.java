package com.example.pnuapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;


public class Schedule extends Activity 
implements OnClickListener{
    private ViewPager mPager;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule_layout);         
        setLayout();         
        mPager = (ViewPager)findViewById(R.id.pager);
        mPager.setAdapter(new PagerAdapterClass(getApplicationContext()));
    }
 
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.btn_one:
            setCurrentInflateItem(0);
            break;
        case R.id.btn_two:
            setCurrentInflateItem(1);
            break;
        case R.id.btn_three:
            setCurrentInflateItem(2);
            break;
        case R.id.btn_four:
            setCurrentInflateItem(3);
            break;
        }
    }
     
    private void setCurrentInflateItem(int type){
        if(type==0){
            mPager.setCurrentItem(0);
        }else if(type==1){
            mPager.setCurrentItem(1);
        }else if(type==2){
            mPager.setCurrentItem(2);
        }else{
            mPager.setCurrentItem(3);
        }
    }
     
    private Button btn_one;
    private Button btn_two;
    private Button btn_three;
    private Button btn_four;
    /**
     * Layout
     */
    private void setLayout(){
        btn_one = (Button) findViewById(R.id.btn_one);
        btn_two = (Button) findViewById(R.id.btn_two);
        btn_three = (Button) findViewById(R.id.btn_three);
        btn_four = (Button) findViewById(R.id.btn_four);
        
        btn_one.setOnClickListener(this);
        btn_two.setOnClickListener(this);
        btn_three.setOnClickListener(this);
        btn_four.setOnClickListener(this);
    }
     
     
    /**
     * PagerAdapter 
     */
    private class PagerAdapterClass extends PagerAdapter{
         
        private LayoutInflater mInflater;
 
        public PagerAdapterClass(Context c){
            super();
            mInflater = LayoutInflater.from(c);
        }
         
        @Override
        public int getCount() {
            return 4;
        }
 
        @Override
        public Object instantiateItem(View pager, int position) {
            View v = null;
            if(position==0){
                v = mInflater.inflate(R.layout.sche_one, null);
                v.findViewById(R.id.iv_one);

            }
            else if(position==1){
                v = mInflater.inflate(R.layout.sche_two, null);
                v.findViewById(R.id.iv_two);

            }else if(position==2){
                v = mInflater.inflate(R.layout.sche_three, null);
                v.findViewById(R.id.iv_three); 

            }else{
                v = mInflater.inflate(R.layout.sche_four, null);
                v.findViewById(R.id.iv_four); 

            }
             
            ((ViewPager)pager).addView(v, 0);             
            return v; 
        }
 
        @Override
        public void destroyItem(View pager, int position, Object view) {    
            ((ViewPager)pager).removeView((View)view);
        }
         
        @Override
        public boolean isViewFromObject(View pager, Object obj) {
            return pager == obj; 
        }
 
        @Override public void restoreState(Parcelable arg0, ClassLoader arg1) {}
        @Override public Parcelable saveState() { return null; }
        @Override public void startUpdate(View arg0) {}
        @Override public void finishUpdate(View arg0) {}
    }
     
}
 
