package com.naver.downloadmanager.data.datasource;


public class URLData {
    public enum STATE {
        NONE, FAIL, SUCCESS
    }

    int mId;
    long mDownloadId;
    String mUrl;
    STATE mState;
    boolean mIsChecked;

    public URLData(int id, String url) {
        this.mId = id;
        mDownloadId = -1;
        this.mUrl = url;
        mState = STATE.NONE;
        mIsChecked = false;
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

    public long getDownloadId() {
        return mDownloadId;
    }

    public void setDownloadId(long downloadId) {
        this.mDownloadId = downloadId;
    }

    public STATE getState() {
        return mState;
    }

    public void setState(STATE state) {
        this.mState = state;
    }

    public boolean compareDownloadId(long downloadId) {
        return downloadId == mDownloadId;
    }

    public void setChecked(boolean isChecked) {
        this.mIsChecked = isChecked;
    }

    public boolean isChecked() {
        return mIsChecked;
    }
}
