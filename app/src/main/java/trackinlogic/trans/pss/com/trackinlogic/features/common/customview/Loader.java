package trackinlogic.trans.pss.com.trackinlogic.features.common.customview;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import trackinlogic.trans.pss.com.trackinlogic.R;


/**
 * Created by Renault Nissan Technology & Business Center India Pvt Ltd
 * Copyright (c) 2016 Renault Nissan Technology & Business Center India Pvt Ltd
 */
public class Loader {

    private static Dialog progressDialog = null;

    /**
     * @param context
     */
    public static void showProgressBar(Context context) {
        try {
            if (progressDialog != null) {
                progressDialog = null;
            }
            progressDialog = new Dialog(context);
            if (!progressDialog.isShowing() && !((Activity)context).isFinishing()) {
                progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                progressDialog.setContentView(R.layout.layout_progress_bar);

                DisplayMetrics metrics = context.getResources().getDisplayMetrics();
                int width = metrics.widthPixels;
                int height = metrics.heightPixels;

                progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                progressDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

                progressDialog.getWindow().setLayout(width, height);
                progressDialog.getWindow().setGravity(Gravity.CENTER);

                progressDialog.setCancelable(false);
                progressDialog.setCanceledOnTouchOutside(false);

                progressDialog.show();
            }
        } catch (Exception e) {

        }
    }

    /**
     * Used for hiding the progress bar
     */
    public static void dismissProgressBar() {
        try {
            if (progressDialog != null) {
                if (progressDialog.isShowing())
                    progressDialog.dismiss();
                progressDialog = null;
            }
        } catch (Exception e) {

        }
    }
}