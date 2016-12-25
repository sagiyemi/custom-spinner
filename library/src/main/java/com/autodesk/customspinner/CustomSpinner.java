package com.autodesk.customspinner;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by sagiyemini on 25/12/2016.
 */

public class CustomSpinner extends LinearLayout {


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
    }

}
