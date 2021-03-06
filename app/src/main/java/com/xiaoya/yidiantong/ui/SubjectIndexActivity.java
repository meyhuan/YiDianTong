package com.xiaoya.yidiantong.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.smartydroid.android.starter.kit.app.StarterActivity;
import com.xiaoya.yidiantong.App;
import com.xiaoya.yidiantong.R;

/**
 * Author: meyu
 * Date:   16/4/24
 * Email:  627655140@qq.com
 */
public class SubjectIndexActivity extends StarterActivity implements View.OnClickListener{

    private ScrollView scrollView;
    private RelativeLayout layoutSubZhenti;
    private RelativeLayout layoutSubBiguomiji;
    private RelativeLayout layoutSubTrafficSign;
    private RelativeLayout layoutOrderPractice;
    private TextView tvPracticeSize;
    private RelativeLayout layoutRandomPractice;
    private TextView tvRandomPracticeSize;
    private RelativeLayout layoutErrorQes;
    private TextView tvErrorQesSize;
    private RelativeLayout layoutSimulationTest;
    private RelativeLayout layoutWeekExam;
    private TextView tvWeekExamSize;
    private RelativeLayout layoutGuessQes;
    private TextView tvGuessQesSize;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);
        if(App.getCurrentSubject() == 1){
            toolbar.setTitle("科目1");
        }else if(App.getCurrentSubject() == 4){
            toolbar.setTitle("科目4");
        }
    }

    @Override
    protected void setupViews() {
        assignViews();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.layout_sub_zhenti:
                startActivity(ZhentiStudyActivity.class);
                break;
            case R.id.layout_sub_biguomiji:
                Intent intent = new Intent(mContext, PreKnowledgeActivity.class);
                intent.putExtra("sub_index", new PreKnowledgeActivity.SubIndex(
                        getResources().getStringArray(R.array.biguo_title),
                        getResources().getStringArray(R.array.biguo_content)));
                startActivity(intent);
                break;
            case R.id.layout_sub_traffic_sign:
                startActivity(TraficSignAndMarkCategoryActivity.class);
                break;
            case R.id.layout_order_practice:
                Intent practiceIntent = new Intent(mContext, ZhentiStudyQesPagerActivity.class);
                practiceIntent.putExtra("is_practice",true);
                startActivity(practiceIntent);
                break;
            case R.id.layout_random_practice:
                Intent practiceIntent2 = new Intent(mContext, ZhentiStudyQesPagerActivity.class);
                practiceIntent2.putExtra("is_practice",true);
                practiceIntent2.putExtra("is_random",true);
                startActivity(practiceIntent2);
                break;
            case R.id.layout_error_qes:
                startActivity(ErrorQuestionActivity.class);
                break;
            case R.id.layout_simulation_test:
                startActivity(SimulationExamActivity.class);
                break;
            case R.id.layout_week_exam:

                break;
            case R.id.layout_guess_qes:

                break;
        }
    }



    private void assignViews() {
        scrollView = (ScrollView) findViewById(R.id.scrollView);
        layoutSubZhenti = (RelativeLayout) findViewById(R.id.layout_sub_zhenti);
        layoutSubBiguomiji = (RelativeLayout) findViewById(R.id.layout_sub_biguomiji);
        layoutSubTrafficSign = (RelativeLayout) findViewById(R.id.layout_sub_traffic_sign);
        layoutOrderPractice = (RelativeLayout) findViewById(R.id.layout_order_practice);
        tvPracticeSize = (TextView) findViewById(R.id.tv_practice_size);
        layoutRandomPractice = (RelativeLayout) findViewById(R.id.layout_random_practice);
        tvRandomPracticeSize = (TextView) findViewById(R.id.tv_random_practice_size);
        layoutErrorQes = (RelativeLayout) findViewById(R.id.layout_error_qes);
        tvErrorQesSize = (TextView) findViewById(R.id.tv_error_qes_size);
        layoutSimulationTest = (RelativeLayout) findViewById(R.id.layout_simulation_test);
        layoutWeekExam = (RelativeLayout) findViewById(R.id.layout_week_exam);
        tvWeekExamSize = (TextView) findViewById(R.id.tv_week_exam_size);
        layoutGuessQes = (RelativeLayout) findViewById(R.id.layout_guess_qes);
        tvGuessQesSize = (TextView) findViewById(R.id.tv_guess_qes_size);

        this.layoutSubZhenti.setOnClickListener(this);
        this.layoutSubBiguomiji.setOnClickListener(this);
        this.layoutSubTrafficSign.setOnClickListener(this);
        this.layoutOrderPractice.setOnClickListener(this);
        this.layoutRandomPractice.setOnClickListener(this);
        this.layoutErrorQes.setOnClickListener(this);
        this.layoutSimulationTest.setOnClickListener(this);
        this.layoutWeekExam.setOnClickListener(this);
        this.layoutGuessQes.setOnClickListener(this);
    }

}
