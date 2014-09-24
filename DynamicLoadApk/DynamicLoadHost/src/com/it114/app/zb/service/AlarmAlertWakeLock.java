package com.it114.app.zb.service;

import android.content.Context;
import android.os.PowerManager;

/**
 * Created by andy on 9/24/2014.
 */
class AlarmAlertWakeLock
{
    private static PowerManager.WakeLock sCpuWakeLock;

    static void acquireCpuWakeLock(Context paramContext)
    {
        if (sCpuWakeLock != null)
            return;
        sCpuWakeLock = ((PowerManager)paramContext.getSystemService("power")).newWakeLock(PowerManager.FULL_WAKE_LOCK  , "AlarmClock");
        sCpuWakeLock.acquire();
    }

    static void releaseCpuLock()
    {
        if (sCpuWakeLock != null)
        {
            sCpuWakeLock.release();
            sCpuWakeLock = null;
        }
    }
}