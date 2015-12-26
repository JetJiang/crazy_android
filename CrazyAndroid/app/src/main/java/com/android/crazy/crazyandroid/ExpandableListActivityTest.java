package com.android.crazy.crazyandroid;

import android.app.ExpandableListActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Jet
 */
public class ExpandableListActivityTest extends ExpandableListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ExpandableListAdapter adapter = new BaseExpandableListAdapter() {
            int[] logos = new int[]{
                    R.drawable.p,
                    R.drawable.z,
                    R.drawable.t
            };
            private String[] armTypes = new String[]{"神族","虫族","人族"};
            private String[][] arms = new String[][]{
                    { "狂战士", "龙骑士", "黑暗圣堂", "电兵" },
                    { "小狗", "刺蛇", "飞龙", "自爆飞机" },
                    { "机枪兵", "护士MM" , "幽灵" }
            };


            //获取指定组位置、指定组列表项处的子列表项数据
            @Override
            public Object getChild(int groupPosition, int childPosition) {
                return arms[groupPosition][childPosition];
            }

            @Override
            public long getChildId(int groupPosition, int childPosition) {
                return childPosition;
            }
            @Override
            public int getChildrenCount(int groupPosition) {
                return arms[groupPosition].length;
            }

            private TextView getTextView(){
                AbsListView.LayoutParams lp = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,64);
                TextView textView = new TextView(ExpandableListActivityTest.this);
                textView.setLayoutParams(lp);
                textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
                textView.setPadding(36,0,0,0);
                textView.setTextSize(20);
                return textView;
            }
            //该方法决定每个子选项的外观
            @Override
            public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
                TextView textView = getTextView();
                textView.setText(getChild(groupPosition, childPosition).toString());
                return textView;
            }

            //获取指定组位置处的数据
            @Override
            public Object getGroup(int groupPosition) {
                return armTypes[groupPosition];
            }
            @Override
            public long getGroupId(int groupPosition) {
                return groupPosition;
            }
            @Override
            public int getGroupCount() {
                return armTypes.length;
            }


            //该方法决定每个组选项的外观
            @Override
            public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
                LinearLayout ll = new LinearLayout(ExpandableListActivityTest.this);
                ll.setOrientation(LinearLayout.HORIZONTAL);
                ImageView imageView = new ImageView(ExpandableListActivityTest.this);
                imageView.setImageResource(logos[groupPosition]);
                ll.addView(imageView);
                TextView textView = getTextView();
                textView.setText(getGroup(groupPosition).toString());
                ll.addView(textView);
                return ll;
            }

            @Override
            public boolean hasStableIds() {
                return true;
            }

            @Override
            public boolean isChildSelectable(int groupPosition, int childPosition) {
                return true;
            }
        };
        setListAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.expandable_test, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
