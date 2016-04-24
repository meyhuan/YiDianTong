package com.xiaoya.yidiantong.ui;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.smartydroid.android.starter.kit.app.StarterActivity;
import com.xiaoya.yidiantong.R;
import com.xiaoya.yidiantong.model.Question;

import org.litepal.crud.DataSupport;

import java.util.List;

import retrofit2.http.POST;

/**
 * Author: meyu
 * Date:   16/4/24
 * Email:  627655140@qq.com
 */
public class ZhentiStudyQesPagerActivity extends StarterActivity{

    ViewPager viewPager ;
    private List<Question> questionList;
    private int questionCategoryID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qeuestion_list);
        questionCategoryID = getIntent().getIntExtra("QuestionCategory_ID", 0);
        new DataTask().execute();
    }

    @Override
    protected void setupViews() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
    }

    class ViewPageAdapt extends FragmentPagerAdapter{
        private List<Question> questionList;

        public ViewPageAdapt(FragmentManager fm,List<Question> questionList){
            this(fm);
            this.questionList = questionList;
        }

        public ViewPageAdapt(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = new BaseQuestionFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable("question",questionList.get(position));
            fragment.setArguments(bundle);
            return fragment;
        }

        @Override
        public int getCount() {
            return questionList.size();
        }
    }

    class DataTask extends AsyncTask<Void, Integer, List<Question>>{

        @Override
        protected List<Question> doInBackground(Void... params) {
            return DataSupport.where("question_category = ?",questionCategoryID+"").find(Question.class);
        }

        @Override
        protected void onPostExecute(List<Question> questions) {
            super.onPostExecute(questions);
            viewPager.setAdapter(new ViewPageAdapt(getSupportFragmentManager(), questions));
        }
    }

}
