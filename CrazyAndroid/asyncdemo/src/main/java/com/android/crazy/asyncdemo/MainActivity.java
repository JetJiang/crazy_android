package com.android.crazy.asyncdemo;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.android.crazy.asyncdemo.util.DownloadTask;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * 异步任务
 */
public class MainActivity extends Activity {

    public static TextView tipText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tipText = (TextView) findViewById(R.id.tipText);
        //设置textView自带滚动条
        tipText.setMovementMethod(ScrollingMovementMethod.getInstance());

    }

    /**
     * 点击事件
     * @param view
     */
    public void start(View view){
        //必须在主线程中创建AsyncTask实例，并在主线程中执行execute方法
        DownloadTask task = new DownloadTask(MainActivity.this);
        try {
            task.execute(new URL("http://www.huxiu.com/article/139343/1.html?f=index_feed_article"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }



}
