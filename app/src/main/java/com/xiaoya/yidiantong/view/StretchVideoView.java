package com.xiaoya.yidiantong.view;


import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

public class StretchVideoView extends VideoView {
    private Context mContext;

    public StretchVideoView(Context paramContext) {
        super(paramContext);
        this.mContext = paramContext;
    }

    public StretchVideoView(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
        this.mContext = paramContext;
    }

    public StretchVideoView(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
        super(paramContext, paramAttributeSet, paramInt);
        this.mContext = paramContext;
    }

    protected void onMeasure(int paramInt1, int paramInt2) {
        setMeasuredDimension(getDefaultSize(getWidth(), paramInt1), getDefaultSize(getHeight(), paramInt2));
    }
}