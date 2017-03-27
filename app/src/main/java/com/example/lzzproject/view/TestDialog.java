package com.example.lzzproject.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.WindowManager;

import com.example.lzzproject.R;

/**
 * Created by 泽泽 on 2017/3/27.
 */

public class TestDialog extends ProgressDialog {
    public TestDialog(Context context) {
        super(context);
    }

    public TestDialog(Context context, int theme) {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setIndeterminate(true);
        setCanceledOnTouchOutside(true);
        setContentView(R.layout.load_dialog);
        WindowManager.LayoutParams arr = getWindow().getAttributes();
        arr.width = WindowManager.LayoutParams.WRAP_CONTENT;
        arr.alpha = 0.8f;
        arr.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(arr);
    }
}
