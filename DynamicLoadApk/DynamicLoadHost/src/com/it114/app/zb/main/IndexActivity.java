package com.it114.app.zb.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
}
