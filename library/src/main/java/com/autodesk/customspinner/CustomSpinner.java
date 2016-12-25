package com.autodesk.customspinner;

import android.content.Context;
import android.support.v7.widget.ListPopupWindow;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by sagiyemini on 25/12/2016.
 */

public class CustomSpinner<T extends SpinnerDropDownItem> extends LinearLayout implements View.OnClickListener, AdapterView.OnItemClickListener {

    private static final String TAG = "CustomSpinner";
    private static final int NO_ITEM_SELECTED = -1;

    private TextView mTitle;
    private ImageView mIcon;

    private ArrayAdapter<T> mAdapter;
    private ListPopupWindow mPopup;

    private int mSelectedItemPosition = NO_ITEM_SELECTED;

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
        mTitle = (TextView) findViewById(R.id.title);
        mIcon = (ImageView) findViewById(R.id.icon);
        setOnClickListener(this);
        mPopup = new ListPopupWindow(getContext());
        mPopup.setOnItemClickListener(this);
        mPopup.setAnchorView(this);
        mPopup.setWidth(500);
        mPopup.setHeight(700);
    }

    public void setAdapter(ArrayAdapter<T> adapter) {
        mAdapter = adapter;
        mPopup.setAdapter(mAdapter);
        if (mSelectedItemPosition == NO_ITEM_SELECTED) {
            this.mSelectedItemPosition = 0;
            if (adapter != null) {
                mIcon.setVisibility(isMultiItem() ? VISIBLE : GONE);
                setSelectedItem(0);
            }
        }
    }

    @Override
    public void onClick(View view) {
        if (isMultiItem()) {
            showDropDown();
        }
    }

    private void showDropDown() {
        mPopup.show();
    }

    private boolean isMultiItem() {
        return mAdapter != null && mAdapter.getCount() > 1;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        setSelectedItem(position);
        mPopup.dismiss();
    }

    private void setSelectedItem(int position) {
        mSelectedItemPosition = position;
        mTitle.setText(mAdapter.getItem(mSelectedItemPosition).title());
    }

}
