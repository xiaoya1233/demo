package com.example.wangyuan.viewpagerdemo.view;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by wangyuan on 2017/12/18.
 */

public class MyProgressDialog {

    private final ProgressDialog progressDialog;

    public MyProgressDialog(Context context) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setTitle("loading ...");
//        progressDialog.addContentView();
    }

  public void  getDialogShow(){

          progressDialog.show();
    }



    public void getDialogHide() {

         progressDialog.hide();
    }
}
