package com.xiaoya.yidiantong.model;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * Author: meyu
 * Date:   16/4/23
 * Email:  627655140@qq.com
 */
public class Question extends DataSupport implements Serializable{
    private String analysis;
    private String difficylty;
    private int id;
    private int kem;
    private String down;
    private String cx;
    private int media_type;
    private String media_content;
    private String option_a;
    private String option_b;
    private String option_c;
    private String option_d;
    private int question_type;
    private String probability;
    private String question;
    private String question_category;
    private String rightOption;
    private String your_small_answer;
    private String your_bus_answer;
    private String your_truck_answer;

    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }

    public String getCx() {
        return cx;
    }

    public void setCx(String cx) {
        this.cx = cx;
    }

    public String getDifficylty() {
        return difficylty;
    }

    public void setDifficylty(String difficylty) {
        this.difficylty = difficylty;
    }

    public String getDown() {
        return down;
    }

    public void setDown(String down) {
        this.down = down;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getKem() {
        return kem;
    }

    public void setKem(int kem) {
        this.kem = kem;
    }

    public String getMedia_content() {
        return media_content;
    }

    public void setMedia_content(String media_content) {
        this.media_content = media_content;
    }

    public int getMedia_type() {
        return media_type;
    }

    public void setMedia_type(int media_type) {
        this.media_type = media_type;
    }

    public String getOption_a() {
        return option_a;
    }

    public void setOption_a(String option_a) {
        this.option_a = option_a;
    }

    public String getOption_b() {
        return option_b;
    }

    public void setOption_b(String option_b) {
        this.option_b = option_b;
    }

    public String getOption_c() {
        return option_c;
    }

    public void setOption_c(String option_c) {
        this.option_c = option_c;
    }

    public String getOption_d() {
        return option_d;
    }

    public void setOption_d(String option_d) {
        this.option_d = option_d;
    }

    public String getProbability() {
        return probability;
    }

    public void setProbability(String probability) {
        this.probability = probability;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestion_category() {
        return question_category;
    }

    public void setQuestion_category(String question_category) {
        this.question_category = question_category;
    }

    public int getQuestion_type() {
        return question_type;
    }

    public void setQuestion_type(int question_type) {
        this.question_type = question_type;
    }

    public String getRightOption() {
        return rightOption;
    }

    public void setRightOption(String rightOption) {
        this.rightOption = rightOption;
    }

    public String getYour_bus_answer() {
        return your_bus_answer;
    }

    public void setYour_bus_answer(String your_bus_answer) {
        this.your_bus_answer = your_bus_answer;
    }

    public String getYour_small_answer() {
        return your_small_answer;
    }

    public void setYour_small_answer(String your_small_answer) {
        this.your_small_answer = your_small_answer;
    }

    public String getYour_truck_answer() {
        return your_truck_answer;
    }

    public void setYour_truck_answer(String your_truck_answer) {
        this.your_truck_answer = your_truck_answer;
    }

    @Override
    public String toString() {
        return "question{" +
                "analysis='" + analysis + '\'' +
                ", difficylty='" + difficylty + '\'' +
                ", id=" + id +
                ", kem=" + kem +
                ", down='" + down + '\'' +
                ", cx='" + cx + '\'' +
                ", media_type=" + media_type +
                ", media_content='" + media_content + '\'' +
                ", option_a='" + option_a + '\'' +
                ", option_b='" + option_b + '\'' +
                ", option_c='" + option_c + '\'' +
                ", option_d='" + option_d + '\'' +
                ", question_type=" + question_type +
                ", probability='" + probability + '\'' +
                ", question='" + question + '\'' +
                ", question_category='" + question_category + '\'' +
                ", rightOption='" + rightOption + '\'' +
                ", your_small_answer='" + your_small_answer + '\'' +
                ", your_bus_answer='" + your_bus_answer + '\'' +
                ", your_truck_answer='" + your_truck_answer + '\'' +
                '}';
    }
}
