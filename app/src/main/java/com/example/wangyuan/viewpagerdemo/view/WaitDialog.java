package com.example.wangyuan.viewpagerdemo.view;

import android.app.Activity;

import cn.pedant.SweetAlert.SweetAlertDialog;


/**
 * Created by Administrator on 2017/2/22 0022.
 */
public class WaitDialog {


    private static SweetAlertDialog dialog;


    public static SweetAlertDialog show2(Activity activity) {
        if (activity == null || activity.isDestroyed() || activity.isFinishing()) {
            return null;
        }
        SweetAlertDialog pDialog = new SweetAlertDialog(activity, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.setTitleText("等待中...");
        pDialog.setCancelable(false);
        pDialog.show();
        return pDialog;
    }

    public static void show(Activity activity) {
        try {
            if (activity == null || activity.isDestroyed() || activity.isFinishing() ) {
                return;
            }
            dialog = new SweetAlertDialog(activity, SweetAlertDialog.PROGRESS_TYPE);
            dialog.setTitleText("等待中...");
            dialog.setCancelable(false);
            dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void hide() {
        if (dialog != null) {
            dialog.dismissWithAnimation();
        }
    }

    public static SweetAlertDialog show(Activity activity, String msg) {
        if (activity == null || activity.isDestroyed() || activity.isFinishing()) {
            return null;
        }
        SweetAlertDialog pDialog = new SweetAlertDialog(activity, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.setTitleText(msg);
        pDialog.setCancelable(false);
        pDialog.show();
        return pDialog;
    }


    public static void hide(SweetAlertDialog dialog) {
        if (dialog != null) {
            dialog.dismissWithAnimation();
        }
    }


}

/**
 * final SweetAlertDialog pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
 * .setTitleText("Loading");
 * pDialog.show();
 * pDialog.setCancelable(false);
 * new CountDownTimer(800 * 7, 800) {
 * public void onTick(long millisUntilFinished) {
 * // you can change the progress bar color by ProgressHelper every 800 millis
 * i++;
 * switch (i){
 * case 0:
 * pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.blue_btn_bg_color));
 * break;
 * case 1:
 * pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.material_deep_teal_50));
 * break;
 * case 2:
 * pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.success_stroke_color));
 * break;
 * case 3:
 * pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.material_deep_teal_20));
 * break;
 * case 4:
 * pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.material_blue_grey_80));
 * break;
 * case 5:
 * pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.warning_stroke_color));
 * break;
 * case 6:
 * pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.success_stroke_color));
 * break;
 * }
 * }
 * <p>
 * public void onFinish() {
 * i = -1;
 * pDialog.setTitleText("Success!")
 * .setConfirmText("OK")
 * .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
 * }
 * }.start();
 */
