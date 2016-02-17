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
 * ͨ��Looper��Handler�����ģ�������߳��м�������
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
        //�������߳�ʵ�������������߳�
        calThread = new CalThread();
        calThread.start();
        //��������UI��Handler������дhandleMessage�������ȴ�������Ϣ
        uiHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.what==123456){
                    //��ȡmessage��Ϣ
                    String result = msg.getData().getString("result");
                    showText.setText(result);
                    showText.invalidate();
                }
            }
        };
    }

    /**
     * ����¼�
     *
     * @param view
     */
    public void cal(View view) {
        int num = Integer.parseInt(editText.getText().toString());
        Bundle bundle = new Bundle();
        bundle.putInt("upper", num);
        //��װmessage
        Message message = new Message();
        message.what = 123123;
        message.setData(bundle);
        //������Ϣ�������ֳɵ�handler�������������������
        calThread.handler.sendMessage(message);
    }


}
