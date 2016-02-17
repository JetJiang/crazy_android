package com.android.crazy.asyncdemo.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.android.crazy.asyncdemo.MainActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * 异步下载类
 * doInBackground():执行耗时逻辑操作，在此期间需要调用父类的publishProgress()方法
 * onPostExecute():doInBackground()执行完毕后调用此方法
 * onPreExecute():doInBackground()执行前调用此方法
 * onProgressUpdate():doInBackground()执行过程中调用此方法
 * Jet
 * Created by Administrator on 2016/2/17.
 */
public class DownloadTask extends AsyncTask<URL, Integer, String> {

    private ProgressDialog progressDialog;
    private Context context;
    private int hasRead;

    public DownloadTask(Context ctx) {
        this.context = ctx;
    }

    /**
     * 后台执行
     *
     * @param params
     * @return
     */
    @Override
    protected String doInBackground(URL... params) {
        StringBuilder sb = new StringBuilder();
        try {
            URLConnection conn = params[0].openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
                hasRead++;
                //AsyncTask类中的方法
                publishProgress(hasRead);
            }
            //这里返回的值在onPostExecute方法中会用到
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 后台执行完毕后调用此方法
     *
     * @param s
     */
    @Override
    protected void onPostExecute(String s) {
        MainActivity.tipText.setText(s);
        if (progressDialog != null) {
            progressDialog.dismiss();
        }

    }

    /**
     * 后台执行之前调用此方法
     */
    @Override
    protected void onPreExecute() {
        //进度条对话框
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("任务正在执行中");
        progressDialog.setMessage("任务正在执行中，请等待...");
        progressDialog.setCancelable(false);
        progressDialog.setMax(300);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setIndeterminate(false);
        progressDialog.show();
    }

    /**
     * 后台执行过程中调用此方法
     *
     * @param values
     */
    @Override
    protected void onProgressUpdate(Integer... values) {
        MainActivity.tipText.setText("已经读了" + values[0] + "行");
        progressDialog.setProgress(values[0]);
    }
}
