package com.naver.downloadmanager.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.naver.downloadmanager.R;
import com.naver.downloadmanager.data.datasource.URLData;

import java.util.ArrayList;
import java.util.List;

public class URLAdapter extends RecyclerView.Adapter<URLAdapter.ViewHolder> {
    Context context;
    List<URLData> mUrls;

    public URLAdapter(Context context) {
        this.context = context;
        mUrls = new ArrayList<>();
//        mUrls.add(new URLData(1, "https://www.naver.com"));
//        mUrls.add(new URLData(2, "https://www.naver.com"));
//        mUrls.add(new URLData(3, "https://www.naver.com"));
//        mUrls.add(new URLData(4, "https://www.naver.com"));
//        mUrls.add(new URLData(5, "https://www.naver.com"));
//        mUrls.add(new URLData(6, "https://www.naver.com"));
//        mUrls.add(new URLData(7, "https://www.naver.com"));
    }

    public void setUrls(List<URLData> urls) {
        mUrls = urls;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.url_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        URLData item = mUrls.get(position);
        holder.id.setText(""+item.getId());
        holder.url.setText(item.getUrl());
    }

    @Override
    public int getItemCount() {
        return mUrls.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView id;
        TextView url;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id);
            url = itemView.findViewById(R.id.url);
        }
    }
}

