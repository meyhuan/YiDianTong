package com.xiaoya.yidiantong.model;

import android.os.Parcel;

import com.smartydroid.android.starter.kit.model.entity.Entity;

/**
 * Author: meyu
 * Date:   16/5/27
 * Email:  627655140@qq.com
 */
public class ExamRecord extends Entity{

    private String date;
    private String grade;
    private String spendTime;


    public ExamRecord(String date, String grade, String spendTime) {
        this.date = date;
        this.grade = grade;
        this.spendTime = spendTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getSpendTime() {
        return spendTime;
    }

    public void setSpendTime(String spendTime) {
        this.spendTime = spendTime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.spendTime);
        dest.writeString(this.grade);
        dest.writeString(this.date);
    }

    public ExamRecord() {
    }

    protected ExamRecord(Parcel in) {
        this.spendTime = in.readString();
        this.grade = in.readString();
        this.date = in.readString();
    }

    public static final Creator<ExamRecord> CREATOR = new Creator<ExamRecord>() {
        @Override
        public ExamRecord createFromParcel(Parcel source) {
            return new ExamRecord(source);
        }

        @Override
        public ExamRecord[] newArray(int size) {
            return new ExamRecord[size];
        }
    };
}
