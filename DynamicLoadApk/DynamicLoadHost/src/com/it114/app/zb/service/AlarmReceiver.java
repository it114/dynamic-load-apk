package com.it114.app.zb.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.it114.app.zb.main.PhoneIncomingCallsActivity;
import com.it114.app.zb.main.PhoneLightScreen;
import com.it114.app.zb.utils.PerferenceUtil;

/**
 * Created by andy on 9/24/2014.
 */
public class AlarmReceiver extends BroadcastReceiver
{
    public void onReceive(Context paramContext, Intent paramIntent)
    {
        if (("android.alarm.demo.action".equals(paramIntent.getAction())) && (PerferenceUtil.getBoolean(paramContext, null, "_PHONE_ENABLE_CALL", false)))
        {
            AlarmAlertWakeLock.acquireCpuWakeLock(paramContext);
            Intent localIntent = new Intent(paramContext, PhoneIncomingCallsActivity.class);
            localIntent.addFlags(268435456);
            paramContext.startActivity(localIntent);
            PerferenceUtil.setLong(paramContext, null, "_PHONE_CALL_TIME", -1L);
        }
    }
}