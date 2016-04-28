package com.xiaoya.yidiantong.adapt;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiaoya.yidiantong.R;
import com.xiaoya.yidiantong.model.TraficSignAndMarkModel;
import com.xiaoya.yidiantong.ui.TraficSignAndMarkActivity;
import com.xiaoya.yidiantong.utils.TraficSignFileUtil;

import java.util.List;

/**
 * Author: meyu
 * Date:   16/4/26
 * Email:  627655140@qq.com
 */
public class TraficSignAndMarkCategoryAdapter extends SimpleBaseAdapter<TraficSignAndMarkModel> {


    public TraficSignAndMarkCategoryAdapter(Context paramContext) {
        super(paramContext);
    }

    public TraficSignAndMarkCategoryAdapter(Context paramContext, List<TraficSignAndMarkModel> paramList) {
        super(paramContext, paramList);
    }

    @Override
    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup) {
        ViewHolder viewHolder;
        final TraficSignAndMarkModel localTraficSignAndMarkModel = getList().get(paramInt);
        if (paramView == null) {
            viewHolder = new ViewHolder();
            paramView = getInflater().inflate(R.layout.item_trafic_category, null);
            viewHolder.iv = ((ImageView) paramView.findViewById(R.id.iv));
            viewHolder.titleTv = ((TextView) paramView.findViewById(R.id.tv_title));
            paramView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) paramView.getTag();
        }
        viewHolder.iv.setImageBitmap(TraficSignFileUtil.newInstance(mContext)
                .getBitmapFromAssets(localTraficSignAndMarkModel.getPicName()));
        String title = localTraficSignAndMarkModel.getName() + "(" + localTraficSignAndMarkModel.getSize() + ")";
        viewHolder.titleTv.setText(title);
        paramView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, TraficSignAndMarkActivity.class);
                intent.putExtra("id", localTraficSignAndMarkModel.getId());
                intent.putExtra("title", localTraficSignAndMarkModel.getName());
                mContext.startActivity(intent);
            }
        });
        return paramView;
    }

    private static class ViewHolder {
        ImageView iv;
        TextView titleTv;
    }
}
