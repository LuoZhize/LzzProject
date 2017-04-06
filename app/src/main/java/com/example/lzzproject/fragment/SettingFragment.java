package com.example.lzzproject.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lzzproject.R;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;


/**
 * 设置页面
 * Created by HP on 2017/3/23.
 */
public class SettingFragment extends Fragment implements View.OnClickListener {
    private View view;
    Context context;
    private TextView ChangeName, Logout;
    private ImageView HeadPortrait;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.set_fragment, container, false);
        initView();
        return view;
    }

    private void initView() {
        ChangeName = (TextView) view.findViewById(R.id.set_fragment_name);
        Logout = (TextView) view.findViewById(R.id.set_fragment_end);
        HeadPortrait = (ImageView) view.findViewById(R.id.set_fragment_image);
        Logout.setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.set_fragment_name:
                break;
            case R.id.set_fragment_image:
                break;
            case R.id.set_fragment_end:
                EMClient.getInstance().logout(true, new EMCallBack() {

                    @Override
                    public void onSuccess() {

                        Toast.makeText(context, "退出成功", Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onProgress(int progress, String status) {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void onError(int code, String message) {
                        Toast.makeText(context, "退出失败", Toast.LENGTH_LONG).show();

                    }
                });
                break;
        }
    }
}
