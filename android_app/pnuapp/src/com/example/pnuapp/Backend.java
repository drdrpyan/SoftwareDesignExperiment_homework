package com.example.pnuapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

//  �ð��� �����ϴ� ����(t) = 0;
//    �ڷΰ��� ��ư (ó��)Ŭ���� �ð��� �����ϴ� ����(t) + 2000(2��)�� ���� �ð����� �۴�.
// �˸�â�� ����.('�ڷ�'��ư�� �ѹ� �� �����ø� ����˴ϴ�.)
// �ð��� �����ϴ� ����(t)�� ���� �ð��� �����Ѵ�.
//    �ڷΰ��� ��ư�� �ѹ��� Ŭ��
// ���� �ð��� ����t + 2000���� ������ �� ����
// ���� �ð��� ����t + 2000���� ũ�� t�� ����ð��� �����ϰ� �˸�â�� ����.

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
                "\'�ڷ�\'��ư�� �ѹ� �� �����ø� ����˴ϴ�.", Toast.LENGTH_SHORT);
        toast.show();
    }
}
