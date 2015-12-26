package com.android.crazy.dialogtest;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.crazy.dialog.DialogModel;

/**
 * ExpandableListView
 * Jet
 */
public class MainActivity extends Activity {

    private ExpandableListView expandableListView;
    private String[] dlgGroup = new String[]{"使用AlertDialog创建对话框", "对话框风格的Activity", "PopupWindow", "ProgressDialog", "时间选择对话框"};
    private String[][] dlgChild = new String[][]{
            {"提示消息的对话框", "简单列表项对话框", "单选列表项对话框", "多选列表项对话框", "自定义列表项对话框", "自定义View对话框"},
            {},
            {},
            {},
            {"DatePickerDialog", "TimePickerDialog"}
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        expandableListView = (ExpandableListView) findViewById(R.id.expand_list);
        ExpandableListAdapter adapter = new BaseExpandableListAdapter() {
            @Override
            public int getGroupCount() {

                return dlgGroup.length;
            }

            @Override
            public int getChildrenCount(int groupPosition) {

                return dlgChild[groupPosition].length;
            }

            @Override
            public Object getGroup(int groupPosition) {

                return dlgGroup[groupPosition];
            }

            @Override
            public Object getChild(int groupPosition, int childPosition) {

                return dlgChild[groupPosition][childPosition];
            }

            @Override
            public long getGroupId(int groupPosition) {

                return groupPosition;
            }

            @Override
            public long getChildId(int groupPosition, int childPosition) {

                return childPosition;
            }

            @Override
            public boolean hasStableIds() {
                return true;
            }

            @Override
            public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
                TextView textView = getGroupTextView();
                textView.setText(getGroup(groupPosition).toString());
                return textView;
            }

            @Override
            public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
                TextView textView = getChildTextView();
                textView.setText(getChild(groupPosition, childPosition).toString());
                return textView;
            }

            @Override
            public boolean isChildSelectable(int groupPosition, int childPosition) {
                return true;
            }
        };
        expandableListView.setAdapter(adapter);
        //子节点点击事件
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                if (groupPosition == 0) {
                    switch (childPosition){
                        case 0:
                            DialogModel dlgModel = new DialogModel();
                            Dialog dlg = dlgModel.dialogSimple(MainActivity.this);
                            dlg.show();
                            break;
                        case 1:

                    }
                }else if(groupPosition==4){
                    DialogModel dlgModel = new DialogModel();
                    Dialog dlg = dlgModel.dialogSimple(MainActivity.this);
                    dlg.show();
                }
                return true;
            }
        });
        //父节点点击事件
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                switch (groupPosition) {
                    case 0:
                        return false;
                    case 1:

                        return true;
                    case 2:
                        break;
                    default:
                        return false;
                }
                return false;
            }
        });
    }

    /**
     * 子菜单样式
     * @return
     */
    private TextView getChildTextView() {
        AbsListView.LayoutParams lp = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 80);
        TextView textView = new TextView(MainActivity.this);
        textView.setLayoutParams(lp);
        textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
        textView.setPadding(66, 0, 0, 0);
        textView.setTextSize(20);
        return textView;
    }

    /**
     * 父菜单样式
     * @return
     */
    private TextView getGroupTextView() {
        AbsListView.LayoutParams lp = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100);
        TextView textView = new TextView(MainActivity.this);
        textView.setLayoutParams(lp);
        textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
        textView.setPadding(36, 0, 0, 0);
        textView.setTextSize(20);
        TextPaint tp = textView.getPaint();
        tp.setFakeBoldText(true);
        return textView;
    }

}
