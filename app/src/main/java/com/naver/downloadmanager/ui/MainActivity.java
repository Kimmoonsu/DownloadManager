package com.naver.downloadmanager.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.naver.downloadmanager.R;
import com.naver.downloadmanager.data.datasource.URLData;
import com.naver.downloadmanager.framework.URLViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView = null;
    private URLAdapter mUrlAdapter = null;
    private URLViewModel mUrlViewModel = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recyclerView);
        mUrlViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(URLViewModel.class);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mUrlAdapter = new URLAdapter(getApplicationContext());
        mRecyclerView.setAdapter(mUrlAdapter);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<URLData> mUrls = new ArrayList<>();
                mUrls.add(new URLData(1, "https://www.naver.com"));
                mUrls.add(new URLData(2, "https://www.naver.com"));
                mUrls.add(new URLData(3, "https://www.naver.com"));
                mUrls.add(new URLData(4, "https://www.naver.com"));
                mUrls.add(new URLData(5, "https://www.naver.com"));
                mUrls.add(new URLData(6, "https://www.naver.com"));
                mUrls.add(new URLData(7, "https://www.naver.com"));
                URLViewModel.addUrl(mUrls);
            }
        });

        mUrlViewModel.urls.observe(this, new Observer<List<URLData>>() {
            @Override
            public void onChanged(List<URLData> list) {
                Log.d("Moonsu", "onChanged");

                mUrlAdapter.setUrls(list);
            }
        });

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
    }
}
