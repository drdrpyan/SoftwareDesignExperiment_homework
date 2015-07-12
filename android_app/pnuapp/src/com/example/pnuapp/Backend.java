package com.example.pnuapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

//  시간을 저장하는 변수(t) = 0;
//    뒤로가기 버튼 (처음)클릭시 시간을 저장하는 변수(t) + 2000(2초)가 현재 시간보다 작다.
// 알림창을 띄운다.('뒤로'버튼을 한번 더 누르시면 종료됩니다.)
// 시간을 저장하는 변수(t)에 현재 시간을 저장한다.
//    뒤로가기 버튼을 한번더 클릭
// 현재 시간이 변수t + 2000보다 작으면 앱 종료
// 현재 시간이 변수t + 2000보다 크면 t에 현재시간을 저장하고 알림창을 띄운다.

public class Backend extends Activity {
	 
    private long backKeyPressedTime = 0;
    private Toast toast;
 
    private Activity activity;
 
    public Backend(Activity context) {
        this.activity = context;
    }
 
    public void onBackPressed() {
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            showGuide();
            return;
        }
        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            activity.finish();
            toast.cancel();
        }
    }
 
    public void showGuide() {
        toast = Toast.makeText(activity,
                "\'뒤로\'버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT);
        toast.show();
    }
}
