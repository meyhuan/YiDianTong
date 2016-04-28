package com.xiaoya.yidiantong.model;

/**
 * Author: meyu
 * Date:   16/4/26
 * Email:  627655140@qq.com
 */

import android.content.Context;

import com.xiaoya.yidiantong.utils.TraficSignFileUtil;

import java.io.Serializable;
import java.util.List;

public class TraficSignAndMarkModel
        implements Serializable {
    private static final long serialVersionUID = 1L;
    private String content;
    private int id;
    private String name;
    private String picName;
    private int size;

    public static List<TraficSignAndMarkModel> getTraficCategory(Context paramContext) {
        return TraficSignFileUtil.newInstance(paramContext).readTraficCategory("traficSign/desc.txt");
    }

    public static List<TraficSignAndMarkModel> getTraficSignByCategoryId(Context paramContext, int paramInt) {
        return TraficSignFileUtil.newInstance(paramContext).getTraficSignByCategoryId(paramInt);
    }

    public String getContent() {
        return this.content;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getPicName() {
        return this.picName;
    }

    public int getSize() {
        return this.size;
    }

    public void setContent(String paramString) {
        this.content = paramString;
    }

    public void setId(int paramInt) {
        this.id = paramInt;
    }

    public void setName(String paramString) {
        this.name = paramString;
    }

    public void setPicName(String paramString) {
        this.picName = paramString;
    }

    public void setSize(int paramInt) {
        this.size = paramInt;
    }

    public String toString() {
        return "name:" + getName() + ",picName:" + getPicName();
    }
}