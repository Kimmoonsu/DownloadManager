package com.naver.downloadmanager.data.datasource;

public class URLData {
    int mId;
    String mUrl;

    public URLData(int id, String url) {
        this.mId = id;
        this.mUrl = url;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        this.mId = id;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        this.mUrl = url;
    }
}
