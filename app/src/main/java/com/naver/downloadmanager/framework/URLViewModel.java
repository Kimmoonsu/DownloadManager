package com.naver.downloadmanager.framework;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.naver.downloadmanager.common.util.SingleLiveEvent;
import com.naver.downloadmanager.data.DummyData;
import com.naver.downloadmanager.data.datasource.URLData;

import java.util.List;

public class URLViewModel extends ViewModel {
    private final MutableLiveData<List<URLData>> mUrls = new MutableLiveData<>();
    private final SingleLiveEvent<Object> checkNetworkConnection = new SingleLiveEvent<>();
    public MutableLiveData<Boolean> selectedAllFlag = new MutableLiveData<>();

    public URLViewModel() {

    }

    public void onClickedDownload() {
        checkNetworkConnection.call();
    }

    public void onClickedUrlButton() {
        mUrls.setValue(DummyData.getDummyData());
    }

    public LiveData<List<URLData>> getUrls() {
        return mUrls;
    }

    public void addUrl(List<URLData> urlList) {
        mUrls.setValue(urlList);
    }

//    public static void setSelectedAllFlag(boolean flag) {
//        selectedAllFlag.setValue(flag);
//    }

    public LiveData<Object> checkNetworkConnection() {
        return checkNetworkConnection;
    }


}
