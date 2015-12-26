package com.android.crazy.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

/**
 * Jet
 * Created by Administrator on 2015/12/26.
 */
public class DialogModel {
    /**
     * 使用AlertDialog创建对话框
     * @param context
     * @return
     */
    public Dialog dialogSimple(final Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setTitle("简单的对话框");
        builder.setMessage("这是一个简单的对话框，只有确定、取消按钮");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context, "您点击了确定键", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context,"您点击了取消键",Toast.LENGTH_SHORT).show();
            }
        });
        return builder.create();
    }
}
