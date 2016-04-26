package com.xiaoya.yidiantong.ui;

import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smartydroid.android.starter.kit.app.StarterActivity;
import com.smartydroid.android.starter.kit.utilities.ViewUtils;
import com.xiaoya.yidiantong.R;
import com.xiaoya.yidiantong.utils.CustomTagHandler;
import com.xiaoya.yidiantong.utils.HtmlImageGetterUtil;
import com.xiaoya.yidiantong.model.ExpandableGroupModel;
import com.xiaoya.yidiantong.model.ExpandleChildModel;
import com.xiaoya.yidiantong.view.PinnedHeaderExpandableListView;

import java.io.Serializable;
import java.util.ArrayList;

public class PreKnowledgeActivity extends StarterActivity implements
        ExpandableListView.OnChildClickListener,
        ExpandableListView.OnGroupClickListener,
        PinnedHeaderExpandableListView.OnHeaderUpdateListener{
    PinnedHeaderExpandableListView expandableListView;

    private ArrayList<ExpandableGroupModel> mGroupList;
    private ArrayList<ExpandleChildModel> mchildList;

    private SubIndex mSubIndex;

    public static class SubIndex implements Serializable{
        public String [] groupTitleArr ;
        public String [] childContent;

        public SubIndex(String [] groupTitleArr, String [] childContent){
            this.groupTitleArr = groupTitleArr;
            this.childContent = childContent;
        }
    }

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

        this.mGroupList = new ArrayList<>();
        this.mchildList = new ArrayList<>();
        mSubIndex = (SubIndex) getIntent().getSerializableExtra("sub_index");
        if(mSubIndex == null){
            return;
        }

        for (int i = 0; i < mSubIndex.groupTitleArr.length; i ++){
            mGroupList.add(new ExpandableGroupModel(mSubIndex.groupTitleArr[i], 0));
            mchildList.add(new ExpandleChildModel(mSubIndex.groupTitleArr[i], mSubIndex.childContent[i]));
        }
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
            return mGroupList.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return 1;
        }

        @Override
        public Object getGroup(int groupPosition) {
            return mGroupList.get(groupPosition);
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return mchildList.get(groupPosition);
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
            GroupHolder groupHolder = null;
            if (convertView == null) {
                groupHolder = new GroupHolder();
                convertView = inflater.inflate(R.layout.item_group_exlist, null);
                groupHolder.textView = (TextView) convertView
                        .findViewById(R.id.tv_header);
                groupHolder.imageView = (ImageView) convertView
                        .findViewById(R.id.iv_up_down);
                convertView.setTag(groupHolder);
            } else {
                groupHolder = (GroupHolder) convertView.getTag();
            }

            groupHolder.textView.setText(((ExpandableGroupModel) getGroup(groupPosition))
                    .getTitle());
            if (isExpanded)// ture is Expanded or false is not isExpanded
                groupHolder.imageView.setImageResource(R.drawable.ic_group_up);
            else
                groupHolder.imageView.setImageResource(R.drawable.ic_group_down);
            return convertView;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            ChildHolder childHolder = null;
            if (convertView == null) {
                childHolder = new ChildHolder();
                convertView = inflater.inflate(R.layout.item_child_exlist, null);

                childHolder.textName = (TextView) convertView
                        .findViewById(R.id.tv);

                convertView.setTag(childHolder);
            } else {
                childHolder = (ChildHolder) convertView.getTag();
            }

            childHolder.textName.setText(Html.fromHtml(((ExpandleChildModel) getChild(groupPosition,
                    childPosition)).getContent(), new HtmlImageGetterUtil(mContext),new CustomTagHandler()));

            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }

    class GroupHolder {
        TextView textView;
        ImageView imageView;
    }

    class ChildHolder {
        TextView textName;
        TextView textAge;
        TextView textAddress;
        ImageView imageView;
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
        View headerView = (ViewGroup) getLayoutInflater().inflate(R.layout.item_group_exlist, null);
        headerView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        return null;
    }

    @Override
    public void updatePinnedHeader(View headerView, int firstVisibleGroupPos) {
//        TextView textView = (TextView) headerView.findViewById(R.id.tv_header);
//        textView.setText((mGroupList.get(firstVisibleGroupPos)).getTitle());
    }


}
