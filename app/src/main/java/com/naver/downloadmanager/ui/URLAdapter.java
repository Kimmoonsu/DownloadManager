package com.naver.downloadmanager.ui;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.naver.downloadmanager.common.Constant;
import com.naver.downloadmanager.common.util.SharedPreferenceUtils;
import com.naver.downloadmanager.data.datasource.URLData;
import com.naver.downloadmanager.databinding.UrlItemBinding;
import com.naver.downloadmanager.framework.URLViewModel;

import java.util.ArrayList;
import java.util.List;

public class URLAdapter extends RecyclerView.Adapter<URLAdapter.ViewHolder> {
    LifecycleOwner lifecycleOwner;
    List<URLData> mUrls;

    public URLAdapter(LifecycleOwner lifecycleOwner) {
        this.lifecycleOwner = lifecycleOwner;
        mUrls = new ArrayList<URLData>();
    }

    public void setUrls(List<URLData> urls) {
        mUrls.clear();
        mUrls.addAll(urls);
        notifyDataSetChanged();
    }

    public List<URLData> selectALL(boolean isChecked) {
        for (URLData urlData : mUrls) {
            urlData.setChecked(isChecked);
        }
        notifyDataSetChanged();
        return mUrls;
    }

    private boolean isSelectedALL() {
        for (URLData urlData : mUrls) {
            if (!urlData.isChecked()) {
                return false;
            }
        }
        return true;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        UrlItemBinding itemBinding = UrlItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(itemBinding, lifecycleOwner);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(mUrls.get(position));
//        holder.itemBinding.checkBox.setOnCheckedChangeListener();
        Log.d("Moonsu", "click");
        if (isSelectedALL()) {
//            URLViewModel.setSelectedAllFlag(true);
        }
    }

    @Override
    public int getItemCount() {
        return mUrls.size();
    }

    @Override
    public long getItemId(int position) {
        return mUrls.get(position).getId();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        LifecycleOwner lifecycleOwner;
        UrlItemBinding itemBinding;

        public ViewHolder(@NonNull UrlItemBinding itemBinding, LifecycleOwner lifecycleOwner) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
            this.lifecycleOwner = lifecycleOwner;
        }

        public void bind(URLData item) {
            itemBinding.setItem(item);
            itemBinding.setLifecycleOwner(lifecycleOwner);
        }
    }
}

