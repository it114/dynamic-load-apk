package com.it114.app.zb.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by andy on 2014/9/24.
 */
public class PerferenceUtil {
    private static final String _defaultSaveFileName = "_zb_data_file_";
    private static String getSaveFileName(String fileName){
        return (fileName==null)?_defaultSaveFileName:fileName;
    }
    public static boolean getBoolean(Context paramContext,String fileName, String key, boolean defvalue)
    {
        return paramContext.getSharedPreferences(getSaveFileName(fileName), 0).getBoolean(key, defvalue);
    }

    public static int getInt(Context paramContext, String fileName,String key, int defvalue)
    {
        return paramContext.getSharedPreferences(getSaveFileName(fileName), 0).getInt(key, defvalue);
    }

    public static long getLong(Context paramContext, String fileName,String key, long defvalue)
    {
        return paramContext.getSharedPreferences(getSaveFileName(fileName), 0).getLong(key, defvalue);
    }

    public static String getString(Context paramContext,String fileName, String key, String defvalue)
    {
        return paramContext.getSharedPreferences(getSaveFileName(fileName), 0).getString(key, defvalue);
    }

    public static void setBoolean(Context paramContext, String fileName,String key, boolean value)
    {
        SharedPreferences.Editor localEditor = paramContext.getSharedPreferences(getSaveFileName(fileName), 0).edit();
        localEditor.putBoolean(key, value);
        localEditor.commit();
    }

    public static void setInt(Context paramContext, String fileName,String key, int value)
    {
        SharedPreferences.Editor localEditor = paramContext.getSharedPreferences(getSaveFileName(fileName), 0).edit();
        localEditor.putInt(key, value);
        localEditor.commit();
    }

    public static void setLong(Context paramContext, String fileName,String key, long value)
    {
        SharedPreferences.Editor localEditor = paramContext.getSharedPreferences(getSaveFileName(fileName), 0).edit();
        localEditor.putLong(key, value);
        localEditor.commit();
    }

    public static void setString(Context paramContext,String fileName, String key, String value)
    {
        SharedPreferences.Editor localEditor = paramContext.getSharedPreferences(getSaveFileName(fileName), 0).edit();
        localEditor.putString(key, value);
        localEditor.commit();
    }
}
