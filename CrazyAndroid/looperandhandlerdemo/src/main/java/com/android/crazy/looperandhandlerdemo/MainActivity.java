package com.android.crazy.looperandhandlerdemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * 通过Looper和Handler结合来模拟在新线程中计算质数
 */
public class MainActivity extends Activity {

    private EditText editText;
    private TextView numText;
    private CalThread calThread;
    private TextView showText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.editText);
        showText = (TextView) findViewById(R.id.showText);
        calThread = new CalThread();
        calThread.start();
    }

    /**
     * 点击事件
     *
     * @param view
     */
    public void cal(View view) {
        int num = Integer.parseInt(editText.getText().toString());
        Log.e("NUM", num + "");
        Bundle bundle = new Bundle();
        bundle.putInt("upper", num);
        Message message = new Message();
        message.what = 123123;
        message.setData(bundle);
        calThread.handler.sendMessage(message);
    }

    /**
     * 创建一个线程类
     */
    class CalThread extends Thread {
        public Handler handler;
        public void run() {
            Looper.prepare();
            handler = new Handler() {

                @Override
                public void handleMessage(Message msg) {
                    if (msg.what == 123123) {
                        int upperNum = msg.getData().getInt("upper");
                        List<Integer> numList = new ArrayList<Integer>();
                        outer:
                        for (int i = 2; i < upperNum; i++) {
                            for (int j = 2; j < Math.sqrt(i); j++) {
                                if (i % j == 0) {
                                    continue outer;
                                }
                            }
                            numList.add(i);
                        }
//                        showText.setText(numList.toString());
//                        showText.invalidate();
                        Toast.makeText(MainActivity.this, numList.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            };
            Looper.loop();
        }
    }

}
