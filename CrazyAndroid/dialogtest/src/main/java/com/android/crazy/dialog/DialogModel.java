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
     * ʹ��AlertDialog�����Ի���
     * @param context
     * @return
     */
    public Dialog dialogSimple(final Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setTitle("�򵥵ĶԻ���");
        builder.setMessage("����һ���򵥵ĶԻ���ֻ��ȷ����ȡ����ť");
        builder.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context, "�������ȷ����", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context,"�������ȡ����",Toast.LENGTH_SHORT).show();
            }
        });
        return builder.create();
    }
}
