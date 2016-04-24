package com.xiaoya.yidiantong.model;

/**
 * Author: meyu
 * Date:   16/4/23
 * Email:  627655140@qq.com
 */
public class ExpandableGroupModel {
    private int iconId;
    private String title;

    public ExpandableGroupModel(String paramString, int paramInt) {
        this.title = paramString;
        this.iconId = paramInt;
    }

    public int getIconId() {
        return this.iconId;
    }

    public String getTitle() {
        return this.title;
    }

    public void setIconId(int paramInt) {
        this.iconId = paramInt;
    }

    public void setTitle(String paramString) {
        this.title = paramString;
    }
}