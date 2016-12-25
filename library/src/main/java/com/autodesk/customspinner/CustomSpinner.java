package com.autodesk.customspinner;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Adapter;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by sagiyemini on 25/12/2016.
 */

public class CustomSpinner extends LinearLayout {

    private Adapter mAdapter;

    private int mSelectedItemPosition;
    private TextView mTitle;

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
    }

    public void setAdapter(Adapter adapter) {
        this.mAdapter = adapter;
        this.mSelectedItemPosition = 0;
        if (adapter != null) {
            if (adapter.getCount() > 0) {
                mTitle.setText(adapter.getItem(mSelectedItemPosition).toString());
            }
        }
    }
}
