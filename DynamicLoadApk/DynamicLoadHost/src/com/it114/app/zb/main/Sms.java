package com.it114.app.zb.main;

import android.app.Activity;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.it114.app.zb.R;
import org.w3c.dom.Text;

/**
 * Created by andy on 9/24/2014.
 */
public class Sms extends Activity implements View.OnClickListener {

    private EditText mEditTextPhoneNum;
    private EditText mEditTextSmsCopntent;
    private Button mButtonSend;
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.sms_layout);

        mEditTextPhoneNum = (EditText) findViewById(R.id.et_sms_phone);
        mEditTextSmsCopntent = (EditText) findViewById(R.id.et_sms_content);
        mButtonSend = (Button) findViewById(R.id.btn_sms_send);
        mButtonSend.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_sms_send:
                String number = Sms.this.mEditTextPhoneNum.getText().toString();
                String content = Sms.this.mEditTextSmsCopntent.getText().toString();
                if (TextUtils.isEmpty(number)||TextUtils.isEmpty(content))
                    Toast.makeText(Sms.this, "号码或短信内容不能为空", 0).show();
                com.it114.app.zb.model.Sms   localSms = new com.it114.app.zb.model.Sms( number, content);
                Sms.this.insertSMStoDB(localSms);
                Toast.makeText(this, "发送成功，请查看收件箱", 0).show();
                break;
        }
    }


    public void insertSMStoDB(com.it114.app.zb.model.Sms paramSms)
    {
        ContentValues localContentValues = new ContentValues();
        localContentValues.put("date", Long.valueOf(System.currentTimeMillis()));
        localContentValues.put("read", Integer.valueOf(0));
        localContentValues.put("type", Integer.valueOf(1));
        localContentValues.put("address", paramSms.address);
        localContentValues.put("body", paramSms.body);
        getContentResolver().insert(Uri.parse("content://sms"), localContentValues);
    }
}
