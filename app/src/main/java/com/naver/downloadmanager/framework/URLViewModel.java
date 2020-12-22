package com.naver.downloadmanager.framework;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.naver.downloadmanager.data.datasource.URLData;

import java.util.List;

public class URLViewModel extends ViewModel {
    public static MutableLiveData<List<URLData>> urls = new MutableLiveData<>();

    public URLViewModel() {

    }

    public static void addUrl(List<URLData> urlList) {
        urls.setValue(urlList);
    }

}
