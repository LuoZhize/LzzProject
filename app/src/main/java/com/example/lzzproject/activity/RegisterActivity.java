package com.example.lzzproject.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lzzproject.R;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

/**
 * Created by Administrator on 2017/3/23.
 */

public class RegisterActivity extends Activity implements View.OnClickListener {
    private EditText userName, pass, newpass;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        initviwe();
    }

    private void initviwe() {
        userName = (EditText) findViewById(R.id.login_activity_userName);
        pass = (EditText) findViewById(R.id.login_activity_passWord);
        newpass = (EditText) findViewById(R.id.login_activity_passWord_new);
        button = (Button) findViewById(R.id.login_activity_register);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_activity_register:
                String name = userName.getText().toString();
                String pas = pass.getText().toString();
                String pas1 = newpass.getText().toString();
                if (pas.equals("") && pas1.equals("")) {
                    try {
                        EMClient.getInstance().createAccount(name, pas);// 同步方法
                        Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(this, LoginActivity.class);
                    } catch (HyphenateException e) {
                        e.printStackTrace();
                    }
                }else{
                    Toast.makeText(this, "注册失败", Toast.LENGTH_SHORT).show();
        }
    }
}
}
