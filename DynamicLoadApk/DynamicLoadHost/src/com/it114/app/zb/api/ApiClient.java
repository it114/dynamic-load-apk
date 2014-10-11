package com.it114.app.zb.api;

import android.content.Context;
import com.it114.app.zb.AppContext;
import com.it114.app.zb.model.NewsList;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import org.apache.http.Header;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Handler;

/**
 * Created by andy on 10/11/2014.
 */
public class ApiClient {
    private static final String BASE_URL = "http://api.twitter.com/1/";
    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }



    public static void  getNewsList(final RequestCallback callback, final int catalog, final int pageIndex, final int pageSize)   {
        RequestParams params = new RequestParams();
        params.put("catalog","");
        params.put("pageIndex",1);
        params.put("pageSize",10);
        get(URLs.NEWS_LIST, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                if(bytes!=null){
                    String content = new String(bytes);
                    try {
                        NewsList newsList = NewsList.parse(content);
                        callback.onSuccess(newsList);
                    } catch (IOException e) {
                        e.printStackTrace();
                        callback.onFail(i,"解析数据异常");
                    }
                }
            }
            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                callback.onFail(i,"请求数据失败");
            }
        });
    }


}
