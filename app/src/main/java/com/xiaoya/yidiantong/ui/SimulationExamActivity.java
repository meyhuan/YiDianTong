package com.xiaoya.yidiantong.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.smartydroid.android.starter.kit.app.StarterActivity;
import com.smartydroid.android.starter.kit.model.entity.Entity;
import com.smartydroid.android.starter.kit.utilities.ACache;
import com.xiaoya.yidiantong.R;
import com.xiaoya.yidiantong.model.ExamRecord;
import com.xiaoya.yidiantong.utils.DataHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * User  : guanhuan
 * Date  : 2016/5/5
 */
public class SimulationExamActivity extends StarterActivity implements View.OnClickListener {
    private LinearLayout layoutSimulationRule;
    private TextView tvSimulationExamSize;
    private TextView tvSimulationExamTime;
    private TextView tvSimulationExamRule;
    private TextView tvSimulationExamTip;
    private LinearLayout layoutExamButton;
    private Button btnSimulationExam;
    private Button btnSimulationUndown;
    private TextView tvNoExamRecord;
    private ListView lvExamResultRecord;

    private List<ExamRecord> entityList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulation_exam);
    }

    @Override
    protected void setupViews() {
        assignViews();
        tvSimulationExamSize.setText(String.format(Locale.CHINA, getResources().getString(R.string.simulation_exam_size), 100));
        tvSimulationExamTime.setText(String.format(Locale.CHINA, getResources().getString(R.string.simulation_exam_time), 45));
        tvSimulationExamTip.setText(String.format(Locale.CHINA, getResources().getString(R.string.simulation_exam_tip), 10));

        entityList = DataHelper.getAsList("key_record_list");
        if (entityList != null) {
            lvExamResultRecord.setAdapter(new RecordAdapt());
            tvNoExamRecord.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_simulation_exam:
            case R.id.btn_simulation_undown:
                Intent intent = new Intent(mContext, ZhentiStudyQesPagerActivity.class);
                intent.putExtra("is_error", false);
                intent.putExtra("is_practice", true);
                intent.putExtra("is_simulation", true);
                startActivity(intent);
                finish();
                break;
        }
    }

    private void assignViews() {
        layoutSimulationRule = (LinearLayout) findViewById(R.id.layout_simulation_rule);
        tvSimulationExamSize = (TextView) findViewById(R.id.tv_simulation_exam_size);
        tvSimulationExamTime = (TextView) findViewById(R.id.tv_simulation_exam_time);
        tvSimulationExamRule = (TextView) findViewById(R.id.tv_simulation_exam_rule);
        tvSimulationExamTip = (TextView) findViewById(R.id.tv_simulation_exam_tip);
        layoutExamButton = (LinearLayout) findViewById(R.id.layout_exam_button);
        btnSimulationExam = (Button) findViewById(R.id.btn_simulation_exam);
        btnSimulationUndown = (Button) findViewById(R.id.btn_simulation_undown);
        tvNoExamRecord = (TextView) findViewById(R.id.tv_no_exam_record);
        lvExamResultRecord = (ListView) findViewById(R.id.lv_exam_result_record);

        btnSimulationExam.setOnClickListener(this);
        btnSimulationUndown.setOnClickListener(this);
    }

    class RecordAdapt extends BaseAdapter {

        @Override
        public int getCount() {
            return entityList.size();
        }

        @Override
        public Object getItem(int position) {
            return entityList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            RecordViewHolder viewHolder;
            ExamRecord examRecord = (ExamRecord) entityList.get(position);
            if (convertView == null) {
                viewHolder = new RecordViewHolder();
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_exam_result_record, null);
                viewHolder.date = (TextView) convertView.findViewById(R.id.tv_date);
                viewHolder.score = (TextView) convertView.findViewById(R.id.tv_score);
                viewHolder.spendTime = (TextView) convertView.findViewById(R.id.tv_time);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (RecordViewHolder) convertView.getTag();
            }
            viewHolder.date.setText(examRecord.getDate());
            viewHolder.score.setText(examRecord.getGrade());
            viewHolder.spendTime.setText(examRecord.getSpendTime());
            return convertView;
        }


    }


    class RecordViewHolder {
        public TextView date;
        public TextView score;
        public TextView spendTime;
    }

}
