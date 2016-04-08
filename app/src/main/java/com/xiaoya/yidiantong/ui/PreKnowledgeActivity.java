package com.xiaoya.yidiantong.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;

import com.smartydroid.android.starter.kit.app.StarterActivity;
import com.smartydroid.android.starter.kit.utilities.ViewUtils;
import com.xiaoya.yidiantong.R;
import com.xiaoya.yidiantong.view.PinnedHeaderExpandableListView;

import java.util.ArrayList;
import java.util.List;

public class PreKnowledgeActivity extends StarterActivity implements
        ExpandableListView.OnChildClickListener,
        ExpandableListView.OnGroupClickListener,
        PinnedHeaderExpandableListView.OnHeaderUpdateListener{
    PinnedHeaderExpandableListView expandableListView;

    private List<String> title = new ArrayList<>();
    private List<String> content = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_knowledge);
    }

    @Override
    protected void setupViews() {
        expandableListView = ViewUtils.getView(this, R.id.listview);
        MyExpandableListAdapter myexpandableListAdapter = new MyExpandableListAdapter(this);
        expandableListView.setOnHeaderUpdateListener(this);
        expandableListView.setOnChildClickListener(this);
        expandableListView.setOnGroupClickListener(this);
        initData();
        expandableListView.setAdapter(myexpandableListAdapter);
    }

    private void initData(){
        title.add("expandableListView1");
        title.add("expandableListView2");
        title.add("expandableListView3");
        title.add("expandableListView4");
        content.add("BaseExpandableListAdapter1");
        content.add("BaseExpandableListAdapter2");
        content.add("BaseExpandableListAdapter3");
        content.add("BaseExpandableListAdapter4");
    }

    class MyExpandableListAdapter extends BaseExpandableListAdapter {
        private Context context;
        private LayoutInflater inflater;

        public MyExpandableListAdapter(Context context) {
            this.context = context;
            inflater = LayoutInflater.from(context);
        }

        @Override
        public int getGroupCount() {
            return 0;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return 0;
        }

        @Override
        public Object getGroup(int groupPosition) {
            return null;
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return null;
        }

        @Override
        public long getGroupId(int groupPosition) {
            return 0;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return 0;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            return null;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            return null;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return false;
        }
    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        return false;
    }

    @Override
    public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
        return false;
    }

    @Override
    public View getPinnedHeader() {
        return null;
    }

    @Override
    public void updatePinnedHeader(View headerView, int firstVisibleGroupPos) {

    }
}
