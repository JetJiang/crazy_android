package com.android.crazy.activitylifetest;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity {

    private String TAG = "ActivityLife";
    private Button newBtn;
    private Button alertBtn;
    private Button finishBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "---onCreate---");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newBtn = (Button)findViewById(R.id.newBtn);
        alertBtn = (Button)findViewById(R.id.alertBtn);
        finishBtn = (Button)findViewById(R.id.finishBtn);
        newBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
        alertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("TIPS");
                builder.setMessage("Hello there.");
                builder.setPositiveButton("NEW", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
            }
        });
    }

    @Override
    protected void onStart() {
        Log.d(TAG,"---onStart---");
        super.onStart();

    }

    @Override
    protected void onResume() {
        Log.d(TAG,"---onResume---");
        super.onResume();
    }

    @Override
    protected void onRestart() {
        Log.d(TAG,"---onRestart---");
        super.onRestart();
    }

    @Override
    protected void onStop() {
        Log.d(TAG,"---onStop---");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG,"---onDestroy---");
        super.onDestroy();
    }
}
