package com.xiaoya.yidiantong.ui;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.smartydroid.android.starter.kit.app.StarterActivity;
import com.xiaoya.yidiantong.App;
import com.xiaoya.yidiantong.R;
import com.xiaoya.yidiantong.model.Question;
import com.xiaoya.yidiantong.model.QuestionCategory;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: meyu
 * Date:   16/4/24
 * Email:  627655140@qq.com
 */
public class ZhentiStudyActivity extends StarterActivity implements AdapterView.OnItemClickListener{

    private ListView listView;
    private List<QuestionCategory> categoryList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhenti_study);
        toolbar.setTitle("真题学习");
        new DataTask().execute();
    }


    @Override
    protected void setupViews() {
        listView = (ListView) findViewById(R.id.listview);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(mContext, ZhentiStudyQesPagerActivity.class);
        intent.putExtra("QuestionCategory_ID", categoryList.get(position).getId());
        intent.putExtra("is_practice",false);
        startActivity(intent);
    }

    class ZhenTiAdapt extends BaseAdapter {
        private List<QuestionCategory> categoryList ;

        public ZhenTiAdapt(List<QuestionCategory> categoryList){
            this.categoryList = categoryList;
        }

        @Override
        public int getCount() {
            return categoryList.size();
        }

        @Override
        public Object getItem(int position) {
            return categoryList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            MyViewHolder holder = null;
            if(convertView == null){
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_zhenti_list, null);
                holder = new MyViewHolder();
                holder.tv_category = (TextView) convertView.findViewById(R.id.tv_category);
                holder.tv_num = (TextView) convertView.findViewById(R.id.tv_num);
                convertView.setTag(holder);
            }else {
                holder = (MyViewHolder) convertView.getTag();
            }
            QuestionCategory category = categoryList.get(position);
            holder.tv_category.setText(category.getCategoryName());
            holder.tv_num.setText(category.getNum()+"题");
            return convertView;
        }
    }

    class MyViewHolder {
        public TextView tv_category;
        public TextView tv_num;
    }

    class DataTask extends AsyncTask<Void, Integer, List<QuestionCategory>>{

        @Override
        protected List<QuestionCategory> doInBackground(Void... params) {
            if(App.getCurrentSubject() == 1){
                return categoryList = DataSupport.where("kem = ?","1").find(QuestionCategory.class);
            }else {
                return categoryList = DataSupport.where("kem = ?","4").find(QuestionCategory.class);
            }
        }

        @Override
        protected void onPostExecute(List<QuestionCategory> questionCategories) {
            List<QuestionCategory> list = new ArrayList<>();
            for (int i = 0; i < 4; i ++) {
                list.add(questionCategories.get(i));
            }
            ZhenTiAdapt zhenTiAdapt = new ZhenTiAdapt(list);
            listView.setAdapter(zhenTiAdapt);
            zhenTiAdapt.notifyDataSetChanged();
        }
    }
}
