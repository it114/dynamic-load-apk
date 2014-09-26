package com.it114.app.zb.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.*;
import com.it114.app.zb.R;
import com.it114.app.zb.utils.PerferenceUtil;
import com.it114.app.zb.views.PhoneLockView;

/**
 * Created by andy on 9/24/2014.
 */
public class PhoneIncomingCallsActivity extends Activity implements View.OnClickListener {

    private TextView mButtonAction;
    private TextView mTextViewCallUser;
    private TextView mTextViewCallNum;
    private ImageView mImageViewUserHeader;
    private TextView mTextViewIncomingTime;
    public static PhoneLockView mPhoneLockView;
    public static LinearLayout mLinearLayoutCalledContainer;//数字键盘等的容器
    public static final int PHONE_INCOMING_OPENED = 101;//接听消息
    private boolean jieDianHua = false;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch(msg.what){
                case PHONE_INCOMING_OPENED:
                    jieDianhua();
                    break;
            }
        }
    };
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.phone_incoming_layout);
        //TODO 音乐 & 界面
        this.mTextViewCallUser = (TextView)this.findViewById(R.id.jname);
        this.mTextViewCallNum = (TextView)this.findViewById(R.id.jnumber);
        this.mImageViewUserHeader = (ImageView)this.findViewById(R.id.imageView_touxiang);
        this.mTextViewIncomingTime = (TextView)this.findViewById(R.id.jtime);
        (PhoneIncomingCallsActivity.mPhoneLockView = (PhoneLockView)this.findViewById(R.id.lockview)).setMainHandler(this.mHandler);
        (PhoneIncomingCallsActivity.mLinearLayoutCalledContainer = (LinearLayout)this.findViewById(R.id.lin_g)).setVisibility(8);
        (this.mButtonAction = (TextView)PhoneIncomingCallsActivity.mLinearLayoutCalledContainer.findViewById(R.id.tv_phonecall_guaduan)).setOnClickListener(this);

        mButtonAction.setOnClickListener(this);

        //mButtonAction.setText("接听");

        //TODO,开启音乐播放、震动效果等
        //Toast.makeText(this, "可以截图发给朋友哦", 1).show();

        mTextViewCallUser.setText(PerferenceUtil.getString(this,null,"_PHONE_CALL_USER",""));
        mTextViewCallNum.setText(PerferenceUtil.getString(this,null,"_PHONE_CALL_NUMBER",""));

    }

    private void jieDianhua(){
        jieDianHua = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                int passedSecond = 0;
                int passedMinute = 0;
                String passedSecondStr = "0";
                String passedMinuteStr = "0";
                while(jieDianHua) {
                    try {
                        Thread.sleep(1000);
                        passedSecond++;
                        if (passedSecond >= 60) {
                            passedSecond = 0;
                            passedMinute++;
                        }
                        passedSecondStr = String.valueOf(passedSecond);
                        passedMinuteStr = String.valueOf(passedMinute);

                        passedSecondStr = (passedSecondStr.length() == 1) ? "0" + passedSecondStr : passedSecondStr;
                        passedMinuteStr = (passedMinuteStr.length() == 1) ? "0" + passedMinuteStr : passedMinuteStr;
                        final String finalPassedMinuteStr = passedMinuteStr;
                        final String finalPassedSecondStr = passedSecondStr;
                        PhoneIncomingCallsActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mTextViewIncomingTime.setText(finalPassedMinuteStr + ":" + finalPassedSecondStr);
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_phonecall_guaduan:
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        jieDianHua = false;
                        PhoneIncomingCallsActivity.this.finish();
                    }
                },500);
                break;
        }
    }



}
