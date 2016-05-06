package com.xiaoya.yidiantong.ui;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;

import com.smartydroid.android.starter.kit.app.StarterActivity;
import com.smartydroid.android.starter.kit.utilities.Utils;
import com.xiaoya.yidiantong.App;
import com.xiaoya.yidiantong.R;
import com.xiaoya.yidiantong.callback.QuesSelectCallback;
import com.xiaoya.yidiantong.model.Question;

import org.litepal.crud.DataSupport;

import java.util.Collections;
import java.util.List;
import java.util.Random;

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
    private boolean isError = false;
    private boolean isSimulation = false;

    TextView rightTextView;

    private static final int ExameTime = 45;
    private int scores = 0;
    private long time = 0;
    private int totalTimes = 1 * 60 * 1000;
    private int errorCount = 0;
    CountDownTimer countDownTimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        questionCategoryID = getIntent().getIntExtra("QuestionCategory_ID", 0);
        isPractice = getIntent().getBooleanExtra("is_practice", false);
        isError = getIntent().getBooleanExtra("is_error", false);
        isSimulation = getIntent().getBooleanExtra("is_simulation", false);
        setContentView(R.layout.activity_qeuestion_list);
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
                if(rightTextView != null) {
                    rightTextView.setText("第" + (position + 1) + "/" + questionList.size() + "題");
                }
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
                scores ++;
                if(currentPageIndex + 1 <= getCount()){
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            viewPager.setCurrentItem(currentPageIndex + 1, true);
                        }
                    },500);
                }
            }else {
                errorCount++;
                if(errorCount > 10){
                    startScoreActivity((int) time, scores);
                }
            }
        }
    }

    class DataTask extends AsyncTask<Void, Integer, List<Question>>{

        @Override
        protected List<Question> doInBackground(Void... params) {
            if(isPractice){
                if(App.getCurrentSubject() == 1){
                    if(isError){
                        return DataSupport.where("kem = ? and your_small_answer = ?","1", "0").find(Question.class);
                    }else {
                        return DataSupport.where("kem = ?","1").find(Question.class);
                    }
                }else {
                    if(isError){

                        return DataSupport.where("kem = ? and your_small_answer = ?","4", "0").find(Question.class);
                    }else {
                        return DataSupport.where("kem = ?","4").find(Question.class);
                    }
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
            questionList = questions;
            if(isPractice && isSimulation && !isError){
                //随机取出一百道题目
                Random random = new Random(System.currentTimeMillis());
                Collections.shuffle(questions,random);
                for (int i = 100; i < questions.size();){
                    questions.remove(i);
                }
                startCount();
            }
            viewPager.setAdapter(new ViewPageAdapt(getSupportFragmentManager(), questions));
        }
    }

    private void startCount(){
        countDownTimer = new CountDownTimer(1 * 60 * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long minute = millisUntilFinished / 1000 / 60;
                long second = millisUntilFinished / 1000 % 60;
                toolbar.setTitle(minute + ":" + second);
                time = totalTimes - millisUntilFinished;
            }

            @Override
            public void onFinish() {
                showToastShort("考试时间到了");
                toolbar.setTitle(0 + ":" + 0);
                startScoreActivity(ExameTime, scores);
            }
        };
        countDownTimer.start();
    }

    private void startScoreActivity(int time, int score){
        Intent intent = new Intent(this, SimluationExamResultActicity.class);
        intent.putExtra("time", time);
        intent.putExtra("scores", score);
        startActivity(intent);
        finish();
    }

    @Override
    public void onCreateCustomToolBar(Toolbar toolbar) {
        super.onCreateCustomToolBar(toolbar);
        if(isPractice && isSimulation && !isError){
            rightTextView = new TextView(this);
            rightTextView.setTextColor(getResources().getColor(R.color.white));
            rightTextView.setTextSize(Utils.dip2px(this, 9));
            rightTextView.setText("第" + 1 + "/" + 100 + "題");
            Toolbar.LayoutParams layoutParams = new Toolbar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.END);
            layoutParams.rightMargin = Utils.dip2px(this, 10);
            toolbar.addView(rightTextView, layoutParams);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(countDownTimer != null){
            countDownTimer.cancel();
        }
    }
}
