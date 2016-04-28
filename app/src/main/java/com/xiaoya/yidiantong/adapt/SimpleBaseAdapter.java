package com.xiaoya.yidiantong.adapt;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Author: meyu
 * Date:   16/4/26
 * Email:  627655140@qq.com
 */
public abstract class SimpleBaseAdapter<T> extends BaseAdapter {
    protected Context mContext;
    private LayoutInflater mInflater;
    protected List<T> mList;

    public SimpleBaseAdapter(Context paramContext) {
        init(paramContext);
    }

    public SimpleBaseAdapter(Context paramContext, List<T> paramList) {
        this.mList = paramList;
        init(paramContext);
    }

    private void init(Context paramContext) {
        this.mContext = paramContext;
        this.mInflater = LayoutInflater.from(paramContext);
    }

    public int getCount() {
        if (this.mList == null)
            return 0;
        return this.mList.size();
    }

    protected LayoutInflater getInflater() {
        return this.mInflater;
    }

    public Object getItem(int paramInt) {
        if (this.mList == null)
            return null;
        return this.mList.get(paramInt);
    }

    public long getItemId(int paramInt) {
        return paramInt;
    }

    public List<T> getList() {
        return this.mList;
    }

    public abstract View getView(int paramInt, View paramView, ViewGroup paramViewGroup);

    public void setList(List<T> paramList) {
        this.mList = paramList;
    }
}