package com.example.module_base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.module_base.utils.recyclerview.BaseBean;
import com.example.module_base.utils.recyclerview.RViewHolder;

import java.util.List;

/**
 * Java模式的adapter
 * */
public abstract class BaseAdapterJava<T extends BaseBean> extends RecyclerView.Adapter{

    protected LayoutInflater layoutInflater;
    protected List<T> dataList;

    public BaseAdapterJava(Context context, List<T> dataList) {
        this.layoutInflater = LayoutInflater.from(context);
        this.dataList = dataList;
    }

    /**
     * 替换
     * */
    public void update( List<T> dataList){
        this.dataList=dataList;
        notifyDataSetChanged();
    }

    public void addAll( List<T> dataList){
        this.dataList.addAll(dataList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return dataList.get(position).getViewType();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(viewType, parent, false);
        return new RViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        bindData((RViewHolder)viewHolder, dataList.get(position));
    }

    @Override
    public int getItemCount() {
        return dataList==null?0:dataList.size();
    }

    protected abstract void bindData(RViewHolder holder, T data);

}
