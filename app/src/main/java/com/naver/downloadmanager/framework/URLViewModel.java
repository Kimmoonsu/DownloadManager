package com.naver.downloadmanager.framework;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.naver.downloadmanager.data.datasource.URLData;

import java.util.List;

public class URLViewModel extends ViewModel {
    private final MutableLiveData<List<URLData>> mUrls = new MutableLiveData<>();
    public URLViewModel() {

    }

    public LiveData<List<URLData>> getUrls() {
        return mUrls;
    }
    public void addUrl(List<URLData> urlList) {
        mUrls.setValue(urlList);
    }


}
