package com.naver.downloadmanager.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;
import com.naver.downloadmanager.data.datasource.URLData;
import com.naver.downloadmanager.databinding.UrlItemBinding;
import com.naver.downloadmanager.viewmodel.IURLViewModel;

import java.util.ArrayList;
import java.util.List;

public class URLAdapter extends RecyclerView.Adapter<URLAdapter.ViewHolder> {
    LifecycleOwner lifecycleOwner;
    IURLViewModel viewModel;
    List<URLData> mUrls;

    public URLAdapter(LifecycleOwner lifecycleOwner, IURLViewModel viewModel) {
        this.lifecycleOwner = lifecycleOwner;
        this.viewModel = viewModel;
        mUrls = new ArrayList<URLData>();
    }

    public void setUrls(List<URLData> urls) {
        mUrls.clear();
        mUrls.addAll(urls);
        notifyDataSetChanged();
    }

    public List<URLData> getUrls() {
        notifyDataSetChanged();
        return mUrls;
    }

    public List<URLData> selectALL(boolean isChecked) {
        for (URLData urlData : mUrls) {
            urlData.setChecked(isChecked);
        }
        notifyDataSetChanged();
        return mUrls;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        UrlItemBinding itemBinding = UrlItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(itemBinding, lifecycleOwner, viewModel);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(mUrls.get(position));
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
        IURLViewModel viewModel;
        LifecycleOwner lifecycleOwner;
        UrlItemBinding itemBinding;

        public ViewHolder(@NonNull UrlItemBinding itemBinding, LifecycleOwner lifecycleOwner, IURLViewModel viewModel) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
            this.lifecycleOwner = lifecycleOwner;
            this.viewModel = viewModel;
        }

        public void bind(URLData item) {
            itemBinding.setItem(item);
            itemBinding.setLifecycleOwner(lifecycleOwner);
            itemBinding.setViewModel(viewModel);
        }
    }
}

