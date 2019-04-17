package com.example.module_base.utils.recyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class RViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> mViews;

    public RViewHolder(@NonNull View itemView) {
        super(itemView);

        mViews = new SparseArray();
    }

    /**
     * 通过viewId获取控件
     *
     * @param viewId
     * @return
     */
    public  <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T)view;
    }

    public RViewHolder setText(int viewId, CharSequence text){
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }

    public RViewHolder setViewVisibility(int viewId, int visibility) {
        getView(viewId).setVisibility(visibility);
        return this;
    }

    public RViewHolder setImageResource(int viewId, int resourceId) {
        ImageView imageView = getView(viewId);
        imageView.setImageResource(resourceId);
        return this;
    }

    public RViewHolder setEditText(int viewId, CharSequence text) {
        EditText editText = getView(viewId);
        editText.setText(text);
        return this;
    }


}
