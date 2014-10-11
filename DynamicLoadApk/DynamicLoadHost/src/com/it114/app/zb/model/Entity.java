package com.it114.app.zb.model;

import java.io.Serializable;

/**
 * Created by andy on 10/11/2014.
 */
public abstract class Entity  implements Serializable {

    protected int id;

    public int getId() {
        return id;
    }

    protected String cacheKey;

    public String getCacheKey() {
        return cacheKey;
    }

    public void setCacheKey(String cacheKey) {
        this.cacheKey = cacheKey;
    }
}
