package com.naver.downloadmanager.ui;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.naver.downloadmanager.DownloadController;
import com.naver.downloadmanager.R;
import com.naver.downloadmanager.common.Constant;
import com.naver.downloadmanager.common.util.DownloadUtils;
import com.naver.downloadmanager.common.util.NetworkConnection;
import com.naver.downloadmanager.common.util.SharedPreferenceUtils;
import com.naver.downloadmanager.common.util.Utils;
import com.naver.downloadmanager.data.datasource.URLData;
import com.naver.downloadmanager.framework.URLViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private RecyclerView mRecyclerView = null;
    private URLAdapter mUrlAdapter = null;
    private URLViewModel mUrlViewModel = null;
    private NetworkConnection mNetworkConnection = null;
    private DownloadReceiver mDownloadReceiver = null;
    private Context mContext = null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = getApplicationContext();

        // check the shared preference value
        List<URLData> urls = SharedPreferenceUtils.getData(mContext, Constant.URL_KEY);

        mRecyclerView = findViewById(R.id.recyclerView);
        mUrlViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(URLViewModel.class);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mUrlAdapter = new URLAdapter(mContext);
        mRecyclerView.setAdapter(mUrlAdapter);

        mUrlViewModel.addUrl(urls);

        Button getUrlListBtn = (Button) findViewById(R.id.url_button);
        Button downloadBtn = (Button) findViewById(R.id.download_button);
        getUrlListBtn.setOnClickListener(this);
        downloadBtn.setOnClickListener(this);

        CheckBox selectedAllCheckBox = (CheckBox) findViewById(R.id.selectAllBtn);

        selectedAllCheckBox.setOnCheckedChangeListener(this);
        selectedAllCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d("Moonsu", "onCheckedChanaged selectall btn");
                mUrlAdapter.selectALL(isChecked);
            }
        });

        mUrlViewModel.getUrls().observe(this, new Observer<List<URLData>>() {
            @Override
            public void onChanged(List<URLData> list) {
                Log.d("Moonsu", "onChanged");
                mUrlAdapter.setUrls(list);
                SharedPreferenceUtils.setData(mContext, Constant.URL_KEY, list);
            }
        });

        mUrlViewModel.isSelectedALL().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isSelectedAll) {
                checkCheckBox(selectedAllCheckBox, isSelectedAll);
            }
        });


        mNetworkConnection = new NetworkConnection(mContext);
        mNetworkConnection.register();

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        intentFilter.addAction(DownloadManager.ACTION_NOTIFICATION_CLICKED);
        mDownloadReceiver = new DownloadReceiver();
        registerReceiver(mDownloadReceiver, intentFilter);
    }

    private void checkCheckBox(CheckBox checkBox, boolean checked) {
        checkBox.setOnCheckedChangeListener(null);
        checkBox.setChecked(checked);
        checkBox.setOnCheckedChangeListener(this);
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

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.download_button) {
            if (mNetworkConnection.isNetworkConnected()) {
                List<URLData> urlDataList = mUrlViewModel.getUrls().getValue();
                for (URLData urlData : urlDataList) {
                    if (urlData.isChecked()) {
                        Log.d("Moonsu", "isChecked : " + urlData.getId());
                        long downloadId = DownloadUtils.downloadFile(mContext, urlData.getUrl());
                        urlData.setDownloadId(downloadId);
                    }
                }
                mUrlViewModel.addUrl(urlDataList);
                return ;
            }
            Toast.makeText(mContext, "Network has not connected", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.url_button) {
            List<URLData> mUrls = new ArrayList<>();
            mUrls.add(new URLData(1, "https://imageproxy.themaven.net//https%3A%2F%2Fwww.history.com%2F.image%2FMTY4OTA4MzI0ODc4NjkwMDAw%2Fchristmas-tree-gettyimages-1072744106.jpg"));
            mUrls.add(new URLData(2, "https://www.history.com/.image/c_fill%2Ccs_srgb%2Cfl_progressive%2Ch_400%2Cq_auto:good%2Cw_620/MTY4ODE4ODA4MzY1MDAwNDY1/christmas-gettyimages-184652817.jpg"));
            mUrls.add(new URLData(3, "https://api.time.com/wp-content/uploads/2019/06/what-is-half-christmas-workaholics.jpg"));
            mUrls.add(new URLData(4, "https://www.scotsman.com/webimg/b25lY21zOjBlZjVkYThkLThjMTktNDM4Yi1hZjBhLTk2MWM3MTMyZDVlZjo4ZjhmMzY0MC1jYzBiLTQ2ZTYtOWUwMi05YmU3MTdkMTRhYjQ=.jpg"));
            mUrls.add(new URLData(5, "https://www.history.com/.image/ar_16:9%2Cc_fill%2Ccs_srgb%2Cfl_progressive%2Cg_faces:center%2Cq_auto:good%2Cw_768/MTY5MDk1ODMyOTUwNTQ4MzYy/american-christmas-traditions-gettyimages-487756624.jpg"));
            mUrls.add(new URLData(6, "https://www.thoughtco.com/thmb/EeiZ2qNO8kAdeTXZ1MUFCWiwMEQ=/1885x1414/smart/filters:no_upscale()/close-up-of-christmas-tree-898733426-5c79da8a46e0fb00018bd7fb.jpg"));
            mUrls.add(new URLData(7, "https://static.officeholidays.com/images/1280x853c/christmas.jpg"));
            mUrls.add(new URLData(8, "https://imageproxy.themaven.net//https%3A%2F%2Fwww.history.com%2F.image%2FMTY4OTA4MzI0ODc4NjkwMDAw%2Fchristmas-tree-gettyimages-1072744106.jpg"));
            mUrls.add(new URLData(9, "https://www.history.com/.image/c_fill%2Ccs_srgb%2Cfl_progressive%2Ch_400%2Cq_auto:good%2Cw_620/MTY4ODE4ODA4MzY1MDAwNDY1/christmas-gettyimages-184652817.jpg"));
            mUrls.add(new URLData(10, "https://api.time.com/wp-content/uploads/2019/06/what-is-half-christmas-workaholics.jpg"));
            mUrls.add(new URLData(11, "https://www.scotsman.com/webimg/b25lY21zOjBlZjVkYThkLThjMTktNDM4Yi1hZjBhLTk2MWM3MTMyZDVlZjo4ZjhmMzY0MC1jYzBiLTQ2ZTYtOWUwMi05YmU3MTdkMTRhYjQ=.jpg"));
            mUrls.add(new URLData(12, "https://www.history.com/.image/ar_16:9%2Cc_fill%2Ccs_srgb%2Cfl_progressive%2Cg_faces:center%2Cq_auto:good%2Cw_768/MTY5MDk1ODMyOTUwNTQ4MzYy/american-christmas-traditions-gettyimages-487756624.jpg"));
            mUrls.add(new URLData(13, "https://www.thoughtco.com/thmb/EeiZ2qNO8kAdeTXZ1MUFCWiwMEQ=/1885x1414/smart/filters:no_upscale()/close-up-of-christmas-tree-898733426-5c79da8a46e0fb00018bd7fb.jpg"));
            mUrls.add(new URLData(14, "https://static.officeholidays.com/images/1280x853c/christmas.jpg"));
            mUrls.add(new URLData(15, "https://imageproxy.themaven.net//https%3A%2F%2Fwww.history.com%2F.image%2FMTY4OTA4MzI0ODc4NjkwMDAw%2Fchristmas-tree-gettyimages-1072744106.jpg"));
            mUrls.add(new URLData(16, "https://www.history.com/.image/c_fill%2Ccs_srgb%2Cfl_progressive%2Ch_400%2Cq_auto:good%2Cw_620/MTY4ODE4ODA4MzY1MDAwNDY1/christmas-gettyimages-184652817.jpg"));
            mUrls.add(new URLData(17, "https://api.time.com/wp-content/uploads/2019/06/what-is-half-christmas-workaholics.jpg"));
            mUrls.add(new URLData(18, "https://www.scotsman.com/webimg/b25lY21zOjBlZjVkYThkLThjMTktNDM4Yi1hZjBhLTk2MWM3MTMyZDVlZjo4ZjhmMzY0MC1jYzBiLTQ2ZTYtOWUwMi05YmU3MTdkMTRhYjQ=.jpg"));
            mUrls.add(new URLData(19, "https://www.history.com/.image/ar_16:9%2Cc_fill%2Ccs_srgb%2Cfl_progressive%2Cg_faces:center%2Cq_auto:good%2Cw_768/MTY5MDk1ODMyOTUwNTQ4MzYy/american-christmas-traditions-gettyimages-487756624.jpg"));
            mUrls.add(new URLData(20, "https://www.thoughtco.com/thmb/EeiZ2qNO8kAdeTXZ1MUFCWiwMEQ=/1885x1414/smart/filters:no_upscale()/close-up-of-christmas-tree-898733426-5c79da8a46e0fb00018bd7fb.jpg"));
            mUrls.add(new URLData(21, "https://static.officeholidays.com/images/1280x853c/christmas.jpg"));
            mUrls.add(new URLData(22, "https://imageproxy.themaven.net//https%3A%2F%2Fwww.history.com%2F.image%2FMTY4OTA4MzI0ODc4NjkwMDAw%2Fchristmas-tree-gettyimages-1072744106.jpg"));
            mUrls.add(new URLData(23, "https://www.history.com/.image/c_fill%2Ccs_srgb%2Cfl_progressive%2Ch_400%2Cq_auto:good%2Cw_620/MTY4ODE4ODA4MzY1MDAwNDY1/christmas-gettyimages-184652817.jpg"));
            mUrls.add(new URLData(24, "https://api.time.com/wp-content/uploads/2019/06/what-is-half-christmas-workaholics.jpg"));
            mUrls.add(new URLData(25, "https://www.scotsman.com/webimg/b25lY21zOjBlZjVkYThkLThjMTktNDM4Yi1hZjBhLTk2MWM3MTMyZDVlZjo4ZjhmMzY0MC1jYzBiLTQ2ZTYtOWUwMi05YmU3MTdkMTRhYjQ=.jpg"));
            mUrls.add(new URLData(26, "https://www.history.com/.image/ar_16:9%2Cc_fill%2Ccs_srgb%2Cfl_progressive%2Cg_faces:center%2Cq_auto:good%2Cw_768/MTY5MDk1ODMyOTUwNTQ4MzYy/american-christmas-traditions-gettyimages-487756624.jpg"));
            mUrls.add(new URLData(27, "https://www.thoughtco.com/thmb/EeiZ2qNO8kAdeTXZ1MUFCWiwMEQ=/1885x1414/smart/filters:no_upscale()/close-up-of-christmas-tree-898733426-5c79da8a46e0fb00018bd7fb.jpg"));
            mUrls.add(new URLData(28, "https://static.officeholidays.com/images/1280x853c/christmas.jpg"));
            mUrlViewModel.addUrl(mUrls);
            SharedPreferenceUtils.setData(mContext, Constant.URL_KEY, mUrls);
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        Log.d("Moonsu", "onCheckedChanaged selectall btn");
        mUrlAdapter.selectALL(isChecked);
    }

    private class DownloadReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            long id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
            String actionId = intent.getAction();
            List<URLData> urlDataList = mUrlViewModel.getUrls().getValue();
            URLData target = findUrlData(urlDataList, id);
            Log.d("Moonsu", "onReceive : " + target.getId());
            if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(actionId)) {
                boolean isSuccessDownload = DownloadUtils.isSuccessDownload(mContext, target.getDownloadId());
                Log.d("Moonsu", "download complete : " + isSuccessDownload);
                target.setState(isSuccessDownload ? URLData.STATE.SUCCESS : URLData.STATE.FAIL);
                mUrlViewModel.addUrl(urlDataList);
            } else if (DownloadManager.ACTION_NOTIFICATION_CLICKED.equals(actionId)) {
                Log.d("Moonsu", "clicked notifiaction");
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
