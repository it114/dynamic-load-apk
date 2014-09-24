package com.it114.app.zb.plugin;

import android.content.Context;
import android.os.Environment;

import java.util.ArrayList;

/**
 * Created by andy on 9/22/2014.
 */
public class PluginConfig {
    //插件更新查询地址
    public final static String pluginServerUrl = "";

    public static String getSdCardStoragePath(Context context){
        return Environment.getExternalStorageDirectory() + "/"+context.getPackageName()+"/.plugins";
    }

    public static String getInternalStoragePath(Context context) {
        return context.getDir("plugins",Context.MODE_PRIVATE).getAbsolutePath();
    }

    public ArrayList<PluginItem> getInternalPluginList(){
        ArrayList<PluginItem> inernalPlugin = new ArrayList<PluginItem>();
        return inernalPlugin;
    }



}
