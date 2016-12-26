package com.autodesk.customspinner;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v4.view.GravityCompat;
import android.support.v7.widget.ListPopupWindow;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by sagiyemini on 25/12/2016.
 */

public class CustomSpinner<T extends SpinnerDropDownItem> extends LinearLayout implements View.OnClickListener, AdapterView.OnItemClickListener {

    private static final String TAG = "CustomSpinner";

    private int mDrawablePaddingLeft = Util.dpToPx(getContext(), 4f);
    private int mDrawablePaddingTop = Util.dpToPx(getContext(), 0f);
    private int mDrawablePaddingRight = Util.dpToPx(getContext(), 4f);
    private int mDrawablePaddingBottom = Util.dpToPx(getContext(), 0f);
    private int mDrawableRight = R.drawable.ic_keyboard_arrow_down_white_24dp;
    private int mDropdownMaxHeight = ListPopupWindow.WRAP_CONTENT;
    private int mDropdownVerticalOffset = Util.dpToPx(getContext(), 8f);
    private int mDropdownWidth = Util.dpToPx(getContext(), 200f);
    private ColorStateList mSpinnerTextColor;
    private float mSpinnerTextSize;
    private boolean mHideSelectedItem = false;

    private TextView mTitle;
    private ImageView mIcon;

    private CustomSpinnerAdapter<T> mAdapter;
    private ListPopupWindow mPopup;
    private OnItemSelectedListener<T> mOnItemSelectedListener;

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
        mPopup.setWidth(mDropdownWidth);
        mPopup.setHeight(mDropdownMaxHeight);
        mPopup.setModal(true);
        mPopup.setDropDownGravity(GravityCompat.END);

        TypedArray attr = context.obtainStyledAttributes(attrs, R.styleable.CustomSpinner, 0, 0);

        mDrawablePaddingLeft = attr.getDimensionPixelSize(R.styleable.CustomSpinner_cs_drawable_paddingLeft, mDrawablePaddingLeft);
        mDrawablePaddingTop = attr.getDimensionPixelSize(R.styleable.CustomSpinner_cs_drawable_paddingTop, mDrawablePaddingTop);
        mDrawablePaddingRight = attr.getDimensionPixelSize(R.styleable.CustomSpinner_cs_drawable_paddingRight, mDrawablePaddingRight);
        mDrawablePaddingBottom = attr.getDimensionPixelSize(R.styleable.CustomSpinner_cs_drawable_paddingBottom, mDrawablePaddingBottom);
        mDrawableRight = attr.getResourceId(R.styleable.CustomSpinner_cs_drawable_right, mDrawableRight);
        mHideSelectedItem = attr.getBoolean(R.styleable.CustomSpinner_cs_hide_selected_item, mHideSelectedItem);
        mDropdownMaxHeight = attr.getDimensionPixelSize(R.styleable.CustomSpinner_cs_dropdown_max_height, mDropdownMaxHeight);
        mDropdownWidth = attr.getDimensionPixelSize(R.styleable.CustomSpinner_cs_dropdown_width, mDropdownWidth);
        mDropdownVerticalOffset = attr.getDimensionPixelSize(R.styleable.CustomSpinner_cs_dropdown_vertical_offset, mDropdownVerticalOffset);
        mSpinnerTextColor = attr.getColorStateList(R.styleable.CustomSpinner_cs_spinner_text_color);
        if (mSpinnerTextColor == null) {
            mSpinnerTextColor = ColorStateList.valueOf(Color.WHITE);
        }
        mSpinnerTextSize = attr.getDimension(R.styleable.CustomSpinner_cs_spinner_text_size, getResources().getDimension(R.dimen.spinner_text_size));
        final int openDirectionEnum = attr.getInteger(R.styleable.CustomSpinner_cs_dropdown_open_direction, 0);
        mPopup.setDropDownGravity(openDirectionEnum == 0 ? GravityCompat.START : GravityCompat.END);

        attr.recycle();

        mIcon.setVisibility(GONE);
        mIcon.setImageResource(mDrawableRight);
        mIcon.setPadding(mDrawablePaddingLeft, mDrawablePaddingTop, mDrawablePaddingRight, mDrawablePaddingBottom);
        mTitle.setTextColor(mSpinnerTextColor);
        mTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, mSpinnerTextSize);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // Offset dropdown anchor to the top of the anchor view
        mPopup.setVerticalOffset(-getMeasuredHeight() + mDropdownVerticalOffset);
    }

    public void setAdapter(CustomSpinnerAdapter<T> adapter) {
        mAdapter = adapter;
        mAdapter.setHideSelectedItem(mHideSelectedItem);
        mPopup.setAdapter(mAdapter);
        mIcon.setVisibility(isMultiItem() ? VISIBLE : GONE);
        setSelectedItem(0);
    }

    public void setOnItemSelectedListener(OnItemSelectedListener<T> onItemSelectedListener) {
        mOnItemSelectedListener = onItemSelectedListener;
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
        if (mOnItemSelectedListener != null) {
            mOnItemSelectedListener.itemSelected(mAdapter.getSelectedItem());
        }
    }

    private void setSelectedItem(int position) {
        mAdapter.setSelectedItem(position);
        mAdapter.notifyDataSetChanged();
        mTitle.setText(mAdapter.getSelectedItemTitle(getResources()));
    }

    public void setInitialSelectedItem(int position) {
        mAdapter.setInitialSelectedItem(position);
        mAdapter.notifyDataSetChanged();
        mTitle.setText(mAdapter.getSelectedItemTitle(getResources()));
    }
}
