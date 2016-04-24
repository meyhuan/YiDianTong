package com.xiaoya.yidiantong.model;

/**
 * Author: meyu
 * Date:   16/4/23
 * Email:  627655140@qq.com
 */

public class ExpandleChildModel {
    private String content;
    private String title;

    public ExpandleChildModel(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getContent() {
        return this.content;
    }

    public String getTitle() {
        return this.title;
    }

    public void setContent(String paramString) {
        this.content = paramString;
    }

    public void setTitle(String paramString) {
        this.title = paramString;
    }
}