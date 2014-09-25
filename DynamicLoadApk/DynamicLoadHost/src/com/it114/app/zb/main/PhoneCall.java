package com.it114.app.zb.main;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.*;
import com.it114.app.zb.R;
import com.it114.app.zb.service.AlarmReceiver;
import com.it114.app.zb.utils.PerferenceUtil;

import java.util.Calendar;

/**
 * Created by andy on 2014/9/23.
 */
public class PhoneCall extends Activity implements View.OnClickListener {
    private ToggleButton mToogleButtonIsOpenPhoneCall;
    private EditText mEditTextCallUser;//
    private EditText mEditTextCallNumber;//
    private Button mButtonSelectCallUser;//
    private Button mButtonSetCallTime;//
    private TextView mTextViewOverview;//
    private Button mButtonFinish;//
    private TextView mTextVierwPhoneCallSelectedTime;

    public void onCreate(Bundle bundle ){
        super.onCreate(bundle);
        setContentView((R.layout.phone_layout));

        mTextViewOverview = (TextView) findViewById(R.id.tv_phonecall_overview);
        mTextVierwPhoneCallSelectedTime = (TextView) findViewById(R.id.tv_phonecall_time);

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

    public void showSlectDateTimeDialog(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = View.inflate(this, R.layout.select_data_time_layout, null);
        final DatePicker datePicker = (DatePicker) view.findViewById(R.id.date_picker);
        final TimePicker timePicker = (android.widget.TimePicker) view.findViewById(R.id.time_picker);
        builder.setView(view);

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis());
        datePicker.init(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), null);

        timePicker.setIs24HourView(true);
        timePicker.setCurrentHour(cal.get(Calendar.HOUR_OF_DAY));
        timePicker.setCurrentMinute(Calendar.MINUTE);

        builder.setTitle("选取时间");
        builder.setPositiveButton("确  定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    StringBuffer sb = new StringBuffer();
                    sb.append(String.format("%d-%02d-%02d",
                            datePicker.getYear(),
                            datePicker.getMonth() + 1,
                            datePicker.getDayOfMonth()));
                    sb.append("  ");
                    sb.append(timePicker.getCurrentHour())
                            .append(":").append(timePicker.getCurrentMinute());
                    mTextVierwPhoneCallSelectedTime.setText(sb.toString());
                    dialog.cancel();
                }
        });
        builder.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_phonecall_set_call_time:
                showSlectDateTimeDialog();
                break;
            case R.id.btn_phonecall_finish:
                //TODO
                addAlarmManager(10000L + Calendar.getInstance().getTimeInMillis());
                Toast.makeText(this,"到时会给你来电的",1).show();
                break;
            case R.id.btn_phonecall_select_calluser:
                String callUser = mEditTextCallUser.getText().toString();
                String callNumber = mEditTextCallNumber.getText().toString();
                if(TextUtils.isEmpty(callUser)||TextUtils.isEmpty(callNumber)){
                    Toast.makeText(this,"参数错误",1).show();
                    return;
                }
                PerferenceUtil.setString(this,null,"_PHONE_CALL_USER",callUser);
                PerferenceUtil.setString(this,null,"_PHONE_CALL_NUMBER",callNumber);
                Toast.makeText(this,"来电联系人保存成功",1).show();
                break;
        }
    }

    private void removeAlarmManager()
    {
        PerferenceUtil.setLong(this, null, "_PHONE_CALL_TIME", -1L);
        PerferenceUtil.setBoolean(this,null,"_PHONE_ENABLE_CALL",false);
        ((AlarmManager)getSystemService("alarm")).cancel(PendingIntent.getBroadcast(this, 800, new Intent(this, AlarmReceiver.class), 0));
    }

    private void addAlarmManager(long paramLong)
    {
        removeAlarmManager();
        PerferenceUtil.setBoolean(this, null, "_PHONE_ENABLE_CALL", true);
        ((AlarmManager)getSystemService("alarm")).set(0, paramLong, PendingIntent.getBroadcast(this, 800, new Intent("android.alarm.demo.action"), PendingIntent.FLAG_CANCEL_CURRENT));
        Calendar localCalendar = Calendar.getInstance();
        localCalendar.setTimeInMillis(paramLong);
        //TODO
    }


}
