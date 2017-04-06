package com.example.lzzproject.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lzzproject.R;
import com.hyphenate.EMCallBack;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;

import java.util.List;

/**
 * Created by 泽泽 on 2017/3/31.
 */

public class DialogueActivity extends AppCompatActivity implements EMMessageListener {
    private ListView leftInformation, RightInformation;
    private EditText content;
    private TextView rece;
    private Button save;
    EMMessageListener msgListener;
    EMMessage message;
    private String username;
    List<EMMessage> messages;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.dialogue_fragment);
        super.onCreate(savedInstanceState);
        EMClient.getInstance().chatManager().addMessageListener(msgListener);
        username = getIntent().getStringExtra("userName");
        initView();
        initListView();
    }

    private void initListView() {
        EMConversation conversation = EMClient.getInstance().chatManager().getConversation(username);
//获取此会话的所有消息
        messages = conversation.getAllMessages();

    }

    private void initView() {
        rece = (TextView) findViewById(R.id.item_dialogue_fragment_name);
        leftInformation = (ListView) findViewById(R.id.dialogue_fragment_receive);
        RightInformation = (ListView) findViewById(R.id.dialogue_fragment_send);
        content = (EditText) findViewById(R.id.dialogue_fragment_content);
        save = (Button) findViewById(R.id.dialogue_fragment_save);
        saveInfor();
    }

    private void saveInfor() {
        String text = content.getText().toString();
        String toChatUsername = "  123";
        //创建一条文本消息，content为消息文字内容，toChatUsername为对方用户或者群聊的id，后文皆是如此
        message = EMMessage.createTxtSendMessage(text, toChatUsername);
//如果是群聊，设置chattype，默认是单聊
//        if (chatType == CHATTYPE_GROUP)
//            message.setChatType(ChatType.GroupChat);
//发送消息
        EMClient.getInstance().chatManager().sendMessage(message);
//        设置消息的发送及接收状态。
        message.setMessageStatusCallback(new EMCallBack() {
            @Override
            public void onSuccess() {
                Log.e("onSuccess", "接收成功");
            }

            @Override
            public void onError(int i, String s) {
                Log.e("onError", "接收失败");
            }

            @Override
            public void onProgress(int i, String s) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //记得在不需要的时候移除listener，如在activity的onDestroy()时
        EMClient.getInstance().chatManager().removeMessageListener(msgListener);
    }

    @Override
    public void onMessageReceived(List<EMMessage> list) {
//收到消息
        for (final EMMessage emMsg :
                list) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    rece.setText(emMsg.getBody().toString());
                }
            });

            Log.e("Receinfoemation", "收到消息:" + emMsg.toString());
        }
    }

    @Override
    public void onCmdMessageReceived(List<EMMessage> list) {
        //收到透传消息
    }

    @Override
    public void onMessageReadAckReceived(List<EMMessage> list) {
        //收到已读回执
    }

    @Override
    public void onMessageDeliveryAckReceived(List<EMMessage> list) {
//收到已送达回执
    }

    @Override
    public void onMessageChanged(EMMessage emMessage, Object o) {
//消息状态变动
    }
}
