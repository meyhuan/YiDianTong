package com.xiaoya.yidiantong.ui;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.smartydroid.android.starter.kit.app.StarterActivity;
import com.xiaoya.yidiantong.App;
import com.xiaoya.yidiantong.R;
import com.xiaoya.yidiantong.callback.QuesSelectCallback;
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
    private int currentPageIndex = 0;
    private boolean isPractice = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qeuestion_list);
        questionCategoryID = getIntent().getIntExtra("QuestionCategory_ID", 0);
        isPractice = getIntent().getBooleanExtra("is_practice", false);
        new DataTask().execute();
    }

    @Override
    protected void setupViews() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                currentPageIndex = position;
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }


    class ViewPageAdapt extends FragmentPagerAdapter  implements QuesSelectCallback{
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
            BaseQuestionFragment fragment = new BaseQuestionFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable("question",questionList.get(position));
            if(isPractice){
                bundle.putBoolean("type_select", true);
            }
            fragment.setArguments(bundle);
            fragment.setmQuesSelectCallback(this);
            return fragment;
        }


        @Override
        public int getCount() {
            return questionList.size();
        }

        @Override
        public void selectOption(Question question, int option) {
            if(question.getRightOption().equals(String.valueOf(option))){
                if(currentPageIndex + 1 <= getCount()){
                    viewPager.setCurrentItem(currentPageIndex + 1, true);
                }
            }
        }
    }

    class DataTask extends AsyncTask<Void, Integer, List<Question>>{

        @Override
        protected List<Question> doInBackground(Void... params) {
            if(isPractice){
                if(App.getCurrentSubject() == 1){
                    return DataSupport.where("kem = ?","1").find(Question.class);
                }else {
                    return DataSupport.where("kem = ?","4").find(Question.class);
                }
            }else {
                if(App.getCurrentSubject() == 1){
                    return DataSupport.where("question_category = ? and kem = ?",questionCategoryID+"", "1").find(Question.class);
                }else {
                    return DataSupport.where("question_category = ? and kem = ?",questionCategoryID+"", "4").find(Question.class);
                }

            }
        }

        @Override
        protected void onPostExecute(List<Question> questions) {
            super.onPostExecute(questions);
            viewPager.setAdapter(new ViewPageAdapt(getSupportFragmentManager(), questions));
        }
    }

}
