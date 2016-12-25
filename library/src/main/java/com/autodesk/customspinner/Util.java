package com.autodesk.customspinner;

import android.content.Context;

/**
 * Created by sagiyemini on 25/12/2016.
 */

public class Util {

    static int dpToPx(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return Math.round(dp * scale);
    }

}
