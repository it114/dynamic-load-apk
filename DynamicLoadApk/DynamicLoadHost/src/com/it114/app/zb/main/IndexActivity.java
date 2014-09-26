package com.it114.app.zb.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import com.it114.app.zb.R;

/**
 * Created by andy on 2014/9/23.
 */
public class IndexActivity extends Activity implements View.OnClickListener {

    private Button mBtnPhone;
    private Button mBtnSms;
    private Button mBtnTeach;
    private Button mBtnBbs;
    private Button mBtnPlugin;
    private Button mBtnXuQiu;

    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.index_layout);

        mBtnPhone  = (Button) findViewById(R.id.btn_index_phone);
        mBtnSms  = (Button) findViewById(R.id.btn_index_sms);
        mBtnBbs  = (Button) findViewById(R.id.btn_index_bbs);
        mBtnPlugin  = (Button) findViewById(R.id.btn_index_plugin);
        mBtnXuQiu  = (Button) findViewById(R.id.btn_index_xuqiu);
        mBtnTeach = (Button) findViewById(R.id.btn_index_teach);

        mBtnPhone.setOnClickListener(this);
        mBtnSms.setOnClickListener(this);
        mBtnBbs.setOnClickListener(this);
        mBtnPlugin.setOnClickListener(this);
        mBtnXuQiu.setOnClickListener(this);
        mBtnTeach.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_index_bbs:
                break;//bbs

            case R.id.btn_index_phone:
                startActivity(PhoneCall.class);
                break;//phone

            case R.id.btn_index_plugin:

                break;

            case R.id.btn_index_sms:
                startActivity(Sms.class);
                break;

            case R.id.btn_index_xuqiu:

                break ;
            case R.id.btn_index_teach:

                break;
        }
    }

    private void startActivity(Class cls){
        Intent intent  = new Intent(this,cls);
        startActivity(intent);

    }





    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 如果是返回键,直接返回到桌面
        // 经过测试,如果是乐Phone返回桌面会报错
        if(keyCode == KeyEvent.KEYCODE_BACK){
            if(notSupportKeyCodeBack()){
                return super.onKeyDown(keyCode, event);
            } else {
                Intent i= new Intent(Intent.ACTION_MAIN);
                i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                i.addCategory(Intent.CATEGORY_HOME);
                startActivity(i);
                return false;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
    // 经过测试,如果是乐Phone返回桌面会报错
    private boolean notSupportKeyCodeBack(){
        if("3GW100".equals(Build.MODEL)|| "3GW101".equals(Build.MODEL) || "3GC101".equals (Build.MODEL)) {
            return true;
        }
        return false;
    }
}
