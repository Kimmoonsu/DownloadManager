package com.naver.downloadmanager.framework;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.naver.downloadmanager.data.datasource.URLData;

import java.util.List;

public class URLViewModel extends ViewModel {
    private final MutableLiveData<List<URLData>> mUrls = new MutableLiveData<>();
    private static final MutableLiveData<Boolean> selectedAllFlag = new MutableLiveData<>();
    public URLViewModel() {

    }

    public LiveData<List<URLData>> getUrls() {
        return mUrls;
    }
    public void addUrl(List<URLData> urlList) {
        mUrls.setValue(urlList);
    }

    public LiveData<Boolean> isSelectedALL() {
        return selectedAllFlag;
    }

    public static void setSelectedAllFlag(boolean flag) {
        selectedAllFlag.setValue(flag);
    }


}
