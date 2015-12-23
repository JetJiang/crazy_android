package com.android.crazy.activitylifetest;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Jet
 * Created by Administrator on 2015/12/23.
 */
public class DialogStyleActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView textView = new TextView(DialogStyleActivity.this);
        textView.setText("这是对话框风格的Activity");
        setContentView(textView);
    }
}
