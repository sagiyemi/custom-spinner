package com.autodesk.customspinner;

import android.content.Context;
import android.support.v7.widget.ListPopupWindow;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;

/**
 * Created by sagiyemini on 25/12/2016.
 */

public class CustomSpinner extends LinearLayout implements View.OnClickListener, AdapterView.OnItemClickListener {

    private static final String TAG = "CustomSpinner";

    private TextView mTitle;
    private ImageView mIcon;

    private ListAdapter mAdapter;
    private ListPopupWindow mPopup;

    private int mSelectedItemPosition;

    public CustomSpinner(Context context) {
        super(context);
        init();
    }

    public CustomSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomSpinner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.custom_spinner, this);
        this.mTitle = (TextView) findViewById(R.id.title);
        this.mIcon = (ImageView) findViewById(R.id.icon);
        this.setOnClickListener(this);
        this.mPopup = new ListPopupWindow(getContext());
    }

    public void setAdapter(ListAdapter adapter) {
        this.mAdapter = adapter;
        this.mSelectedItemPosition = 0;
        if (adapter != null) {
            mIcon.setVisibility(isMultiItem() ? VISIBLE : GONE);
            setSelectedItem(0);
        }
    }

    @Override
    public void onClick(View view) {
        Log.wtf(TAG, "Click");
        if (isMultiItem()) {
            showDropDown();
        }
    }

    private void showDropDown() {
        mPopup.setAdapter(mAdapter);
        mPopup.setOnItemClickListener(this);
        mPopup.setAnchorView(this);
        mPopup.setWidth(300);
        mPopup.setHeight(300);
        mPopup.show();
    }

    private boolean isMultiItem() {
        return mAdapter != null && mAdapter.getCount() > 1;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d(TAG, "onItemClick " + position + " " + id);
        setSelectedItem(position);
        mPopup.dismiss();
    }

    private void setSelectedItem(int position) {
        mSelectedItemPosition = position;
        mTitle.setText(mAdapter.getItem(mSelectedItemPosition).toString());
    }
}
