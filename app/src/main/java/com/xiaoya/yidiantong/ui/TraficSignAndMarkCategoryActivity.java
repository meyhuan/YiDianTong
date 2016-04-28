package com.xiaoya.yidiantong.ui;

import android.os.Bundle;
import android.widget.GridView;

import com.smartydroid.android.starter.kit.app.StarterActivity;
import com.xiaoya.yidiantong.R;
import com.xiaoya.yidiantong.adapt.TraficSignAndMarkCategoryAdapter;
import com.xiaoya.yidiantong.model.TraficSignAndMarkModel;
import com.xiaoya.yidiantong.utils.TraficSignFileUtil;

import java.util.List;

/**
 * Author: meyu
 * Date:   16/4/26
 * Email:  627655140@qq.com
 */
public class TraficSignAndMarkCategoryActivity extends StarterActivity{

    private GridView mGridView;
    private TraficSignAndMarkCategoryAdapter mAadapter;
    private List<TraficSignAndMarkModel> mList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trafic_sign);
    }

    @Override
    protected void setupViews() {
        mGridView = (GridView) findViewById(R.id.gv_trafic_sign);
        this.mList = TraficSignAndMarkModel.getTraficCategory(this);
        this.mAadapter = new TraficSignAndMarkCategoryAdapter(this, this.mList);
        mGridView.setAdapter(mAadapter);
    }
}
