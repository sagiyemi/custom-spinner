package com.autodesk.customspinner;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sagiyemini on 25/12/2016.
 */

public class CustomSpinnerAdapter<T extends SpinnerDropDownItem> extends BaseAdapter {

    private List<T> mItems;
    private List<T> mVisibleItems = new ArrayList<>();
    private int mLayoutResId;
    private ViewBinder<T> mViewBinder;
    private int mSelectedItemPosition;
    private boolean mHideSelectedItem;

    public CustomSpinnerAdapter(@LayoutRes int dropDownItemResId, @NonNull ViewBinder<T> viewBinder) {
        this.mLayoutResId = dropDownItemResId;
        this.mViewBinder = viewBinder;
    }

    public void addAll(List<T> items) {
        mItems = items;
        mVisibleItems.clear();
        mVisibleItems.addAll(mItems);
    }

    private void invalidateVisibleItemsList() {
        mVisibleItems.clear();
        mVisibleItems.addAll(mItems);
        mVisibleItems.remove(mSelectedItemPosition);
    }

    public void setHideSelectedItem(boolean hide) {
        mHideSelectedItem = hide;
    }

    public List<T> getVisibleItems() {
        return mHideSelectedItem ? mVisibleItems : mItems;
    }

    @Override
    public int getCount() {
        return getVisibleItems().size();
    }

    @Override
    public T getItem(int position) {
        return getVisibleItems().get(position);
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

    public String getSelectedItemTitle() {
        return mItems.get(mSelectedItemPosition).title();
    }

    public void setSelectedItem(int selectedItemPosition) {
        mSelectedItemPosition = findSelectedItemPosition(selectedItemPosition);
        invalidateVisibleItemsList();
    }

    private int findSelectedItemPosition(int selectedItemPosition) {
        final long itemId = getItemId(selectedItemPosition);
        for (int position = 0; position < mItems.size(); position++) {
            if (mItems.get(position).hashCode() == itemId) {
                return position;
            }
        }
        return 0;
    }
}
