package com.example.lzzproject.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.lzzproject.MainActivity;
import com.example.lzzproject.R;
import com.example.lzzproject.view.TestDialog;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;

/**
 * Created by Administrator on 2017/3/22.
 */

public class LoginActivity extends Activity implements View.OnClickListener {
    ;
    private EditText userName, passWord;
    private Button login, register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        initview();
        new TestDialog(this,R.style.dialog);
    }

    private void initview() {
        userName = (EditText) findViewById(R.id.login_activity_userName);
        passWord = (EditText) findViewById(R.id.login_activity_passWord);
        login = (Button) findViewById(R.id.login_activity_login);
        register = (Button) findViewById(R.id.login_activity_register);
        login.setOnClickListener(this);
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_activity_login:
                String name = userName.getText().toString();
                String pass = passWord.getText().toString();
                if (name.equals("twoatwo") && pass.equals("2a2")) {
                    Intent in = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(in);
                    finish();
                }
                break;
            case R.id.login_activity_register:
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
        }
    }


    private void login(String userName, String password) {
        EMClient.getInstance().login(userName, password, new EMCallBack() {// 回调
            @Override
            public void onSuccess() {
                EMClient.getInstance().groupManager().loadAllGroups();
                EMClient.getInstance().chatManager().loadAllConversations();
                Log.d("s", "登录聊天服务器成功！");
            }

            @Override
            public void onProgress(int progress, String status) {

            }

            @Override
            public void onError(int code, String message) {
                Log.d("s", "登录聊天服务器失败！");
            }
        });
    }

}
