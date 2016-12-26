package com.autodesk.customspinner;

import android.support.annotation.StringRes;

/**
 * Created by sagiyemini on 25/12/2016.
 */

public interface SpinnerDropDownItem {

    String spinnerTitle();

    @StringRes
    int spinnerTitleResId();

}
