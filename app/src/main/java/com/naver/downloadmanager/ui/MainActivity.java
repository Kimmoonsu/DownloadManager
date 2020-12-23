package com.naver.downloadmanager.ui;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.naver.downloadmanager.R;
import com.naver.downloadmanager.common.Constant;
import com.naver.downloadmanager.common.util.DownloadUtils;
import com.naver.downloadmanager.common.util.NetworkConnection;
import com.naver.downloadmanager.common.util.SharedPreferenceUtils;
import com.naver.downloadmanager.common.util.Utils;
import com.naver.downloadmanager.data.datasource.URLData;
import com.naver.downloadmanager.databinding.ActivityMainBinding;
import com.naver.downloadmanager.viewmodel.URLViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private RecyclerView mRecyclerView = null;
    private URLAdapter mUrlAdapter = null;
    private URLViewModel mUrlViewModel = null;
    private NetworkConnection mNetworkConnection = null;
    private DownloadReceiver mDownloadReceiver = null;
    private Context mContext = null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getApplicationContext();

        mUrlViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(URLViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setViewModel(mUrlViewModel);
        binding.setLifecycleOwner(this);

        binding.selectAllBtn.setOnClickListener(v -> {
            List<URLData> urls = ((URLAdapter) binding.recyclerView.getAdapter()).selectALL(binding.selectAllBtn.isChecked());
            SharedPreferenceUtils.setData(mContext, Constant.URL_KEY, urls);
        });

        setupNetworkConnection();
        setupRecyclerView();
        setupObserves();

        mDownloadReceiver = new DownloadReceiver();

    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        intentFilter.addAction(DownloadManager.ACTION_NOTIFICATION_CLICKED);
        registerReceiver(mDownloadReceiver, intentFilter);
    }

    private void setupNetworkConnection() {
        mNetworkConnection = new NetworkConnection(mContext);
        mNetworkConnection.register();
    }

    private void setupRecyclerView() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        URLAdapter urlAdapter = new URLAdapter(this, mUrlViewModel);
        urlAdapter.setHasStableIds(true);
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.setAdapter(urlAdapter);

        // check the shared preference value
        List<URLData> urls = SharedPreferenceUtils.getData(mContext, Constant.URL_KEY);
        mUrlViewModel.addUrl(urls);
    }

    private void setupObserves() {
        mUrlViewModel.getUrls().observe(this, list -> {
            ((URLAdapter) binding.recyclerView.getAdapter()).setUrls(list);
            SharedPreferenceUtils.setData(mContext, Constant.URL_KEY, list);
        });

        mUrlViewModel.selectedAllFlag.observe(this, isChecked -> {
            List<URLData> urls = ((URLAdapter) binding.recyclerView.getAdapter()).getUrls();
            SharedPreferenceUtils.setData(mContext, Constant.URL_KEY, urls);
        });

        mUrlViewModel.checkNetworkConnection().observe(this, __ -> {
            startDownload();
        });
    }

    private void startDownload() {
        if (mNetworkConnection.isNetworkConnected() && Utils.isNetworkConnected(mContext)) {
            List<URLData> urlDataList = mUrlViewModel.getUrls().getValue();
            if (urlDataList.size() == 0) {
                Toast.makeText(mContext, getResources().getString(R.string.message_click_geturl_button), Toast.LENGTH_SHORT).show();
            }
            for (URLData urlData : urlDataList) {
                if (urlData.isChecked()) {
                    long downloadId = DownloadUtils.downloadFile(mContext, urlData.getUrl());
                    urlData.setDownloadId(downloadId);
                }
            }
            mUrlViewModel.addUrl(urlDataList);
            return ;
        }
        Toast.makeText(mContext, getResources().getString(R.string.message_network_off), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mNetworkConnection.unregister();
        unregisterReceiver(mDownloadReceiver);
    }

    private class DownloadReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            long id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
            String actionId = intent.getAction();
            List<URLData> urlDataList = mUrlViewModel.getUrls().getValue();
            URLData target = findUrlData(urlDataList, id);
            if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(actionId)) {
                boolean isSuccessDownload = DownloadUtils.isSuccessDownload(mContext, target.getDownloadId());
                target.setState(isSuccessDownload ? URLData.STATE.SUCCESS : URLData.STATE.FAIL);
                mUrlViewModel.addUrl(urlDataList);
            } else if (DownloadManager.ACTION_NOTIFICATION_CLICKED.equals(actionId)) {
            }
        }
    }

    private URLData findUrlData(List<URLData> urlDataList, long id) {
        for (URLData urlData : urlDataList) {
            if (urlData.compareDownloadId(id)) {
                return urlData;
            }
        }
        return null;
    }
}
