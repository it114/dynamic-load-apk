package com.it114.app.zb.api;

/**
 * Created by andy on 10/11/2014.
 */
public interface RequestCallback {

    public void onSuccess(Object object);
    public void onFail(int code,String msg);
}
