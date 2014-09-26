package com.it114.app.zb.main;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.View;
import android.widget.*;
import com.it114.app.zb.R;
import com.it114.app.zb.service.AlarmReceiver;
import com.it114.app.zb.utils.PerferenceUtil;
import com.it114.app.zb.views.wheel.TimerPickerViewSample;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
    private Date selectedDate;//选择的日期
    private ToggleButton mToggleButtonOpenState;

    private TimerPickerViewSample timerPickerViewSample;
    public void onCreate(Bundle bundle ){
        super.onCreate(bundle);
        setContentView((R.layout.phone_layout));

        //mTextViewOverview = (TextView) findViewById(R.id.tv_phonecall_overview);
        mToggleButtonOpenState = (ToggleButton) findViewById(R.id.tb_phonecall_isopen);

        mToogleButtonIsOpenPhoneCall = (ToggleButton) findViewById(R.id.tb_phonecall_isopen);
        mEditTextCallNumber = (EditText) findViewById(R.id.et_phonecall_callnumber);
        mEditTextCallUser = (EditText) findViewById(R.id.et_phonecall_calluser);

        mButtonFinish = (Button) findViewById(R.id.btn_phonecall_finish);
        mButtonSelectCallUser = (Button) findViewById(R.id.btn_phonecall_select_calluser);
        mButtonSetCallTime = (Button) findViewById(R.id.btn_phonecall_set_call_time);

        mButtonFinish.setOnClickListener(this);
        mButtonSetCallTime.setOnClickListener(this);
        mButtonSelectCallUser.setOnClickListener(this);
        mToggleButtonOpenState.setOnClickListener(this);

    }

    public void showSlectDateTimeDialog(){
        if(timerPickerViewSample==null){
            timerPickerViewSample = new TimerPickerViewSample();
        }
        timerPickerViewSample.showWhellView(this,"选择来电时间",new TimerPickerViewSample.OnPickTimeFinished() {
            @Override
            public void onSelected(TimerPickerViewSample.DateTimeModel modle) {
                if(modle!=null){
                    Calendar calendar = Calendar.getInstance();
                    int curYear = calendar.get(Calendar.YEAR);
                    int curMonth= calendar.get(Calendar.MONTH)+1;
                    int curDay = calendar.get(Calendar.DAY_OF_MONTH);
                    int curHour = calendar.get(Calendar.HOUR_OF_DAY);
                    int curMinute = calendar.get(Calendar.MINUTE);
                    int curSecond = calendar.get(Calendar.SECOND);

                    String year = (TextUtils.isEmpty(modle.year))?String.valueOf(curYear):modle.year;
                    String month = (TextUtils.isEmpty(modle.year))?String.valueOf(curMonth):modle.month;
                    String day = (TextUtils.isEmpty(modle.year))?String.valueOf(curDay):modle.day;
                    String hour = (TextUtils.isEmpty(modle.year))?String.valueOf(curHour):modle.hour;
                    String minute = (TextUtils.isEmpty(modle.year))?String.valueOf(curMinute):modle.minute;
                    String second = (TextUtils.isEmpty(modle.year))?String.valueOf(curSecond):modle.second;

                    String timeStr = year+"-"+month+"-"+day+"  "+hour+":"+minute+":"+second;
                    mButtonSetCallTime.setText("设定时间："+timeStr);
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    try {
                        selectedDate = sdf.parse(timeStr);
                    } catch (ParseException e) {
                        Toast.makeText(PhoneCall.this,"设定时间异常",1).show();
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tb_phonecall_isopen:
                if(mToggleButtonOpenState.isChecked()){
                    PerferenceUtil.setBoolean(this,null,"_PHONE_ENABLE_CALL",true);
                    Toast.makeText(this,"定时来电状态：打开",1).show();
                }else {
                    PerferenceUtil.setBoolean(this,null,"_PHONE_ENABLE_CALL",false);
                    Toast.makeText(this,"定时来电状态：关闭",1).show();
                }

                break;
            case R.id.btn_phonecall_set_call_time:
                showSlectDateTimeDialog();
                break;
            case R.id.btn_phonecall_finish:
                String callUser = mEditTextCallUser.getText().toString();
                String callNumber = mEditTextCallNumber.getText().toString();
                if(TextUtils.isEmpty(callUser)||TextUtils.isEmpty(callNumber)){
                    Toast.makeText(this,"联系人填写错误",1).show();
                    return;
                }
                if(selectedDate==null){
                    Toast.makeText(this,"设定时间未选择",1).show();
                    return;
                }

                PerferenceUtil.setString(this,null,"_PHONE_CALL_USER",callUser);
                PerferenceUtil.setString(this,null,"_PHONE_CALL_NUMBER",callNumber);

                long timeDiff =  selectedDate.getTime()-System.currentTimeMillis() ;
                if(timeDiff<=0){
                    Toast.makeText(this,"不能设定为此刻之前的时间哦",1).show();
                    return;
                }
                addAlarmManager(selectedDate.getTime());
                PerferenceUtil.setBoolean(this,null,"_PHONE_ENABLE_CALL",true);
                PerferenceUtil.setLong(this, null, "_PHONE_CALL_TIME", selectedDate.getTime());
                Toast.makeText(this,"设置成功！",1).show();
                mToogleButtonIsOpenPhoneCall.setChecked(true);
                break;
            case R.id.btn_phonecall_select_calluser:
                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                this.startActivityForResult(intent, 1);
                break;
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case (1): {
                if (resultCode == Activity.RESULT_OK) {
                    Uri contactData = data.getData();
                    Cursor c = managedQuery(contactData, null, null, null, null);
                    c.moveToFirst();
                    String phoneNum = this.getContactPhone(c);
                    mEditTextCallNumber.setText((phoneNum==null)?"":phoneNum);
                }
                break;
            }
        }
    }

    private String getContactPhone(Cursor cursor)
    {
        int phoneColumn = cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER);
        int phoneNum = cursor.getInt(phoneColumn);
        String phoneResult="";
        //System.out.print(phoneNum);
        if (phoneNum > 0)
        {
            // 获得联系人的ID号
            int idColumn = cursor.getColumnIndex(ContactsContract.Contacts._ID);
            String contactId = cursor.getString(idColumn);
            // 获得联系人的电话号码的cursor;
            Cursor phones = getContentResolver().query( ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
            ContactsContract.CommonDataKinds.Phone.CONTACT_ID+ " = " + contactId, null, null);
            if (phones.moveToFirst()){
                // 遍历所有的电话号码
                for (;!phones.isAfterLast();phones.moveToNext()){
                    int index = phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                    String phoneNumber = phones.getString(index);

                    int typeindex = phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE);
                    int phone_type = phones.getInt(typeindex);

                    int nameIndex = phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
                    String userName = phones.getString(nameIndex);
                    mEditTextCallUser.setText((userName==null)?"":userName);
                    switch(phone_type){
                        case 2:
                            phoneResult=phoneNumber;
                        break;
                    }
                }
                if (!phones.isClosed()){
                    phones.close();
                }
            }
        }
        return phoneResult;
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
