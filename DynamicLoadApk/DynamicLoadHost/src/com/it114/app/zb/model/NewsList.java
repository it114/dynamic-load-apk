package com.it114.app.zb.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by andy on 10/11/2014.
 */
public class NewsList extends Entity{

    public final static int CATALOG_ALL = 1;
    public final static int CATALOG_INTEGRATION = 2;
    public final static int CATALOG_SOFTWARE = 3;

    private int catalog;
    private int pageSize;
    private int newsCount;
    private List<News> newslist = new ArrayList<News>();

    public int getCatalog() {
        return catalog;
    }
    public int getPageSize() {
        return pageSize;
    }
    public int getNewsCount() {
        return newsCount;
    }
    public List<News> getNewslist() {
        return newslist;
    }

    public static NewsList parse(String str) throws IOException  {
        NewsList newslist = new NewsList();
        News news = null;
        return null;
    }

}
