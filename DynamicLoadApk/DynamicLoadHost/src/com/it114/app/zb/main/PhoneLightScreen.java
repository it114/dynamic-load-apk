package com.it114.app.zb.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

/**
 * Created by andy on 9/24/2014.
 */
public class PhoneLightScreen extends Activity
{
    protected static final String SCREEN_OFF = "screen_off";

    protected void onCreate(Bundle paramBundle)
    {
        super.onCreate(paramBundle);
        Window localWindow = getWindow();
        localWindow.addFlags(4718592);
        if (!getIntent().getBooleanExtra("screen_off", false))
        {
            localWindow.addFlags(2097281);
            new Handler().postDelayed(new Runnable()
            {
                public void run()
                {
                    PhoneLightScreen.this.finish();
                    Intent localIntent = new Intent(PhoneLightScreen.this, PhoneIncomingCallsActivity.class);
                    localIntent.addFlags(268435456);
                    PhoneLightScreen.this.startActivity(localIntent);
                    PhoneLightScreen.this.overridePendingTransition(2130968578, 2130968576);
                }
            }
            ,100L);
            return;
        }
        finish();
        Intent localIntent = new Intent(this, PhoneIncomingCallsActivity.class);
        localIntent.addFlags(268435456);
        startActivity(localIntent);
        //overridePendingTransition(2130968578, 2130968576);
    }
}