package com.xiaoya.yidiantong.utils;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Html;

/**
 * Author: meyu
 * Date:   16/4/23
 * Email:  627655140@qq.com
 */



public class HtmlImageGetterUtil
        implements Html.ImageGetter {
    private Context mContext;

    public HtmlImageGetterUtil(Context paramContext) {
        this.mContext = paramContext;
    }

    public Drawable getDrawable(String paramString) {
        int i = this.mContext.getResources().getIdentifier(paramString, "drawable", this.mContext.getPackageName());
        Drawable drawable = this.mContext.getResources().getDrawable(i);
        i = drawable.getIntrinsicWidth();
        int j = drawable.getIntrinsicHeight();
        drawable.setBounds(0, 0, i, j);
        return drawable;
    }
}
