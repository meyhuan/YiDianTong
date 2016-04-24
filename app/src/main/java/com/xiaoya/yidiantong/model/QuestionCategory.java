package com.xiaoya.yidiantong.model;

import org.litepal.crud.DataSupport;

/**
 * Author: meyu
 * Date:   16/4/24
 * Email:  627655140@qq.com
 */
public class QuestionCategory extends DataSupport{
    private int id;
    private String categoryName;
    private String kem;
    private int num;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKem() {
        return kem;
    }

    public void setKem(String kem) {
        this.kem = kem;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "QuestionOptionType{" +
                "categoryName='" + categoryName + '\'' +
                ", id=" + id +
                ", kem=" + kem +
                ", num=" + num +
                '}';
    }
}
