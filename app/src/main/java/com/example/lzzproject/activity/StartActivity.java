package com.example.lzzproject.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.lzzproject.R;


/**
 * Created by Administrator on 2017/3/22.
 */

public class StartActivity extends Activity {
    private int sleep = 2000;
    private Context context = StartActivity.this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.split_activity);
        start();
    }
    private void start() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (sleep == 2000) {
                    try {
                        Thread.sleep(sleep);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Intent intent = new Intent(context, LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent1 = new Intent(context, RegisterActivity.class);
                    startActivity(intent1);
                }
            }

        }).start();
    }
}


