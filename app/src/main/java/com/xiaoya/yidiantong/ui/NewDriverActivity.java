package com.xiaoya.yidiantong.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.smartydroid.android.starter.kit.app.StarterActivity;
import com.xiaoya.yidiantong.R;

public class NewDriverActivity extends StarterActivity implements View.OnClickListener{

    private LinearLayout linearLayout1;
    private LinearLayout linearLayout2;
    private LinearLayout linearLayout3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_driver);
    }

    @Override
    protected void setupViews() {
        linearLayout1 = (LinearLayout) findViewById(R.id.new_driver_layout1);
        linearLayout2 = (LinearLayout) findViewById(R.id.new_driver_layout2);
        linearLayout3 = (LinearLayout) findViewById(R.id.new_driver_layout3);

        linearLayout1.setOnClickListener(this);
        linearLayout2.setOnClickListener(this);
        linearLayout3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(mContext, PreKnowledgeActivity.class);
        switch (v.getId()){
            case R.id.new_driver_layout1:
                intent.putExtra("sub_index", new PreKnowledgeActivity.SubIndex(
                        getResources().getStringArray(R.array.jiqiao_title),
                        getResources().getStringArray(R.array.jiqiao_content)));
                startActivity(intent);
                break;
            case R.id.new_driver_layout2:
                intent.putExtra("sub_index", new PreKnowledgeActivity.SubIndex(
                        getResources().getStringArray(R.array.law_title),
                        getResources().getStringArray(R.array.law_content)));
                startActivity(intent);
                break;
            case R.id.new_driver_layout3:
                intent.putExtra("sub_index", new PreKnowledgeActivity.SubIndex(
                        getResources().getStringArray(R.array.newest_driver_title2),
                        getResources().getStringArray(R.array.newest_driver_content2)));
                startActivity(intent);
                break;
        }
    }
}
