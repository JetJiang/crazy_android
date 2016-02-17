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
 * �첽����
 */
public class MainActivity extends Activity {

    public static TextView tipText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tipText = (TextView) findViewById(R.id.tipText);
        //����textView�Դ�������
        tipText.setMovementMethod(ScrollingMovementMethod.getInstance());

    }

    /**
     * ����¼�
     * @param view
     */
    public void start(View view){
        //���������߳��д���AsyncTaskʵ�����������߳���ִ��execute����
        DownloadTask task = new DownloadTask(MainActivity.this);
        try {
            task.execute(new URL("http://www.huxiu.com/article/139343/1.html?f=index_feed_article"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }



}
