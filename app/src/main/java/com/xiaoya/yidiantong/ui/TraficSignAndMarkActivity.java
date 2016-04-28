package com.xiaoya.yidiantong.ui;

import android.os.Bundle;
import android.widget.GridView;

import com.smartydroid.android.starter.kit.app.StarterActivity;
import com.xiaoya.yidiantong.R;
import com.xiaoya.yidiantong.adapt.TraficSignAndMarkAdapter;
import com.xiaoya.yidiantong.adapt.TraficSignAndMarkCategoryAdapter;
import com.xiaoya.yidiantong.model.TraficSignAndMarkModel;

import java.util.List;

/**
 * Author: meyu
 * Date:   16/4/26
 * Email:  627655140@qq.com
 */
public class TraficSignAndMarkActivity extends StarterActivity{

    public static final String LIST = "list";
    public static final String POS = "pos";
    private int categoryId;
    private TraficSignAndMarkAdapter mAadapter;
    private GridView mGridView;
    private List<TraficSignAndMarkModel> mList;
    private String mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trafic_sign);
    }

    @Override
    protected void setupViews() {
        getData();
        mGridView = (GridView) findViewById(R.id.gv_trafic_sign);
        this.mAadapter = new TraficSignAndMarkAdapter(this, this.mList);
        mGridView.setAdapter(mAadapter);
        toolbar.setTitle(mTitle);
    }

    private void getData()
    {
        this.mTitle = getIntent().getStringExtra("title");
        this.categoryId = getIntent().getIntExtra("id", 0);
        this.mList = TraficSignAndMarkModel.getTraficSignByCategoryId(this, this.categoryId);
    }
}
