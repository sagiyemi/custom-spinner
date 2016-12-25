package com.autodesk.customspinner;

import android.view.View;

/**
 * Created by sagiyemini on 25/12/2016.
 */

public interface ViewBinder<T extends SpinnerDropDownItem> {

    void bindView(View view, T item);

}
