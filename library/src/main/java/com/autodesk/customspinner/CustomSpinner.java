package com.autodesk.customspinner;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.ListPopupWindow;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by sagiyemini on 25/12/2016.
 */

public class CustomSpinner extends LinearLayout implements View.OnClickListener, AdapterView.OnItemClickListener {

    private static final String TAG = "CustomSpinner";

    private int mDrawablePaddingLeft = Util.dpToPx(getContext(), 8f);
    private int mDrawablePaddingTop = Util.dpToPx(getContext(), 0f);
    private int mDrawablePaddingRight = Util.dpToPx(getContext(), 8f);
    private int mDrawablePaddingBottom = Util.dpToPx(getContext(), 0f);
    private int mDrawableRight = R.drawable.ic_keyboard_arrow_down_white_24dp;
    private boolean mHideSelectedItem = false;

    private TextView mTitle;
    private ImageView mIcon;

    private CustomSpinnerAdapter mAdapter;
    private ListPopupWindow mPopup;

    public CustomSpinner(Context context) {
        this(context, null);
    }

    public CustomSpinner(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomSpinner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        inflate(context, R.layout.custom_spinner, this);
        mTitle = (TextView) findViewById(R.id.title);
        mIcon = (ImageView) findViewById(R.id.icon);
        setOnClickListener(this);
        mPopup = new ListPopupWindow(context);
        mPopup.setOnItemClickListener(this);
        mPopup.setAnchorView(this);
        mPopup.setWidth(500);
        mPopup.setHeight(600);
        mPopup.setModal(true);

        TypedArray attr = context.obtainStyledAttributes(attrs, R.styleable.CustomSpinner, 0, 0);

        mDrawablePaddingLeft = attr.getDimensionPixelSize(R.styleable.CustomSpinner_cs_drawable_paddingLeft, mDrawablePaddingLeft);
        mDrawablePaddingTop = attr.getDimensionPixelSize(R.styleable.CustomSpinner_cs_drawable_paddingTop, mDrawablePaddingTop);
        mDrawablePaddingRight = attr.getDimensionPixelSize(R.styleable.CustomSpinner_cs_drawable_paddingRight, mDrawablePaddingRight);
        mDrawablePaddingBottom = attr.getDimensionPixelSize(R.styleable.CustomSpinner_cs_drawable_paddingBottom, mDrawablePaddingBottom);
        mDrawableRight = attr.getResourceId(R.styleable.CustomSpinner_cs_drawable_right, mDrawableRight);
        mHideSelectedItem = attr.getBoolean(R.styleable.CustomSpinner_cs_hide_selected_item, mHideSelectedItem);

        attr.recycle();

        mIcon.setImageResource(mDrawableRight);
        mIcon.setPadding(mDrawablePaddingLeft, mDrawablePaddingTop, mDrawablePaddingRight, mDrawablePaddingBottom);
    }

    public void setAdapter(CustomSpinnerAdapter adapter) {
        mAdapter = adapter;
        mAdapter.setHideSelectedItem(mHideSelectedItem);
        mPopup.setAdapter(mAdapter);
        if (adapter != null) {
            mIcon.setVisibility(isMultiItem() ? VISIBLE : GONE);
            setSelectedItem(0);
        }
    }

    @Override
    public void onClick(View view) {
        if (mPopup.isShowing()) {
            mPopup.dismiss();
        } else {
            if (isMultiItem()) {
                showDropDown();
            }
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
        mAdapter.setSelectedItem(position);
        mAdapter.notifyDataSetChanged();
        mTitle.setText(mAdapter.getSelectedItemTitle());
    }
}
