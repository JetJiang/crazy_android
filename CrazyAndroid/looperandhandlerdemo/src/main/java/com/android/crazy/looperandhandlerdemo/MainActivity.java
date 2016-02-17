package com.android.crazy.looperandhandlerdemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.crazy.looperandhandlerdemo.thread.CalThread;

/**
 * 通过Looper和Handler结合来模拟在新线程中计算质数
 */
public class MainActivity extends Activity {

    private EditText editText;
    private CalThread calThread;
    private TextView showText;
    public static Handler uiHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.editText);
        showText = (TextView) findViewById(R.id.showText);
        //创建新线程实例，并启动新线程
        calThread = new CalThread();
        calThread.start();
        //创建更新UI的Handler，并重写handleMessage方法，等待接收消息
        uiHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.what==123456){
                    //获取message消息
                    String result = msg.getData().getString("result");
                    showText.setText(result);
                    showText.invalidate();
                }
            }
        };
    }

    /**
     * 点击事件
     *
     * @param view
     */
    public void cal(View view) {
        int num = Integer.parseInt(editText.getText().toString());
        Bundle bundle = new Bundle();
        bundle.putInt("upper", num);
        //封装message
        Message message = new Message();
        message.what = 123123;
        message.setData(bundle);
        //发送消息给计算现成的handler，告诉其计算所有质数
        calThread.handler.sendMessage(message);
    }


}
