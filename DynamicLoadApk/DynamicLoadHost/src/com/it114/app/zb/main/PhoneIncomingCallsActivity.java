package com.it114.app.zb.main;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.it114.app.zb.R;

/**
 * Created by andy on 9/24/2014.
 */
public class PhoneIncomingCallsActivity extends Activity implements View.OnClickListener {

    private Button mButtonAction;
    private TextView mTextViewCallUser;
    private Handler mHandler = new Handler();
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.phone_call_incoming_layout);
        //TODO 音乐 & 界面
        mButtonAction = (Button) findViewById(R.id.btn_phone_comming_action);
        mTextViewCallUser = (TextView) findViewById(R.id.tv_phone_incomming_calluser);

        mButtonAction.setOnClickListener(this);

        mButtonAction.setText("接听");

        //Toast.makeText(this, "可以截图发给朋友哦", 1).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_phone_comming_action:
                mButtonAction.setText("通话结束");
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        PhoneIncomingCallsActivity.this.finish();
                    }
                },2000);
                break;
        }
    }
}
