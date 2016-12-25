package com.autodesk.customspinner;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by sagiyemini on 25/12/2016.
 */

public class CustomSpinnerAdapter<T extends SpinnerDropDownItem> extends BaseAdapter {

    private List<T> mItems;
    private int mLayoutResId;
    private ViewBinder<T> mViewBinder;

    public CustomSpinnerAdapter(@LayoutRes int dropDownItemResId, @NonNull ViewBinder<T> viewBinder) {
        this.mLayoutResId = dropDownItemResId;
        this.mViewBinder = viewBinder;
    }

    public void addAll(List<T> items) {
        this.mItems = items;
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public T getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).hashCode();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) viewGroup.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view1 = inflater.inflate(mLayoutResId, null);
        mViewBinder.bindView(view1, getItem(position));
        return view1;
    }
}
