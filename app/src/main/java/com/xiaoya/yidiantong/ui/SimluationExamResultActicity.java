package com.xiaoya.yidiantong.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smartydroid.android.starter.kit.app.StarterActivity;
import com.smartydroid.android.starter.kit.model.entity.Entity;
import com.smartydroid.android.starter.kit.utilities.ACache;
import com.smartydroid.android.starter.kit.utilities.DateUtils;
import com.xiaoya.yidiantong.App;
import com.xiaoya.yidiantong.R;
import com.xiaoya.yidiantong.model.ExamRecord;
import com.xiaoya.yidiantong.utils.DataHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * User  : guanhuan
 * Date  : 2016/5/6
 */
public class SimluationExamResultActicity extends StarterActivity{

    private ImageView ivUserHead;
    private TextView tvScore;
    private TextView tvTime;
    private LinearLayout llComment;
    private TextView tvComment;
    private ImageView ivGood;
    private LinearLayout llShare;


    private int time = 0;
    private int scores = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulation_result);
    }

    @Override
    protected void setupViews() {
        assignViews();
        time = getIntent().getIntExtra("time", 0);
        scores = getIntent().getIntExtra("scores", 0);
        int minute =  time / 1000 / 60;
        int timeString = time / 1000 % 60;
        String spendTime = String.valueOf(minute + "分" + timeString + "秒");
        tvTime.setText(spendTime);
        tvScore.setText(String.valueOf(scores+ "分"));
        int sub = 1;
        if(App.getCurrentSubject() == 1){
            sub = 1;
        }else if(App.getCurrentSubject() == 4){
            sub = 4;
        }
        if(scores < 90){
            String s = getResources().getString(R.string.simulation_exam_result_content1);
            tvComment.setText(String.format(Locale.CHINA, s, sub));
        }else {
            String s = getResources().getString(R.string.simulation_exam_result_content2);
            tvComment.setText(String.format(Locale.CHINA, s, sub));
            ivGood.setVisibility(View.VISIBLE);
        }
        List<ExamRecord> list =  DataHelper.getAsList("key_record_list");
        if(list == null){
            list = new ArrayList<>();
        }
        Date date = new Date(System.currentTimeMillis());
        String formatData = new  SimpleDateFormat("yyyy-MM-dd hh:mm", Locale.CHINA).format(date);
        list.add(new ExamRecord(formatData , scores+"分", spendTime));
       DataHelper.put(list, "key_record_list");
    }



    private void assignViews() {
        ivUserHead = (ImageView) findViewById(R.id.iv_user_head);
        tvScore = (TextView) findViewById(R.id.tv_score);
        tvTime = (TextView) findViewById(R.id.tv_time);
        tvComment = (TextView) findViewById(R.id.tv_comment);
        ivGood = (ImageView) findViewById(R.id.iv_good);
        llShare = (LinearLayout) findViewById(R.id.ll_share);
        ivGood.setVisibility(View.INVISIBLE);
    }
}
