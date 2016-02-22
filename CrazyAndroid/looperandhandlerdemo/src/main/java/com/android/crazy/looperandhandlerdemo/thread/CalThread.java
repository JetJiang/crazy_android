package com.android.crazy.looperandhandlerdemo.thread;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.android.crazy.looperandhandlerdemo.MainActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Jet
 * Created by Administrator on 2016/2/17.
 */
public class CalThread extends Thread {
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
                    String result = numList.toString();

                    Bundle bundle = new Bundle();
                    bundle.putString("result",result);
                    Message message = new Message();
                    message.setData(bundle);
                    message.what = 123456;
                    MainActivity.uiHandler.sendMessage(message);
                }
            }
        };

        Looper.loop();

    }
}
