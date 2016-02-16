package com.android.crazy.handlerdemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Handlerģ��
 * ˼·���⣺
 * Handler��ʹ�ã����з��ͷ������շ������ͷ��������̣߳����շ������̡߳�
 * ���߳���sendEmptyMessage������Ϣ��
 * ���շ�ͨ��handleMessage����������Ϣ
 */
public class MainActivity extends Activity {

    private ImageView imageView;
    private Handler handler;
    private int[] imageIds = new int[]{
            R.mipmap.pic1,
            R.mipmap.pic2,
            R.mipmap.pic3,
            R.mipmap.pic4,
            R.mipmap.pic5
    };
    private int currentId = 0;
    private Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.imageview);
        //����Handler
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.what==123123){
                    if(currentId<10){
                        currentId++;
                    }else{
                        currentId = 0;
                    }
                    //����ͼƬ
                    imageView.setImageResource(imageIds[currentId%imageIds.length]);

                }
            }
        };
        //����Timer��ʱ��
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(123123);
            }
        },0,2000);
    }



}
