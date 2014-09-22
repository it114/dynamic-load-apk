package com.ryg.plugin;

import android.content.pm.PackageInfo;

/**
 * Created by andy on 9/22/2014.
 */
public class PluginItem {

    public PackageInfo packageInfo;
    public String pluginPath;
    public String pluginUpdateUrl;//升级文件
    public String versionCode;
    public String versionName;
    public String installPosition;//内存中还是SD卡中
    public int order;// 插件排序
    public PluginItem() {
    }
}
