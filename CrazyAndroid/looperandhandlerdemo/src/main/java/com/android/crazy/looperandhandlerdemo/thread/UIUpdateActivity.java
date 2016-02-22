package com.android.crazy.looperandhandlerdemo.thread;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.crazy.looperandhandlerdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Jet
 * Created by Administrator on 2016/2/22.
 */
public class UIUpdateActivity extends Activity{
    private EditText editText;
    private TextView showText;
    public static Handler uiHandler;
    public static int num;
    public static String result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.editText);
        showText = (TextView) findViewById(R.id.showText);
    }
    public void cal(View view){
        result = showText.getText().toString();
        new Thread(new Runnable() {
            @Override
            public void run() {
                //计算质数
                List<Integer> numList = new ArrayList<Integer>();
                outer:
                for (int i = 2; i < UIUpdateActivity.num; i++) {
                    for (int j = 2; j < Math.sqrt(i); j++) {
                        if (i % j == 0) {
                            continue outer;
                        }
                    }
                    numList.add(i);
                }
                String result = numList.toString();
                UIUpdateActivity.result = result;
                //通过runOnUiThread来更新UI
                UIUpdateActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showText.setText(UIUpdateActivity.result);
                    }
                });
            }
        }).start();
    }
}
