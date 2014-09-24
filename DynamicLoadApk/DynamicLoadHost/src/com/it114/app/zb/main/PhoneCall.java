package com.it114.app.zb.main;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;
import com.it114.app.zb.R;

/**
 * Created by andy on 2014/9/23.
 */
public class PhoneCall extends Activity implements View.OnClickListener {

    public static final String PHONE_DATA_SAVE_FILE = "_phone_module_data_file_";//电话模块，保存数据文件名

    private ToggleButton mToogleButtonIsOpenPhoneCall;
    private EditText mEditTextCallUser;//
    private EditText mEditTextCallNumber;//
    private Button mButtonSelectCallUser;//
    private Button mButtonSetCallTime;//
    private TextView mTextViewOverview;//
    private Button mButtonFinish;//


    public void onCreate(Bundle bundle ){
        super.onCreate(bundle);
        setContentView((R.layout.phone_layout));

        mTextViewOverview = (TextView) findViewById(R.id.tv_phonecall_overview);

        mToogleButtonIsOpenPhoneCall = (ToggleButton) findViewById(R.id.tb_phonecall_isopen);
        mEditTextCallNumber = (EditText) findViewById(R.id.et_phonecall_callnumber);
        mEditTextCallUser = (EditText) findViewById(R.id.et_phonecall_calluser);

        mButtonFinish = (Button) findViewById(R.id.btn_phonecall_finish);
        mButtonSelectCallUser = (Button) findViewById(R.id.btn_phonecall_select_calluser);
        mButtonSetCallTime = (Button) findViewById(R.id.btn_phonecall_set_call_time);

        mButtonFinish.setOnClickListener(this);
        mButtonSetCallTime.setOnClickListener(this);
        mButtonSelectCallUser.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_phonecall_set_call_time:

                break;
            case R.id.btn_phonecall_finish:

                break;
            case R.id.btn_phonecall_select_calluser:

                break;
        }
    }
}
